package com.zhiren.fuelmis.dc.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 陈宝露
 */
public class FileUtil {
	public static String[] UploadFile(MultipartFile file) {
		String path = PropertiesUtil.getValue("upload_file_folder");
		String[] str = new String[2];
		//String id = String.valueOf(System.currentTimeMillis());
		String name = file.getOriginalFilename();
		File targetFile = new File(path, name);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
			//str[0] = id;
			str[1] = name;
			return str;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

		return null;
	}
	public static byte[] getFileStream(File f) throws IOException {
		InputStream is = null;  
    	is = new BufferedInputStream(new FileInputStream(f));  
        long contentLength = f.length();  
        ByteArrayOutputStream outstream = new ByteArrayOutputStream( contentLength > 0 ? (int) contentLength : 1024);  
        byte[] buffer = new byte[24096];  
        int len;  
        while ((len = is.read(buffer)) > 0) {  
            outstream.write(buffer, 0, len);  
        }              
        outstream.close();  
        is.close();
        byte[] FileData = outstream.toByteArray(); 
        return FileData;
	}
	
	@SuppressWarnings("resource")
	public static byte[] getBytesFromFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);

	    // Get the size of the file
	    long length = file.length();

	    // You cannot create an array using a long type.
	    // It needs to be an int type.
	    // Before converting to an int type, check
	    // to ensure that file is not larger than Integer.MAX_VALUE.
	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }

	    // Create the byte array to hold the data
	    byte[] bytes = new byte[(int)length];

	    // Read in the bytes
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }

	    // Ensure all the bytes have been read in
	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }

	    // Close the input stream and return bytes
	    is.close();
	    return bytes;
	}
	
	public static void writeFile(byte[] FileData, File f) throws IOException {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
			bos.write(FileData);
			bos.close();
	}

}
