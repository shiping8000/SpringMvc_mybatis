package com.boc.crm.dao.criteria;

import com.boc.crm.base.mybatis.BaseCriteria;
import java.util.List;
/**
 * 
 * <br>
 * <b>功能：</b>SystemNoticeCriteria<br>
 */
public class SystemNoticeCriteria extends BaseCriteria {
	
	// -------------------------- id ----------------------------
	
	public SystemNoticeCriteria andIdIsNull() {addCriterion("id is null"); return this ;}
	public SystemNoticeCriteria andIdIsNotNull() {addCriterion("id is not null"); return this ;}
	public SystemNoticeCriteria andIdEqualTo(String value) {addCriterion("id = ", value , "id"); return this ;}
	public SystemNoticeCriteria andIdNotEqualTo(String value) {addCriterion("id <> ", value , "id"); return this ;}
	public SystemNoticeCriteria andIdGreaterThan(String value) {addCriterion("id > ", value , "id"); return this ;}
	public SystemNoticeCriteria andIdGreaterThanOrEqualTo(String value) {addCriterion("id >= ", value , "id"); return this ;}
	public SystemNoticeCriteria andIdLessThan(String value) {addCriterion("id < ", value , "id"); return this ;}
	public SystemNoticeCriteria andIdLessThanOrEqualTo(String value) {addCriterion("id <= ", value , "id"); return this ;}
	public SystemNoticeCriteria andIdLike(String value) {addCriterion("id like ", value , "id"); return this ;}
	public SystemNoticeCriteria andIdNotLike(String value) {addCriterion("id not like ", value , "id"); return this ;}
	public SystemNoticeCriteria andIdIn(List<String> values) {addCriterion("id in ", values , "id"); return this ;}
	public SystemNoticeCriteria andIdNotIn(List<String> values) {addCriterion("id not in ", values , "id"); return this ;}
	public SystemNoticeCriteria andIdBetween(String value1, String value2) {addCriterion("id between ", value1 , value2, "id"); return this ;}
	public SystemNoticeCriteria andIdNotBetween(String value1, String value2) {addCriterion("id not between ", value1 , value2, "id"); return this ;}
	
	// -------------------------- server_id ----------------------------
	
	public SystemNoticeCriteria andServerIdIsNull() {addCriterion("server_id is null"); return this ;}
	public SystemNoticeCriteria andServerIdIsNotNull() {addCriterion("server_id is not null"); return this ;}
	public SystemNoticeCriteria andServerIdEqualTo(String value) {addCriterion("server_id = ", value , "serverId"); return this ;}
	public SystemNoticeCriteria andServerIdNotEqualTo(String value) {addCriterion("server_id <> ", value , "serverId"); return this ;}
	public SystemNoticeCriteria andServerIdGreaterThan(String value) {addCriterion("server_id > ", value , "serverId"); return this ;}
	public SystemNoticeCriteria andServerIdGreaterThanOrEqualTo(String value) {addCriterion("server_id >= ", value , "serverId"); return this ;}
	public SystemNoticeCriteria andServerIdLessThan(String value) {addCriterion("server_id < ", value , "serverId"); return this ;}
	public SystemNoticeCriteria andServerIdLessThanOrEqualTo(String value) {addCriterion("server_id <= ", value , "serverId"); return this ;}
	public SystemNoticeCriteria andServerIdLike(String value) {addCriterion("server_id like ", value , "serverId"); return this ;}
	public SystemNoticeCriteria andServerIdNotLike(String value) {addCriterion("server_id not like ", value , "serverId"); return this ;}
	public SystemNoticeCriteria andServerIdIn(List<String> values) {addCriterion("server_id in ", values , "serverId"); return this ;}
	public SystemNoticeCriteria andServerIdNotIn(List<String> values) {addCriterion("server_id not in ", values , "serverId"); return this ;}
	public SystemNoticeCriteria andServerIdBetween(String value1, String value2) {addCriterion("server_id between ", value1 , value2, "serverId"); return this ;}
	public SystemNoticeCriteria andServerIdNotBetween(String value1, String value2) {addCriterion("server_id not between ", value1 , value2, "serverId"); return this ;}
	
