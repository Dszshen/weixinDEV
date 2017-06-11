package com.dszshen.wxsdk.common.util;

public class JsonResult<T> {
    // code：200：正常响应。data中有必要的数据。
    // Code：603: 数据没有变化
    // Code：601：数据未经授权（标示用户已经在服务端处于非登录状态，需要重新登录），本地需要清除用户的login状态
    // 其他
    // code 300~399：需要显示错误信息。并停留在当前页面。
    // code: 400~499：用户数据错误，需要在提示完reason后，用户点击确定，退出客户端。

    public static final String CODE_OK = "200";
    public static final String CODE_NOCHANGE = "603";
    public static final String CODE_RELOGIN = "601";
    public static final String CODE_ERROR = "300";
    public static final String CODE_ERROR_INVALID_TOKEN = "301";// TOKEN(UID)问题
    public static final String CODE_FAULT_ERROR = "400";

    public static final String FLAG_SUCCESS = "success";//成功标记
    public static final String FLAG_FAILURE = "failure";//失败标记
    public static final String FLAG_ERROR = "error";//出错标记

    public static final int SUCCESS = 200;

    private int code;
    private String flag;//标记是否成功，成功为success,失败为failure,程序内部出错为error
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }






    /**
     * 程序出错
     * @param code
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> error(int code) {
        JsonResult<T> result = new JsonResult<T>();
        result.setCode(code);
        result.setFlag(FLAG_ERROR);
        return result;
    }

    /**
     * 程序出错，只有错误提示信息
     * @param message
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> error(String message) {
        JsonResult<T> result = new JsonResult<T>();
        result.setFlag(FLAG_ERROR);
        return result;
    }

    /**
     * 程序出错，含出错码和出错信息
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> error(int code, String message) {
        JsonResult<T> result = new JsonResult<T>();
        result.setCode(code);
        result.setFlag(FLAG_ERROR);
        result.setMessage(message);
        return result;
    }

    /**
     * 请求成功,并返回接口要求的数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> success(T data) {
        JsonResult<T> result = new JsonResult<T>();
        result.setFlag(FLAG_SUCCESS);
        result.setCode(SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 请求成功，并返回接口要求的数据和成功的提示信息
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> success(String message, T data) {
        JsonResult<T> result = new JsonResult<T>();
        result.setFlag(FLAG_SUCCESS);
        result.setCode(SUCCESS);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 操作失败，并返回接口要求的数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> failure(T data) {
        JsonResult<T> result = new JsonResult<T>();
        result.setCode(SUCCESS);
        result.setFlag(FLAG_FAILURE);
        result.setData(data);
        return result;
    }

    /**
     * 操作失败，并返回接口要求的数据和失败信息
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> JsonResult<T> failure(String message, T data) {
        JsonResult<T> result = new JsonResult<T>();
        result.setCode(SUCCESS);
        result.setFlag(FLAG_FAILURE);
        result.setMessage(message);
        result.setData(data);
        return result;
    }



    public static <T> JsonResult<T> result(String flag,String message,T data,int code) {
        JsonResult<T> jsonResult = new JsonResult<T>();

        if(flag.equals("success")){
            jsonResult.setFlag(FLAG_SUCCESS);
            jsonResult.setCode(SUCCESS);
        }else if(flag.equals("failure")){
            jsonResult.setCode(SUCCESS);
            jsonResult.setFlag(FLAG_FAILURE);
        }else if(flag.equals("error")){
            jsonResult.setCode(code);
            jsonResult.setFlag(FLAG_ERROR);
        }

        jsonResult.setMessage(message);
        jsonResult.setData(data);
        return jsonResult;
    }


}
