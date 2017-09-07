package com.zhiren.fuelmis.dc.controller.common;


import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiren.fuelmis.dc.utils.FileOperateUtil;
import com.zhiren.fuelmis.dc.utils.PropertiesUtil;

import com.zhiren.fuelmis.dc.utils.Sequence;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/upload")
    public void upload(HttpServletRequest request, HttpServletResponse response, String hetid) throws Exception {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        String base = PropertiesUtil.getValue("upload_file_folder");
        try {
            FileOperateUtil.uploadWithRealName(request, base);
            //-----------------------------------------
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = mRequest.getFileMap();
            String sql = "begin \n ";
            for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
                MultipartFile mFile = entry.getValue();
                String fileName = mFile.getOriginalFilename();
                sql += "delete from fujxx where mingc='" + fileName + "';\n";
                sql += "insert into fujxx (id,mingc,guanlid,luj,yewly,zhuangt) values (" + Sequence.nextId() + ",'" + fileName + "'," + hetid + ",'" + base + "','rl_ht_hetb',1);\n";
            }
            sql += "end;";
            jdbcTemplate.update(sql);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    @RequestMapping(value = "/downloadFileById")
    public void downloadFile(@RequestParam String fileId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Map<String,Object> fujxxMap=jdbcTemplate.queryForMap("select mingc,luj from fujxx where id="+fileId);
			String fujmc = fujxxMap.get("MINGC").toString();
            String path =fujxxMap.get("LUJ").toString()+fujmc;
            System.out.print("============文件路径==="+path+"=========================================");
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename="+ new String(fujmc.getBytes("UTF-8"), "ISO8859-1"));
            OutputStream toClient = response.getOutputStream();
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
    @RequestMapping(value = "/getFileList")
    public void getFileList(@RequestParam String guanlid,String yewly, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Map<String,Object> fujxxMap=jdbcTemplate.queryForMap("select mingc,luj from fujxx where guanlid="+guanlid+" and yewly='" +yewly+"'");
            String fujmc = fujxxMap.get("MINGC").toString();
            String path =fujxxMap.get("LUJ").toString()+fujmc;
            System.out.print("============文件路径==="+path+"=========================================");
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename="+ new String(fujmc.getBytes("UTF-8"), "ISO8859-1"));
            OutputStream toClient = response.getOutputStream();
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
