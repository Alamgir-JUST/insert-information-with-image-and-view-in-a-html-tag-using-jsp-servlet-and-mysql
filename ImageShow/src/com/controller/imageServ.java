package com.controller;

import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.util.DBConnection;

@WebServlet("/imageServ")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)

public class imageServ extends HttpServlet {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String UPLOAD_DIR = "images";
    public String dbFileName = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pst = null;
        
        String pictureId = request.getParameter("pictureId");
        String name = request.getParameter("name");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");	
        Part part = request.getPart("picture");//
        String fileName = extractFileName(part);//file name
        System.out.println("fileName:"+fileName);//wait
        /**
         * *** Get The Absolute Path Of The Web Application ****
         */
        String applicationPath = getServletContext().getRealPath("");
        String uploadPath = applicationPath+ UPLOAD_DIR;
        System.out.println("applicationPath:" + applicationPath);
        File fileUploadDirectory = new File(uploadPath);
        if (!fileUploadDirectory.exists()) {
            fileUploadDirectory.mkdirs();
        }
        String savePath = uploadPath + File.separator + fileName;
        System.out.println("savePath: " + savePath);
        String sRootPath = new File(savePath).getAbsolutePath();
        System.out.println("sRootPath: " + sRootPath);
        part.write(savePath + File.separator);
        File fileSaveDir1 = new File(savePath);
        /*if you may have more than one files with same name then you can calculate some random characters
         and append that characters in fileName so that it will  make your each image name identical.*/
        dbFileName = UPLOAD_DIR + File.separator + fileName;
        part.write(savePath + File.separator);
        //out.println(id+" "+firstName+" "+lastName+" "+fileName+" "+savePath);
        /*
         You need this loop if you submitted more than one file
         for (Part part : request.getParts()) {
         String fileName = extractFileName(part);
         part.write(savePath + File.separator + fileName);
         }*/
        try {
            conn = DBConnection.createConnection();
            String sql = "insert into tbl_student(pictureId,name,email,mobile,picture) values (?,?,?,?,?)";
			
            pst = conn.prepareStatement(sql);
			pst.setString(1, pictureId);
			pst.setString(2, name);
			pst.setString(3, email);
			pst.setString(4, mobile);
			pst.setString(5, dbFileName);
            int i = pst.executeUpdate();
            if(i!=0) {
            	out.println("<center><h1>Information inserted Succesfully......</h1></center>");
            	request.getRequestDispatcher("viewImage.jsp").forward(request, response);
            }
            else {
            	out.println("<center><h1>Information not inserted Succesfully......</h1></center>");
            }
            
            //out.println("<center><a href='display.jsp?id=" + id + "'>Display</a></center>");
        } catch (Exception e) {
            out.println(e);
        }
        
    }
    // file name of the upload file is included in content-disposition header like this:
    //form-data; name="dataFile"; filename="PHOTO.JPG"

    private String extractFileName(Part part) {//This method will print the file name.
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
