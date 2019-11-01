package com.xxx.xxx.application.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 基础应答对象
 *
 * @author long
 */
@Data
public class GenericResponse<T> implements Serializable {

	/**
	 * 错误码
	 */
	private String errorCode;

	/**
	 * 错误描述，可以通过面向切面方式统一填充
	 */
	private String errorMsg;

	/**
	 * 数据对象
	 */
	private T data;

	public GenericResponse() {
	}

	/**
	 * 成功
	 *
	 * @param <T>
	 * @return
	 */
	public static <T> GenericResponse<T> succ() {
		return succ(null, null);
	}

	/**
	 * 成功
	 *
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> GenericResponse<T> succ(T data) {
		return succ(null, data);
	}

	/**
	 * 成功
	 *
	 * @param errorMsg
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> GenericResponse<T> succ(String errorMsg, T data) {
		GenericResponse<T> result = new GenericResponse<>();
		result.setErrorCode(ErrorCodes.SUCCESS);
		result.setErrorMsg(errorMsg);
		result.setData(data);
		return result;
	}


	/**
	 * 失败
	 *
	 * @param errorCode
	 * @param <T>
	 * @return
	 */
	public static <T> GenericResponse<T> err(String errorCode) {
		return err(errorCode, null);
	}

	/**
	 * 失败
	 *
	 * @param errorCode
	 * @param errorMsg
	 * @param <T>
	 * @return
	 */
	public static <T> GenericResponse<T> err(String errorCode, String errorMsg) {
		return err(errorCode, errorMsg, null);
	}

	/**
	 * 失败
	 *
	 * @param errorCode
	 * @param errorMsg
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> GenericResponse<T> err(String errorCode, String errorMsg, T data) {
		GenericResponse<T> result = new GenericResponse<>();
		result.setErrorCode(errorCode);
		result.setErrorMsg(errorMsg);
		result.setData(data);
		return result;
	}

}
