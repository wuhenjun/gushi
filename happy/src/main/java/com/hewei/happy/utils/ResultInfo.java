package com.hewei.happy.utils;

import java.io.Serializable;

public class ResultInfo<T> implements Serializable {
    private static final long serialVersionUID = -3034369732746020867L;
    private Integer resultCode;
    private String resultMsg;
    private T result;

    public ResultInfo() {
    }

    public  static <T> ResultInfo build(Integer resultCode, String resultMsg, T result){
        return new ResultInfo( resultCode,  resultMsg,  result);
    }

    private ResultInfo(Integer resultCode, String resultMsg, T result) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.result = result;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
