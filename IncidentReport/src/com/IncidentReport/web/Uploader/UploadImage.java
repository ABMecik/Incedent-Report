package com.IncidentReport.web.Uploader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Part;

public class UploadImage {
	
	public UploadImage(){
		
	}
	
	
	/**
	 * @param part
	 * @param date
	 * @param appPath
	 * @return
	 * @throws IOException
	 */
	public String UploadNewImage(Part part, Date date, String appPath) throws IOException {
		String dbPath;
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = "" + sd.format(date).toString();
		
		String imgPath = "uploads" + File.separator + "images" + File.separator + "evidance" + File.separator;
		
		String savePath = (appPath +imgPath + fileName.trim() + part.getSubmittedFileName().trim());
		
		dbPath = imgPath + fileName.trim() + part.getSubmittedFileName().trim();
		
		File file = new File(savePath);
		file.getParentFile().mkdirs();
		part.write(savePath);
		System.out.println(savePath);
		return dbPath;
	}

}
