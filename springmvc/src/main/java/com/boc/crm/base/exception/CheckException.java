package com.boc.crm.base.exception;


/**
 * 数据验证异常
 * @author Neo
 * 2014-2-7
 */
public class CheckException extends Exception {

	private static final long serialVersionUID = 8480852132973391742L;
	public static int TYPE_ERROR = 0;
	public static int TYPE_WARN = 1;
	
	/**
	 * 错误代码
	 */
	protected String errorCode;
	/**
	 * 异常类型
	 */
	protected int type;

	public CheckException(String message) {
		super(message);
		this.type = TYPE_ERROR;
	}

	public CheckException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
		this.type = TYPE_ERROR;
	}
	
	public CheckException(String message, String errorCode,int type) {
		super(message);
		this.errorCode = errorCode;
		this.type = type;
	}
	
	public CheckException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CheckException(String message, String errorCode, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public CheckException(Throwable cause) {
		super(cause);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "[" +errorCode + "] : " + "["+getMessage()+"]" + super.toString();
	}
	
	public boolean isWarn(){
		if(type==TYPE_WARN){
			return true;
		}
		return false;
	}
	
	public boolean isError(){
		if(type==TYPE_ERROR){
			return true;
		}
		return false;
	}
}
