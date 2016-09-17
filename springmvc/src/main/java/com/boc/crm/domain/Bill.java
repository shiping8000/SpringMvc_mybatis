package com.boc.crm.domain;

import java.math.BigDecimal;

import com.boc.crm.base.model.BaseModel;

/**
 * 
 * <br>
 * <b>功能：</b>BillEntity<br>
 */
public class Bill extends BaseModel {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private java.lang.Integer id;//   自增主键	private BigDecimal amount;//   消费金额 单位元	private BigDecimal ltlExp;//   手续费用 单位（元）	private java.lang.String terminalNo;//   终端号	private java.lang.String terminalName;//   终端名称	private java.lang.String cardNo;//   银行卡号	private java.lang.String merchantId;//   商户Id	private java.lang.String agentId;//   代理商ID	private java.lang.String status;//   状态 0 初始化  1 成功  2 失败	private java.util.Date createTime;//   创建时间	public java.lang.Integer getId() {	    return this.id;	}	public void setId(java.lang.Integer id) {	    this.id=id;	}	public BigDecimal getAmount() {	    return this.amount;	}	public void setAmount(BigDecimal amount) {	    this.amount=amount;	}	public BigDecimal getLtlExp() {	    return this.ltlExp;	}	public void setLtlExp(BigDecimal ltlExp) {	    this.ltlExp=ltlExp;	}	public java.lang.String getTerminalNo() {	    return this.terminalNo;	}	public void setTerminalNo(java.lang.String terminalNo) {	    this.terminalNo=terminalNo;	}	public java.lang.String getTerminalName() {	    return this.terminalName;	}	public void setTerminalName(java.lang.String terminalName) {	    this.terminalName=terminalName;	}	public java.lang.String getCardNo() {	    return this.cardNo;	}	public void setCardNo(java.lang.String cardNo) {	    this.cardNo=cardNo;	}	public java.lang.String getMerchantId() {	    return this.merchantId;	}	public void setMerchantId(java.lang.String merchantId) {	    this.merchantId=merchantId;	}	public java.lang.String getAgentId() {	    return this.agentId;	}	public void setAgentId(java.lang.String agentId) {	    this.agentId=agentId;	}	public java.lang.String getStatus() {	    return this.status;	}	public void setStatus(java.lang.String status) {	    this.status=status;	}	public java.util.Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.util.Date createTime) {	    this.createTime=createTime;	}
}

