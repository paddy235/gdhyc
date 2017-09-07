package com.zhiren.fuelmis.dc.entity.hetgl;

/**
 * 
 * @author ZY
 *
 */
public class PriceBean {
	public String type;
	
	public double priceChange; // --不确定
	public String rangeMax; // 指标上限
	public String rangeMin; // 指标下限
	public boolean isSuccess = false;
	public String errMsg = ""; // 操作提示
	
	public double hetjg; // 合同价格
	public String zhib; // 结算指标
	public double hetjz; // 合同基准	
	public double zhibValue; //指标值	
	public double chaz; // 相差数量
	public double price; // 实际价格
	public String formula; // 价格公式
	

	public String getZhib() {
		return zhib;
	}

	public void setZhib(String zhib) {
		this.zhib = zhib;
	}
	
	public double getChaz() {
		return chaz;
	}

	public void setChaz(double chaz) {
		this.chaz = chaz;
	}
	
	public String getType() {
		return type;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public double getHetjg() {
		return hetjg;
	}

	public void setHetjg(double hetjg) {
		this.hetjg = hetjg;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPriceChange() {
		return priceChange;
	}

	public void setPriceChange(double priceChange) {
		this.priceChange = priceChange;
	}

	public String getRangeMax() {
		return rangeMax;
	}

	public void setRangeMax(String rangeMax) {
		this.rangeMax = rangeMax;
	}

	public String getRangeMin() {
		return rangeMin;
	}

	public void setRangeMin(String rangeMin) {
		this.rangeMin = rangeMin;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public void setType(String type) {
		this.type = type;
	}
	public double getHetjz() {
		return hetjz;
	}

	public void setHetjz(double hetjz) {
		this.hetjz = hetjz;
	}

	public double getZhibValue() {
		return zhibValue;
	}

	public void setZhibValue(double zhibValue) {
		this.zhibValue = zhibValue;
	}
	
}
