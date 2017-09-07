package com.zhiren.fuelmis.dc.entity.hetgl;

/**
 * 计价组件
 * @author ZY
 *
 */

public class PriceComponent {
	private Long id;
	private String name;
	private String classname;
	private String url;
	private String remarks;
	private int status;
	private Long created_by_userid;
	private String created_by_useracc;
	private String created_by_username;
	private String creation_date;
	private Long last_updated_by_userid;
	private String last_updated_by_useracc;
	private String last_updated_by_username;
	private String last_update_date;
	private Long diancxxb_id;
	
	
	
	public Long getDiancxxb_id() {
		return diancxxb_id;
	}
	public void setDiancxxb_id(Long diancxxb_id) {
		this.diancxxb_id = diancxxb_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getCreated_by_userid() {
		return created_by_userid;
	}
	public void setCreated_by_userid(Long created_by_userid) {
		this.created_by_userid = created_by_userid;
	}
	public String getCreated_by_useracc() {
		return created_by_useracc;
	}
	public void setCreated_by_useracc(String created_by_useracc) {
		this.created_by_useracc = created_by_useracc;
	}
	public String getCreated_by_username() {
		return created_by_username;
	}
	public void setCreated_by_username(String created_by_username) {
		this.created_by_username = created_by_username;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public Long getLast_updated_by_userid() {
		return last_updated_by_userid;
	}
	public void setLast_updated_by_userid(Long last_updated_by_userid) {
		this.last_updated_by_userid = last_updated_by_userid;
	}
	public String getLast_updated_by_useracc() {
		return last_updated_by_useracc;
	}
	public void setLast_updated_by_useracc(String last_updated_by_useracc) {
		this.last_updated_by_useracc = last_updated_by_useracc;
	}
	public String getLast_updated_by_username() {
		return last_updated_by_username;
	}
	public void setLast_updated_by_username(String last_updated_by_username) {
		this.last_updated_by_username = last_updated_by_username;
	}
	public String getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}
}
