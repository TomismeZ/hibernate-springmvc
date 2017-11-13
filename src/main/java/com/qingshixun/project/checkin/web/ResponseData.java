/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.checkin.web;

import java.io.Serializable;

/**
 * 基于Ajax方式调用时返回结果对象
 */
public class ResponseData implements Serializable {
	
	//操作执行成功
	private static final String SUCCESS_CODE = "0";

	//操作执行失败
	private static final String ERROR_CODE = "-1";

	//默认返回状态为“成功”
	private String status = SUCCESS_CODE;

	//操作返回数据内容
	private Object data;

	public boolean isSuccess() {
		return this.status.equalsIgnoreCase(SUCCESS_CODE) ? true : false;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setError(Object data) {
		this.status = ERROR_CODE;
		this.data = data;
	}

	public void setError(String status, Object data) {
		this.status = status;
		this.data = data;
	}
}

