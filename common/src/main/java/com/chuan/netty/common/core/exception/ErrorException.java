package com.chuan.netty.common.core.exception;

import com.chuan.netty.common.core.myenum.ErrorEnum;

public class ErrorException extends Exception{
    /**
     * 异常状态码
     */
    private int errorCode;
    /**
     * 异常信息
     */
    private String messsage;

    public ErrorException(int errorCode, String messsage) {
        super();
        this.errorCode = errorCode;
        this.messsage = messsage;
    }

    public ErrorException(ErrorEnum errorEnum) {
        super();
        this.errorCode=errorEnum.getCode();
        this.messsage=errorEnum.getMessage();
    }

    public ErrorException() {
        super();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

    @Override
    public String toString() {
        return "发生异常:状态码"+errorCode+"\n"+messsage;
    }
}
