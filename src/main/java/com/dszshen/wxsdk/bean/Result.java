package com.dszshen.wxsdk.bean;

import java.util.List;

/**
 * Created by zhangbin on 2017/5/29 0029.
 */
public class Result<T> {
    private Error error;
    private T result;
    private List<T> results;


}
