package com.zhiren.fuelmis.dc.utils.formular;
import java.util.List;
import java.util.Map;
public class Huayz {
	public static void calculateHuayz(Map<String,Object> map){
		calculateHuayz(map,null);
	}
	public static void calculateHuayz(Map<String, Object> map,List<String> columNamesList) {
		if (map.get("MT") != null && map.get("MAD") != null
				&& map.get("AAD") != null && map.get("VAD") != null
				&& map.get("STAD") != null && map.get("HAD") != null
				&& map.get("QBAD") != null) {
			double A_d = 0;
			double A_ar = 0;
			double Qgr_ad = 0;
			double Qnet_ar = 0;
			double FC_ad = 0;
			double V_daf = 0;
			double St_d = 0;
			double S_daf = 0;
			double H_daf = 0;
			double Qgr_daf = 0;
			double H_ar = 0;
			double Qgr_d = 0;
			
			Double Mt_ar = Double.parseDouble(map.get("MT").toString());
			
			Double Mt_ad = Double.parseDouble(map.get("MAD").toString());
			Double A_ad = Double.parseDouble(map.get("AAD").toString());	
			Double V_ad = Double.parseDouble(map.get("VAD").toString());
			Double St_ad = Double.parseDouble(map.get("STAD").toString());
			Double H_ad = Double.parseDouble(map.get("HAD").toString());
			Double Qb_ad = Double.parseDouble(map.get("QBAD").toString());//保留两位小数
			/*Qb_ad=round_new(Qb_ad,2);
			map.put("QBAD",Qb_ad);
			Double A_d = A_ad * 100 / (100 - Mt_ad);
			map.put("AD", round_new(A_d,2));
			Double A_ar = A_ad * (100 - Mt_ar) / (100 - Mt_ad);
			map.put("AAR", round_new(A_ar,2));*/
			Double V_d = V_ad * 100 / (100 - Mt_ad);
			/*map.put("VD", round_new(V_d,2));
			Double V_daf = V_ad * 100 / (100 - Mt_ad - A_ad);
			map.put("VDAF", round_new(V_daf,2));*/
			Double V_ar = V_ad * (100 - Mt_ar) / (100 - Mt_ad);
			/*map.put("VAR", round_new(V_ar,2));
			Double FC_ad = 100 - Mt_ad - A_ad - V_ad;
			map.put("FCAD", round_new(FC_ad,2));*/
			Double FC_d = 100 - A_d - V_d;
			/*map.put("FCD", round_new(FC_d,2));*/
			Double FC_ar = 100 - A_ar - V_ar - Mt_ar;
			/*map.put("FCAR", round_new(FC_ar,2));
			Double Qgr_ad = (Qb_ad * 1000 - 94.1 * St_ad - 0.0012 * Qb_ad * 1000) / 1000;
			map.put("QGRAD", round_new(Qgr_ad,2));
			Double Qgr_d = Qgr_ad * 100 / (100 - Mt_ad);
			map.put("QGRD", round_new(Qgr_d,2));
			Double Qgr_daf = Qgr_ad * 100 / (100 - Mt_ad - A_ad);
			map.put("QGRAD_DAF", round_new(Qgr_daf,2));*/
			Double Qgr_ar = Qgr_ad * (100 - Mt_ar) / (100 - Mt_ad);
			/*map.put("QGRAR", round_new(Qgr_ar,2));
			Double Qnet_ar = ((Qgr_ad * 1000 - 206 * 2.98) * (100 - Mt_ar)
					/ (100 - Mt_ad) - 23 * Mt_ar) / 1000;
			map.put("QNET_AR", round_new(Qnet_ar,2));
			Double St_d = St_ad * 100 / (100 - Mt_ad);
			map.put("STD", round_new(St_d,2));
			Double St_daf = St_ad * 100 / (100 - Mt_ad - A_ad);
			map.put("STDAF", round_new(St_daf,2));
			Double H_daf = H_ad * 100 / (100 - Mt_ad - A_ad);
			map.put("HDAF", round_new(H_daf,2));*/
			
			
			/*map.put("STAR", round_new(St_ar,2));
			Double H_ar=(H_daf*(100-Mt_ar-A_ar))/100;
			map.put("HAR", round_new(H_ar,2));*/
			
	
			double dblA = 0;

			if (Mt_ad == 100) {
				A_d = 0.0;
			} else {
				A_d = round_new(A_ad * 100 / (100 - Mt_ad), 2);
			}

			if (Mt_ad == 100) {
				St_d = 0.0;
			} else {
				St_d = round_new(St_ad * 100 / (100 - Mt_ad), 2);
			}
			double St_daf = round_new((100 / (100 - A_d)) * St_d, 2);

			St_ad = round_new((100 - Mt_ad) * St_d / 100, 2);
			St_daf = round_new((100 / (100 - A_d)) * St_d, 2);

			if (Mt_ad == 100) {
				St_d = 0.0;
			} else {
				St_d = round_new((100 - A_d) / 100 * St_daf, 2);
			}
			St_ad = round_new((100 - Mt_ad) * St_d / 100, 2);
			Double St_ar=St_d*(100-Mt_ar)/100;
			
			if ((100 - Mt_ad - A_ad) == 0) {
				H_daf = 0.0;
			} else {
				H_daf = round_new(H_ad * 100 / (100 - Mt_ad - A_ad), 2);
			}

			H_ad = round_new(((100 - Mt_ad - A_ad) * H_daf / 100), 2);

			if (Qb_ad < 16.72) {
				dblA = 0.001;
			} else {
				if (Qb_ad >= 16.72 && Qb_ad <= 25.1) {
					dblA = 0.0012;
				} else {
					dblA = 0.0016;
				}
			}
			Qgr_ad = round_new((Qb_ad - (0.0941 * St_ad + dblA * Qb_ad)), 3);
			if (Mt_ad == 100) {
				Qnet_ar = -23.0;
				A_d = 0.0;
				A_ar = 0.0;
			} else {
				Qnet_ar = round_new(((Qgr_ad - 0.206 * H_ad) * (100 - Mt_ar)
						/ (100 - Mt_ad) - 0.023 * Mt_ar), 3);
				A_d = round_new((A_ad * 100 / (100 - Mt_ad)), 2);
				A_ar = round_new((A_ad * (100 - Mt_ar) / (100 - Mt_ad)), 2);
				FC_ad = round_new((100 - A_ad - V_ad - Mt_ad), 2);
			}
			if ((100 - Mt_ad - A_ad) == 0) {
				V_daf = 0.0;
			} else {
				V_daf = round_new((V_ad * 100 / (100 - Mt_ad - A_ad)), 2);
			}
			Qgr_daf = round_new((100 / (100 - Mt_ad - A_ad) * Qgr_ad), 3);
			Qgr_d = round_new(Qgr_ad * 100 / (100 - Mt_ad), 3);
			H_ar = round_new(H_ad * (100 - Mt_ar) / (100 - Mt_ad), 2);
			
			map.put("QBAD",Qb_ad);
			map.put("AD", round_new(A_d,2));
			map.put("AAR", round_new(A_ar,2));
			map.put("VD", round_new(V_d,2));
			map.put("VDAF", round_new(V_daf,2));
			map.put("VAR", round_new(V_ar,2));
			map.put("FCAD", round_new(FC_ad,2));
			map.put("FCD", round_new(FC_d,2));
			map.put("FCAR", round_new(FC_ar,2));
			map.put("QGRAD", round_new(Qgr_ad,2));
			map.put("QGRD", round_new(Qgr_d,2));
			map.put("QGRAD_DAF", round_new(Qgr_daf,2));
			map.put("QGRAR", round_new(Qgr_ar,2));
			map.put("QNET_AR", round_new(Qnet_ar,2));
			map.put("STD", round_new(St_d,2));
			map.put("STDAF", round_new(St_daf,2));
			map.put("HDAF", round_new(H_daf,2));
			map.put("STAR", round_new(St_ar,2));
			map.put("HAR", round_new(H_ar,2));	
		}else{
			if(columNamesList!=null){
				for (int i = 0; i < columNamesList.size(); i++) {
					String columName=columNamesList.get(i);
					if(!map.containsKey(columName)){
						map.put(columName, "");
					}
				}
			}
		}
	}
	public static void round_new(Map<String,Object> map){
		String[] column = {"MT","MAD","AAD","AD","AAR","VAD","VDAF","QNET_AR","SDAF","STAD","STD","STAR","HDAF","HAD","FCAD"};
		for (int i = 0; i < column.length; i++) {
			String key=column[i];
			if(map.get(key)!=null){
				double value=Double.parseDouble(map.get(key).toString());
				if(!column[i].equals("MT")){
					map.put(key,round_new(value,2));
				}else{
					map.put(key,round_new(value,1));
				}
			}
		}
	
	}
	public static Double round_new(Double Value0 ,int Bit0){
		    Double Value1 =0.0;
		    Double Value2=0.0;

		    if( Value0 ==0){
		    	 return 0.0;
		    }else{

			    Value1 = Math.floor(Value0 * Math.pow(10, Bit0)) - Math.floor(Value0 * Math.pow(10, Bit0 - 1)) * 10;
			    Value2 = Value0 * Math.pow(10, Bit0);

			    if(((Value2 - Math.floor(Value0 * Math.pow(10, Bit0))) >= 0.5) &&((Value2 - Math.floor(Value0 * Math.pow(10, Bit0))) < 0.6)) {
				      if ((Value2 - Math.floor(Value0 *Math.pow(10, Bit0))) == 0.5) {
				        if (Value1 == 0 || Value1 == 2 || Value1 == 4 || Value1 == 6 ||Value1 == 8 ){
				          return Math.floor(Value0 * Math.pow(10, Bit0)) / Math.pow(10, Bit0);
				        }else{
				          return(Math.floor(Value0 * Math.pow(10, Bit0)) + 1) / Math.pow(10, Bit0);
				        }
				      }else{
				        return Math.round(Value0 * Math.pow(10, Bit0)) / Math.pow(10, Bit0);
				      }
			    }else{
			      return Math.round(Value0 * Math.pow(10, Bit0)) / Math.pow(10, Bit0);
			    }
		    }     
	}
}