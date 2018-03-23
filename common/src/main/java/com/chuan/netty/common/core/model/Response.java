package com.chuan.netty.common.core.model;

public class Response {
    /**
     * 模块号
     */
    private short module;

    /**
     * 命令号
     */
    private short cmd;

    /**
     * 返回类型
     */
    private short type;

    /**
     * 状态码
     */
    private short status;

    /**
     * 数据
     */
    private byte[] data;

    public Response(Request request){
        this.module=request.getModule();
        this.cmd=request.getCmd();

    }

    public Response(){

    }

    public Response(short module, short cmd, short type, byte[] data) {
        this.module = module;
        this.cmd = cmd;
        this.type = type;
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public short getModule() {
        return module;
    }

    public void setModule(short module) {
        this.module = module;
    }

    public short getCmd() {
        return cmd;
    }

    public void setCmd(short cmd) {
        this.cmd = cmd;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }
}
