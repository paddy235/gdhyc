package com.zhiren.fuelmis.dc.service.hetgl;

import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhiren.fuelmis.dc.entity.hetgl.Hetbean;

public interface HetbeanService {

	public JSONArray addHetbean(Hetbean hetbean, JSONObject subs, Long diancxxb_id);

	public JSONArray updateHetbean(Hetbean hetbean, JSONObject subs,Long diancxxb_id);

	public JSONObject getHetbeanList(Map<String,Object> map);

	public JSONObject getHetbeaninfoList(String strdate, String enddate,
			String diancid);

	public JSONArray delHetbean(Map<String, Object> map);

	public JSONObject getHetbeanById(Map<String, Object> map);

	public JSONArray getCaigddbById(Map<String, Object> map);

	public JSONObject getGongysById(String gongysid);

	public JSONObject getDiancxxById(String id);

	public JSONArray getCaigddbsub(JSONArray subs);

	public JSONArray createDoc(String id);

	public HWPFDocument getHWPFDocument(String id);

	public byte[] getbytes(String id);

	public Map<String, String> getPmapMap(String id);

	public void updateHetbeanSanjzt(String id);

	public JSONArray getCaigddbList(Map<String,Object> map);

	public JSONArray getKaohzbbList(Map<String, Object> map);

	public JSONObject getCaigddb(Map<String, Object> map);

	public JSONArray getRlhtmbList(Map<String, Object> map);

	String getHetbh(String hetbh);
}
