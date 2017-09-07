package com.zhiren.fuelmis.dc.utils.testExportExcel;

import com.zhiren.fuelmis.dc.utils.FileUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class MakeHtml {
    private static long star = 0;
    private static long end = 0;
    private static long ttime = 0;
    public static String html="";
    //	返回html代码
    private static String getHtmlCode(String httpUrl)//,int i, int j)
    {
        Date before = new Date();
        star = before.getTime();
        StringBuffer htmlCode = new StringBuffer();
        try {
            InputStream in;
            URL url = new java.net.URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/4.0");
            connection.connect();
            in = connection.getInputStream();
            java.io.BufferedReader breader = new BufferedReader(new InputStreamReader(in, "GBK"));
            String currentLine;
            while ((currentLine = breader.readLine()) != null) {
                htmlCode.append(currentLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Date after = new Date();
            end = after.getTime();
            ttime = end - star;
            System.out.println("执行时间:" + ttime + "毫秒");
        }
        return htmlCode.toString();
    }

    //	存储文件
    public static synchronized void writeHtml(String filePath, String info, String flag) {
        PrintWriter pw = null;
        try {
            File writeFile = new File(filePath);
            boolean isExit = writeFile.exists();
            if (isExit != true) {
                writeFile.createNewFile();
            } else {
                if (!flag.equals("NO")) {
                    writeFile.delete();
                    writeFile.createNewFile();
                }
            }
            pw = new PrintWriter(new FileOutputStream(filePath, true));
            pw.println(info);
            pw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            pw.close();
        }
    }

    public static void main(String[] args) {

//		for (int i = 0; i < 20; i++) {
//
//			for (int j = 0; j < 20; j++) {
//
//				// System.out.println("j =" +j+": " +39.0183+(0.03409 * j));
//				// }
//				// System.out.println("i =" +i+": " +121.417323+(0.04380 * i));
//				// }
////				System.out
////						.println("http://maps.google.com/maps/api/staticmap?center="
////								+ (39.0183
////								- (0.03409 * j))
////								+ ","
////								+ (121.417323
////								+ (0.04380 * i))
////								+ "&zoom=14&size=512x512&maptype=roadmap&format=png&sensor=false");
//
//				String url = "http://maps.google.com/maps/api/staticmap?center="
//							+ (39.0183
//							- (0.03409 * j))
//							+ ","
//							+ (121.417323
//							+ (0.04380 * i))
//							+ "&zoom=14&size=512x512&maptype=roadmap&format=png&sensor=false";
//
//				writeHtml("E:/bat/sina.htm",getHtmlCode(url,i,j),"NO");
//			}
//			System.out.println("==============================");
//
//		}
//        String url = "http://localhost:8080/fuelmis/dc/logon#/jiestz";
//        writeHtml("E:/sina.html", getHtmlCode(url), "NO");
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
            HSSFRow row1 = sheet.createRow(1);
            HSSFCell cell1 = row1.createCell(0);
            File f = new File("E:/sina.html");
            byte[] b = FileUtil.getBytesFromFile(f);
//            InputStream inStream = new FileInputStream("E:/sina.html"); // 读入原文件
//            FileOutputStream fs = new FileOutputStream("E:/x.xls");
//            byte[] buffer = new byte[1444];
//            int length;
//            int bytesum = 0;
//            int byteread = 0;
//            while ((byteread = inStream.read(buffer)) != -1) {
//                bytesum += byteread; // 字节数 文件大小
//                System.out.println(bytesum);
//                fs.write(buffer, 0, byteread);
//            }
//            inStream.close();
//            cell1.setCellValue(b);
            FileOutputStream fileOut = new FileOutputStream("E:/sina.xls");
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeExcel(String s,String filePath) {
        try {
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet
//            sheet.
//            HSSFRow row1 = sheet.createRow(1);
//            HSSFCell cell1 = row1.createCell(0);
//            cell1.setCellValue(s);
//            FileOutputStream fileOut = new FileOutputStream("E:/sina.xls");
//            workbook.write(fileOut);
//            fileOut.close();

            String xml = s;
            byte[] bytexml = xml.getBytes();
            OutputStream os = new FileOutputStream(new File(filePath));
            os.write(bytexml);
            os.flush();
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//	public static void createHtml(String filePath,String url,String flag) {
//		writeHtml(filePath,getHtmlCode(url,),flag);
//	}

