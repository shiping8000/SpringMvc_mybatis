package com.boc.crm.domain;

import com.boc.crm.base.model.BaseModel;

/**
 * 
 * <br>
 * <b>功能：</b>SystemNoticeEntity<br>
 */
public class SystemNotice extends BaseModel {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private java.lang.Integer id;//   	private java.lang.Integer serverId;//   服务器ID	private java.lang.String serverName;//   服务器名称	private java.lang.Integer type;//   公告类型1服务器2系统	private java.lang.String content;//   公告内容	private java.util.Date createTime;//   创建时间	private java.lang.Integer delay;//   播放间隔	private java.util.Date noticeTime;//   上次播报时间		public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}
	public java.lang.Integer getServerId() {	    return this.serverId;	}	public void setServerId(java.lang.Integer serverId) {	    this.serverId=serverId;	}	public java.lang.String getServerName() {	    return this.serverName;	}	public void setServerName(java.lang.String serverName) {	    this.serverName=serverName;	}	public java.lang.Integer getType() {	    return this.type;	}	public void setType(java.lang.Integer type) {	    this.type=type;	}	public java.lang.String getContent() {	    return this.content;	}	public void setContent(java.lang.String content) {	    this.content=content;	}	public java.util.Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.util.Date createTime) {	    this.createTime=createTime;	}	public java.lang.Integer getDelay() {	    return this.delay;	}	public void setDelay(java.lang.Integer delay) {	    this.delay=delay;	}	public java.util.Date getNoticeTime() {	    return this.noticeTime;	}	public void setNoticeTime(java.util.Date noticeTime) {	    this.noticeTime=noticeTime;	}
}

