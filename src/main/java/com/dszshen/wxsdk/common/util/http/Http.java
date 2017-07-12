package com.dszshen.wxsdk.common.util.http;

import com.belerweb.social.http.HttpException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.Args;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangbin on 2017/5/30 0030.
 */
public final class Http {
    private static final String[] AGENTS = new String[]{"Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1664.3 Safari/537.36", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.66 Safari/535.11", "Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.66 Safari/535.11", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.66 Safari/535.11", "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.66 Safari/535.11", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.66 Safari/535.11", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.66 Safari/535.11", "Mozilla/5.0 (Windows NT 6.0; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.66 Safari/535.11", "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.66 Safari/535.11", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.66 Safari/535.11", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.66 Safari/535.11", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.66 Safari/535.11", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.66 Safari/535.11", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_5_8) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.66 Safari/535.11", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; WOW64; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ; SLCC1; .NET CLR 2.0.50727; .NET CLR 3.0.04506; Media Center PC 5.0; .NET CLR 3.5.21022; GreenBrowser)", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Trident/4.0; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; GreenBrowser)", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Trident/4.0; SLCC1; .NET CLR 2.0.50727; .NET CLR 3.5.30729; InfoPath.2; .NET CLR 3.0.30729; GreenBrowser)", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Trident/4.0; SLCC1; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; OfficeLiveConnector.1.4; OfficeLivePatch.1.3; GreenBrowser)", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; GTB6; .NET CLR 2.0.50727; GreenBrowser)", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; GTB6; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 1.1.4322; GreenBrowser)", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; GTB6.3; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 1.1.4322; GreenBrowser)", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; GTB0.0; InfoPath.1; GreenBrowser)", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 4.0.20506; GreenBrowser)", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 1.1.4322; InfoPath.2; GreenBrowser)", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 1.0.3705; .NET CLR 1.1.4322; GreenBrowser)", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; .NET CLR 2.0.50727; .NET CLR 1.1.4322; .NET CLR 3.0.04506.30; GreenBrowser)", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; InfoPath.2; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; GreenBrowser)", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1) ; .NET CLR 1.0.3705; .NET CLR 1.1.4322; Media Center PC 4.0; .NET CLR 2.0.50727; InfoPath.1; GreenBrowser)", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 2.0.50727; GreenBrowser)", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; GreenBrowser)", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 1.1.4322; GreenBrowser)", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022; .NET CLR 1.1.4322; GreenBrowser)", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.62 Safari/537.36"};
    public static final HttpClient CLIENT = newClient();

    public Http() {
    }

    public static String get(String uri, List<NameValuePair> params) throws HttpException {
        String url = uri;
        if(params != null) {
            String param = StringUtils.join(params, "&");
            if(uri.contains("?")) {
                url = uri + "&" + param;
            } else {
                url = uri + "?" + param;
            }
        }

        return get(url, new Header[0]);
    }

    public static String get(String uri, Header... headers) throws HttpException {
        HttpGet request = new HttpGet(uri);
        if(headers != null) {
            Header[] arr$ = headers;
            int len$ = headers.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Header header = arr$[i$];
                request.addHeader(header);
            }
        }

        return execute(request);
    }

    public static String post(String uri, HttpEntity postBody, Header... headers) throws HttpException {
        HttpPost request = new HttpPost(uri);
        if(postBody != null) {
            request.setEntity(postBody);
        }

        if(headers != null) {
            Header[] arr$ = headers;
            int len$ = headers.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Header header = arr$[i$];
                request.addHeader(header);
            }
        }

        return execute(request);
    }

    public static String post(String uri, List<NameValuePair> params, String charset, Header... headers) throws HttpException {
        HttpPost request = new HttpPost(uri);
        if(params != null) {
            List<NameValuePair> parameters = new ArrayList();
            parameters.addAll(params);

            try {
                HttpEntity entity = new UrlEncodedFormEntity(parameters, charset);
                request.setEntity(entity);
            } catch (UnsupportedEncodingException var9) {
                throw new HttpException(var9);
            }
        }

        if(headers != null) {
            Header[] arr$ = headers;
            int len$ = headers.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Header header = arr$[i$];
                request.addHeader(header);
            }
        }

        return execute(request);
    }

    public static String post(String uri) throws HttpException {
        return post(uri, (HttpEntity)null, new Header[0]);
    }

    private static String execute(HttpRequestBase request) throws HttpException {
        String var2;
        try {
            HttpResponse response = CLIENT.execute(request);
            var2 = responseToString(response);
        } catch (ClientProtocolException var7) {
            throw new HttpException(var7);
        } catch (IOException var8) {
            throw new HttpException(var8);
        } finally {
            request.releaseConnection();
        }

        return var2;
    }

    public static boolean isRequestSuccess(HttpResponse response) throws HttpException {
        return response.getStatusLine() != null && response.getStatusLine().getStatusCode() == 200;
    }

    public static String responseToString(HttpResponse response) throws HttpException {
        HttpEntity entity = response.getEntity();
        String result = null;
        if(entity != null) {
            Charset charset = null;
            Header encoding = entity.getContentEncoding();
            if(encoding == null) {
                ContentType contentType = ContentType.get(entity);
                if(contentType != null) {
                    charset = contentType.getCharset();
                }
            } else {
                charset = Charset.forName(encoding.getValue());
            }

            try {
                result = IOUtils.toString(entity.getContent(), charset);
                return result;
            } catch (Exception var6) {
                throw new HttpException(var6);
            }
        } else {
            throw new HttpException("No response entity.");
        }
    }

    public static String randomAgent() {
        return AGENTS[RandomUtils.nextInt(AGENTS.length)];
    }

    public static HttpClient newClient() {
        SSLContext sslContext = SSLContexts.createDefault();

        try {
            sslContext = SSLContexts.custom().loadTrustMaterial((KeyStore)null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return HttpClientBuilder.create().setSslcontext(sslContext).setMaxConnPerRoute(50).setMaxConnTotal(200).setUserAgent(randomAgent()).setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000).setConnectionRequestTimeout(30000).build()).build();
    }
}
