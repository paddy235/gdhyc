
package com.zhiren.fuelmis.dc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * Properties工具类.
 *
 * @author 陈宝露
 * @version 1.0.0
 *
 */
public class PropertiesUtil {
    private static final String FILE_PATH = "/com/zhiren/fuelmis/config/properties.properties";
    private static Properties pro = new Properties();
    private static InputStream inputStream = null;
    private static InputStreamReader reader = null;
    private static Map<String, String> maps = new HashMap<String, String>();

    /**
     * 获取properties文件的value值.
     * @param
     * @return value.
     */
    public static String getValue(String key) {
        return maps.get(key);
    }

    static {
        load();
    }

    /**
     *
     */
    private static void load() {
        try {
            inputStream = PropertiesUtil.class.getResourceAsStream(FILE_PATH);
            reader = new InputStreamReader(inputStream, "utf-8");
            pro.load(reader);
            Set<Entry<Object, Object>> set = pro.entrySet();
            Iterator<Entry<Object, Object>> it = set.iterator();
            while (it.hasNext()) {
                Entry<Object, Object> entry = it.next();
                String key = entry.getKey().toString().trim();
                String value = entry.getValue().toString().trim();
                maps.put(key, value);
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                reader.close();
                inputStream.close();
                pro.clear();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
