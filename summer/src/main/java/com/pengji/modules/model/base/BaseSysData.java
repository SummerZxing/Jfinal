package com.pengji.modules.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysData<M extends BaseSysData<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}

	public java.lang.String getName() {
		return getStr("name");
	}

	public M setDescription(java.lang.String description) {
		set("description", description);
		return (M)this;
	}

	public java.lang.String getDescription() {
		return getStr("description");
	}

	public M setAttachmentId(java.lang.Integer attachmentId) {
		set("attachmentId", attachmentId);
		return (M)this;
	}

	public java.lang.Integer getAttachmentId() {
		return getInt("attachmentId");
	}

	public M setSubjectId(java.lang.Integer subjectId) {
		set("subjectId", subjectId);
		return (M)this;
	}

	public java.lang.Integer getSubjectId() {
		return getInt("subjectId");
	}

	public M setCreateBy(java.lang.Integer createBy) {
		set("createBy", createBy);
		return (M)this;
	}

	public java.lang.Integer getCreateBy() {
		return getInt("createBy");
	}

	public M setCreateDate(java.util.Date createDate) {
		set("createDate", createDate);
		return (M)this;
	}

	public java.util.Date getCreateDate() {
		return get("createDate");
	}

	public M setUpdateBy(java.lang.Integer updateBy) {
		set("updateBy", updateBy);
		return (M)this;
	}

	public java.lang.Integer getUpdateBy() {
		return getInt("updateBy");
	}

	public M setUpdateDate(java.util.Date updateDate) {
		set("updateDate", updateDate);
		return (M)this;
	}

	public java.util.Date getUpdateDate() {
		return get("updateDate");
	}

	public M setRemarks(java.lang.String remarks) {
		set("remarks", remarks);
		return (M)this;
	}

	public java.lang.String getRemarks() {
		return getStr("remarks");
	}

	public M setDelFlag(java.lang.String delFlag) {
		set("delFlag", delFlag);
		return (M)this;
	}

	public java.lang.String getDelFlag() {
		return getStr("delFlag");
	}

	public M setStatus(java.lang.String status) {
		set("status", status);
		return (M)this;
	}

	public java.lang.String getStatus() {
		return getStr("status");
	}

	public M setGradeId(java.lang.Integer gradeId) {
		set("gradeId", gradeId);
		return (M)this;
	}

	public java.lang.Integer getGradeId() {
		return getInt("gradeId");
	}

	public M setDataLink(java.lang.String dataLink) {
		set("dataLink", dataLink);
		return (M)this;
	}

	public java.lang.String getDataLink() {
		return getStr("dataLink");
	}

	public M setAdminTop(java.lang.String adminTop) {
		set("adminTop", adminTop);
		return (M)this;
	}

	public java.lang.String getAdminTop() {
		return getStr("adminTop");
	}

	public M setUserTop(java.lang.String userTop) {
		set("userTop", userTop);
		return (M)this;
	}

	public java.lang.String getUserTop() {
		return getStr("userTop");
	}

}
