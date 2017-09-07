package com.zhiren.fuelmis.dc.entity.gongyspg.pingggl;
import bsh.EvalError;
import bsh.Interpreter;

public class Calculator {
	private double dblCf;
	private double dblKf;
	private double dblBz;
	private String sFormula="";
	private double dblResult=0;
	
	public Calculator(){
		
	}
	public Calculator(double dblCf,double dblKf,double dblBz,String sFormula){
		this.dblCf=dblCf;
		this.dblKf=dblKf;
		this.dblBz=dblBz;
		this.sFormula=sFormula;
	}
	
	public void  setCf(double dblCf){
		this.dblCf=dblCf;
	}
	
	public void  setKf(double dblKf){
		this.dblKf=dblKf;
	}
	
	public void setSetBz(double dblBz){
		this.dblBz=dblBz;
	}
	
	public void setFormula(String sFormula){
		this.sFormula=sFormula;
	}
	
	public double getResult(){
		return this.dblResult;
	}
	
	public boolean Compute(){
		boolean blnIsSuc=false;
		Interpreter bsh;
		try {
			bsh = new Interpreter();
			bsh.set("CF",this.dblCf );
			bsh.set("KF", this.dblKf);
			bsh.set("指标标准分", this.dblBz);
			bsh.eval(this.sFormula);
			this.dblResult= Double.parseDouble(bsh.get("result").toString());
			blnIsSuc=true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (EvalError e) {
			e.printStackTrace();
		}
		bsh = null;
		return blnIsSuc;
	}
}