	// -------------------------- server_name ----------------------------
	
	public SystemNoticeCriteria andServerNameIsNull() {addCriterion("server_name is null"); return this ;}
	public SystemNoticeCriteria andServerNameIsNotNull() {addCriterion("server_name is not null"); return this ;}
	public SystemNoticeCriteria andServerNameEqualTo(String value) {addCriterion("server_name = ", value , "serverName"); return this ;}
	public SystemNoticeCriteria andServerNameNotEqualTo(String value) {addCriterion("server_name <> ", value , "serverName"); return this ;}
	public SystemNoticeCriteria andServerNameGreaterThan(String value) {addCriterion("server_name > ", value , "serverName"); return this ;}
	public SystemNoticeCriteria andServerNameGreaterThanOrEqualTo(String value) {addCriterion("server_name >= ", value , "serverName"); return this ;}
	public SystemNoticeCriteria andServerNameLessThan(String value) {addCriterion("server_name < ", value , "serverName"); return this ;}
	public SystemNoticeCriteria andServerNameLessThanOrEqualTo(String value) {addCriterion("server_name <= ", value , "serverName"); return this ;}
	public SystemNoticeCriteria andServerNameLike(String value) {addCriterion("server_name like ", value , "serverName"); return this ;}
	public SystemNoticeCriteria andServerNameNotLike(String value) {addCriterion("server_name not like ", value , "serverName"); return this ;}
	public SystemNoticeCriteria andServerNameIn(List<String> values) {addCriterion("server_name in ", values , "serverName"); return this ;}
	public SystemNoticeCriteria andServerNameNotIn(List<String> values) {addCriterion("server_name not in ", values , "serverName"); return this ;}
	public SystemNoticeCriteria andServerNameBetween(String value1, String value2) {addCriterion("server_name between ", value1 , value2, "serverName"); return this ;}
	public SystemNoticeCriteria andServerNameNotBetween(String value1, String value2) {addCriterion("server_name not between ", value1 , value2, "serverName"); return this ;}
	
	// -------------------------- type ----------------------------
	
	public SystemNoticeCriteria andTypeIsNull() {addCriterion("type is null"); return this ;}
	public SystemNoticeCriteria andTypeIsNotNull() {addCriterion("type is not null"); return this ;}
	public SystemNoticeCriteria andTypeEqualTo(String value) {addCriterion("type = ", value , "type"); return this ;}
	public SystemNoticeCriteria andTypeNotEqualTo(String value) {addCriterion("type <> ", value , "type"); return this ;}
	public SystemNoticeCriteria andTypeGreaterThan(String value) {addCriterion("type > ", value , "type"); return this ;}
	public SystemNoticeCriteria andTypeGreaterThanOrEqualTo(String value) {addCriterion("type >= ", value , "type"); return this ;}
	public SystemNoticeCriteria andTypeLessThan(String value) {addCriterion("type < ", value , "type"); return this ;}
	public SystemNoticeCriteria andTypeLessThanOrEqualTo(String value) {addCriterion("type <= ", value , "type"); return this ;}
	public SystemNoticeCriteria andTypeLike(String value) {addCriterion("type like ", value , "type"); return this ;}
	public SystemNoticeCriteria andTypeNotLike(String value) {addCriterion("type not like ", value , "type"); return this ;}
	public SystemNoticeCriteria andTypeIn(List<String> values) {addCriterion("type in ", values , "type"); return this ;}
	public SystemNoticeCriteria andTypeNotIn(List<String> values) {addCriterion("type not in ", values , "type"); return this ;}
	public SystemNoticeCriteria andTypeBetween(String value1, String value2) {addCriterion("type between ", value1 , value2, "type"); return this ;}
	public SystemNoticeCriteria andTypeNotBetween(String value1, String value2) {addCriterion("type not between ", value1 , value2, "type"); return this ;}
	
