package com.zhiren.fuelmis.dc.entity.hetgl;

public class PriceQalityRange {
	
	private Long id;
	private Long po_sub_id;
	private Long price_component_id;
	private Long quality_item_id;
	private Double range_min;
	private Double range_max;
	private Double unitprice;
	private String created_by_useracc;
	private String created_by_username;
	private String creation_date;
	private Long last_updated_by_userid;
	private String last_updated_by_useracc;
	private String last_updated_by_username;
    private String last_update_date;
    private Long diancxxb_id;
    private int status;
    private Long created_by_userid;
    
    
    
    
	public Long getCreated_by_userid() {
		return created_by_userid;
	}
	public void setCreated_by_userid(Long created_by_userid) {
		this.created_by_userid = created_by_userid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPo_sub_id() {
		return po_sub_id;
	}
	public void setPo_sub_id(Long po_sub_id) {
		this.po_sub_id = po_sub_id;
	}
	public Long getPrice_component_id() {
		return price_component_id;
	}
	public void setPrice_component_id(Long price_component_id) {
		this.price_component_id = price_component_id;
	}
	public Long getQuality_item_id() {
		return quality_item_id;
	}
	public void setQuality_item_id(Long quality_item_id) {
		this.quality_item_id = quality_item_id;
	}
	public Double getRange_min() {
		return range_min;
	}
	public void setRange_min(Double range_min) {
		this.range_min = range_min;
	}
	public Double getRange_max() {
		return range_max;
	}
	public void setRange_max(Double range_max) {
		this.range_max = range_max;
	}
	public Double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
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
	public Long getDiancxxb_id() {
		return diancxxb_id;
	}
	public void setDiancxxb_id(Long diancxxb_id) {
		this.diancxxb_id = diancxxb_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
    
}
