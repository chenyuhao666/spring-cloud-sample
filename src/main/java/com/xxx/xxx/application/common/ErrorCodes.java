package com.xxx.xxx.application.common;

/**
 * 格式：<应用名>.编号，例如：APP.0092
 *
 * <p>
 * 默认编号：000：成功；092：网络异常；096：系统异常
 *
 * @author Ryan
 */
public final class ErrorCodes {

	/**
	 * 成功
	 */
	public static final String SUCCESS = "APP.000";

	/**
	 * 网络异常
	 */
	public static final String NETWORK_ERROR = "APP.092";

	/**
	 * 系统异常
	 */
	public static final String SYS_ERROR = "APP.096";

	private ErrorCodes() {

	}
}
