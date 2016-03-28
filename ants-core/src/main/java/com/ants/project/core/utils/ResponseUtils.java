package com.ants.project.core.utils;

import javax.xml.ws.Response;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liushun
 * @version 1.0
 * @Date 2016-03-09
 */
public class ResponseUtils{


    //执行状态
    private static final String STATE = "ResponseCode";

    //消息描述
    private static final String MSG = "ResponseMessage";

    //数据对象
    private static final String RESULT = "result";

    //时间戳
    private static final String TIMESPAN = "timeSpan";

    //异常消息
    private static final String ERROR = "exception";


    //响应吗定义
    enum ResponseCode {

        SUCCESS(0, "成功"),
        DATA_FAIL(1, "数据参数错误"),
        SYSTEM_ERROR(2, "系统异常错误"),
        TOKEN_ERROR(3, "token 无效"),
        USER_NO_AUTH(4, "无权限访问");

        private int code;
        private String msg;

        ResponseCode(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }


    /**
     * 操作失败
     * @param data 数据对象
     * @param startTime 开始时间
     * @param <T>
     * @return
     */
    public static <T>Map jsonSuccess(T data, Long startTime){
        Map map = new HashMap();
        map.put(STATE, ResponseCode.SUCCESS.getCode());
        map.put(MSG, ResponseCode.SUCCESS.getMsg());
        map.put(RESULT, data);
        map.put(TIMESPAN, System.currentTimeMillis()-startTime+" msec");
        return map;
    }

    /**
     * 操作异常失败
     * @param responseCode 枚举响应码
     * @param exception 异常信息
     * @return
     */
    public static Map jsonException(ResponseCode responseCode, String exception){
        Map map = new HashMap();
        map.put(STATE, responseCode.getCode());
        map.put(MSG, responseCode.getMsg());
        map.put(ERROR, exception);
        return map;
    }

    /**
     * 操作错误
     * @param responseCode 枚举响应码
     * @return
     */
    public static Map jsonFail(ResponseCode responseCode){
        Map map = new HashMap();
        map.put(STATE, responseCode.getCode());
        map.put(MSG, responseCode.getMsg());
        return map;
    }

    /**
     * 操作错误自定义错误码
     * @param msg 自定义系统错误消息
     * @return
     */
    public static Map jsonFail(String msg){
        Map map = new HashMap();
        map.put(STATE, ResponseCode.SYSTEM_ERROR.getCode());
        map.put(MSG, msg);
        return map;
    }

}