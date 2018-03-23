package constans;

public class NettyMessageType {
    /**
     * 握手连接请求
     */
    public static final short LOGIN_REQ=100;

    /**
     * 握手连接响应
     */
    public static final short LOGIN_RESP=101;


    /**
     * 心跳包请求
     */
    public static final short HEART_REQ=102;

    /**
     * 心跳包响应
     */
    public static final short HEART_RESP=103;

    /**
     * 包头标识
     */
    public static final int HEADER_FLAG=-21415431;


}
