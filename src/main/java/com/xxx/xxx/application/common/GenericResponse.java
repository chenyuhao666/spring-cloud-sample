package com.xxx.xxx.application.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 响应统一处理
 * @author long
 */
@Data
public class GenericResponse<T> implements Serializable {
	
	private static final long serialVersionUID = 8078728290429694954L;
	
	public static final String SUCC_CODE = "0";
	
	/**
	 * 状态码：
	 * 	0 成功
	 *  其他为失败
	 */
    private String code;

    /**
     * 其他为失败原因
     */
    private String message;

    /**
     * 数据结果集
     */
    private T data;
    
    public GenericResponse() {
    }
    
    
    public static <T> GenericResponse<T> succ() {
        GenericResponse<T> result = new GenericResponse<>();
    	result.setCode(SUCC_CODE);
    	return result;
    }
    
    public static <T> GenericResponse<T> succ(T data) {
        GenericResponse<T> result = new GenericResponse<>();
    	result.setCode(SUCC_CODE);
    	result.setData(data);
    	return result;
    }

    public static <T> GenericResponse<T> err(String msg) {
        GenericResponse<T> result = new GenericResponse<>();
        result.setMessage(msg);
        return result;
    }

}