	// -------------------------- content ----------------------------
	
	public SystemNoticeCriteria andContentIsNull() {addCriterion("content is null"); return this ;}
	public SystemNoticeCriteria andContentIsNotNull() {addCriterion("content is not null"); return this ;}
	public SystemNoticeCriteria andContentEqualTo(String value) {addCriterion("content = ", value , "content"); return this ;}
	public SystemNoticeCriteria andContentNotEqualTo(String value) {addCriterion("content <> ", value , "content"); return this ;}
	public SystemNoticeCriteria andContentGreaterThan(String value) {addCriterion("content > ", value , "content"); return this ;}
	public SystemNoticeCriteria andContentGreaterThanOrEqualTo(String value) {addCriterion("content >= ", value , "content"); return this ;}
	public SystemNoticeCriteria andContentLessThan(String value) {addCriterion("content < ", value , "content"); return this ;}
	public SystemNoticeCriteria andContentLessThanOrEqualTo(String value) {addCriterion("content <= ", value , "content"); return this ;}
	public SystemNoticeCriteria andContentLike(String value) {addCriterion("content like ", value , "content"); return this ;}
	public SystemNoticeCriteria andContentNotLike(String value) {addCriterion("content not like ", value , "content"); return this ;}
	public SystemNoticeCriteria andContentIn(List<String> values) {addCriterion("content in ", values , "content"); return this ;}
	public SystemNoticeCriteria andContentNotIn(List<String> values) {addCriterion("content not in ", values , "content"); return this ;}
	public SystemNoticeCriteria andContentBetween(String value1, String value2) {addCriterion("content between ", value1 , value2, "content"); return this ;}
	public SystemNoticeCriteria andContentNotBetween(String value1, String value2) {addCriterion("content not between ", value1 , value2, "content"); return this ;}
	
	// -------------------------- create_time ----------------------------
	
	public SystemNoticeCriteria andCreateTimeIsNull() {addCriterion("create_time is null"); return this ;}
	public SystemNoticeCriteria andCreateTimeIsNotNull() {addCriterion("create_time is not null"); return this ;}
	public SystemNoticeCriteria andCreateTimeEqualTo(String value) {addCriterion("create_time = ", value , "createTime"); return this ;}
	public SystemNoticeCriteria andCreateTimeNotEqualTo(String value) {addCriterion("create_time <> ", value , "createTime"); return this ;}
	public SystemNoticeCriteria andCreateTimeGreaterThan(String value) {addCriterion("create_time > ", value , "createTime"); return this ;}
	public SystemNoticeCriteria andCreateTimeGreaterThanOrEqualTo(String value) {addCriterion("create_time >= ", value , "createTime"); return this ;}
	public SystemNoticeCriteria andCreateTimeLessThan(String value) {addCriterion("create_time < ", value , "createTime"); return this ;}
	public SystemNoticeCriteria andCreateTimeLessThanOrEqualTo(String value) {addCriterion("create_time <= ", value , "createTime"); return this ;}
	public SystemNoticeCriteria andCreateTimeLike(String value) {addCriterion("create_time like ", value , "createTime"); return this ;}
	public SystemNoticeCriteria andCreateTimeNotLike(String value) {addCriterion("create_time not like ", value , "createTime"); return this ;}
	public SystemNoticeCriteria andCreateTimeIn(List<String> values) {addCriterion("create_time in ", values , "createTime"); return this ;}
	public SystemNoticeCriteria andCreateTimeNotIn(List<String> values) {addCriterion("create_time not in ", values , "createTime"); return this ;}
	public SystemNoticeCriteria andCreateTimeBetween(String value1, String value2) {addCriterion("create_time between ", value1 , value2, "createTime"); return this ;}
	public SystemNoticeCriteria andCreateTimeNotBetween(String value1, String value2) {addCriterion("create_time not between ", value1 , value2, "createTime"); return this ;}
	
	// -------------------------- delay ----------------------------
	
