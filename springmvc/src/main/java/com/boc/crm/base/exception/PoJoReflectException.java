package com.boc.crm.base.exception;

public class PoJoReflectException extends CheckException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PoJoReflectException(String message, String errorCode) {
		super(message, errorCode);		
	}
	
	public PoJoReflectException(String[] excepInfo) {
		super(excepInfo[1],excepInfo[0]);		
	}
	
	@SuppressWarnings("null")
	public PoJoReflectException(String[] excepInfo,String extInfo) {
		super(excepInfo!=null?excepInfo[1]+"["+extInfo+"]":excepInfo[1],excepInfo[0]);		
	}
	
	public static String[] CODE_1_NOREADMETHOD = {"PE001","no read method for property in bean!"};
	public static String[] CODE_2_CREATEPRODESCRIPTOR = {"PE002","create property's PropertyDescriptor exception!"};
	public static String[] CODE_3_INVOKEGETSETMETHOD = {"PE003","invoke get or set method exception!"};
}
