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
     * 数据
     */
    private byte[] data;

    public Response(Request request){
        this.module=request.getModule();
        this.cmd=request.getCmd();

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
}