	public SystemNoticeCriteria andDelayIsNull() {addCriterion("delay is null"); return this ;}
	public SystemNoticeCriteria andDelayIsNotNull() {addCriterion("delay is not null"); return this ;}
	public SystemNoticeCriteria andDelayEqualTo(String value) {addCriterion("delay = ", value , "delay"); return this ;}
	public SystemNoticeCriteria andDelayNotEqualTo(String value) {addCriterion("delay <> ", value , "delay"); return this ;}
	public SystemNoticeCriteria andDelayGreaterThan(String value) {addCriterion("delay > ", value , "delay"); return this ;}
	public SystemNoticeCriteria andDelayGreaterThanOrEqualTo(String value) {addCriterion("delay >= ", value , "delay"); return this ;}
	public SystemNoticeCriteria andDelayLessThan(String value) {addCriterion("delay < ", value , "delay"); return this ;}
	public SystemNoticeCriteria andDelayLessThanOrEqualTo(String value) {addCriterion("delay <= ", value , "delay"); return this ;}
	public SystemNoticeCriteria andDelayLike(String value) {addCriterion("delay like ", value , "delay"); return this ;}
	public SystemNoticeCriteria andDelayNotLike(String value) {addCriterion("delay not like ", value , "delay"); return this ;}
	public SystemNoticeCriteria andDelayIn(List<String> values) {addCriterion("delay in ", values , "delay"); return this ;}
	public SystemNoticeCriteria andDelayNotIn(List<String> values) {addCriterion("delay not in ", values , "delay"); return this ;}
	public SystemNoticeCriteria andDelayBetween(String value1, String value2) {addCriterion("delay between ", value1 , value2, "delay"); return this ;}
	public SystemNoticeCriteria andDelayNotBetween(String value1, String value2) {addCriterion("delay not between ", value1 , value2, "delay"); return this ;}
	
	// -------------------------- notice_time ----------------------------
	
	public SystemNoticeCriteria andNoticeTimeIsNull() {addCriterion("notice_time is null"); return this ;}
	public SystemNoticeCriteria andNoticeTimeIsNotNull() {addCriterion("notice_time is not null"); return this ;}
	public SystemNoticeCriteria andNoticeTimeEqualTo(String value) {addCriterion("notice_time = ", value , "noticeTime"); return this ;}
	public SystemNoticeCriteria andNoticeTimeNotEqualTo(String value) {addCriterion("notice_time <> ", value , "noticeTime"); return this ;}
	public SystemNoticeCriteria andNoticeTimeGreaterThan(String value) {addCriterion("notice_time > ", value , "noticeTime"); return this ;}
	public SystemNoticeCriteria andNoticeTimeGreaterThanOrEqualTo(String value) {addCriterion("notice_time >= ", value , "noticeTime"); return this ;}
	public SystemNoticeCriteria andNoticeTimeLessThan(String value) {addCriterion("notice_time < ", value , "noticeTime"); return this ;}
	public SystemNoticeCriteria andNoticeTimeLessThanOrEqualTo(String value) {addCriterion("notice_time <= ", value , "noticeTime"); return this ;}
	public SystemNoticeCriteria andNoticeTimeLike(String value) {addCriterion("notice_time like ", value , "noticeTime"); return this ;}
	public SystemNoticeCriteria andNoticeTimeNotLike(String value) {addCriterion("notice_time not like ", value , "noticeTime"); return this ;}
	public SystemNoticeCriteria andNoticeTimeIn(List<String> values) {addCriterion("notice_time in ", values , "noticeTime"); return this ;}
	public SystemNoticeCriteria andNoticeTimeNotIn(List<String> values) {addCriterion("notice_time not in ", values , "noticeTime"); return this ;}
	public SystemNoticeCriteria andNoticeTimeBetween(String value1, String value2) {addCriterion("notice_time between ", value1 , value2, "noticeTime"); return this ;}
	public SystemNoticeCriteria andNoticeTimeNotBetween(String value1, String value2) {addCriterion("notice_time not between ", value1 , value2, "noticeTime"); return this ;}
	
}

