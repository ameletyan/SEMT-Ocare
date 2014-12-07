/** @mainpage SEMT-OCare
 * @authors codejava.net
 * <hr>
 * Modifiers: Bryan Connelly, Johnlee Alvarez
 * <hr>
 * @date 12/9/2014
 * <hr>
 * \n
 * @section intro Java Servlets
 *  - These Servlets are what allow Patients/Doctors to 
 *  upload and download the records.
 *  - We determine whether or not you are a doctor or patient based 
 *  on your login info
 * \n
 * <hr>
 */

package net.codejava.upload;
 
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 
/**
 * A Java servlet that handles file upload and download from client.
 * @author www.codejava.net
 * <hr>
 * Modifiers: Bryan Connelly, Johnlee Alvarez
 * <hr>
 */
public class Servlet3 extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
 
    /*!
     * - This method handles file upload via HTTP POST method
     * - The upload path is determined as a location on local disk
     * - Allows you to select which file you want to upload
     *   with drop-down menu
     * @return Message stating if the upload was successful
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Request does not contain upload data");
            writer.flush();
            return;
        }
         
        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(THRESHOLD_SIZE);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
         
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
         
        // constructs the directory path to store upload file
        String uploadPath = "/Users/JackAlvarez/Documents/School/Fall 2014/CSCI 3308/Project/UploadServletApp/UploadRepo/3";
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
         
        try {
            // parses the request's content to extract file data
            List formItems = upload.parseRequest(request);
            Iterator iter = formItems.iterator();
             
            // iterates over form's fields
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                // processes only fields that are not form fields
                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadPath + File.separator + fileName;
                    File storeFile = new File(filePath);
                     
                    // saves the file on disk
                    item.write(storeFile);
                }
            }
            request.setAttribute("message", "Upload has been done successfully!");
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        }
        getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
    }
    
    /*!
     * - This method handles file upload via HTTP GET method
     * - Download path is set from an absolute path on Local Disk
     * - With more time we would have implemented a drop-down menu
     *   to select which file you wanted to download
     * @return The file that is stored in the Download path
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads input file from an absolute path
        String filePath = "/Users/JackAlvarez/Documents/School/Fall 2014/CSCI 3308/Project/UploadServletApp/UploadRepo/3/Text3.txt";
        File downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);
         
        // if you want to use a relative path to context root:
        String relativePath = getServletContext().getRealPath("");
        System.out.println("relativePath = " + relativePath);
         
        // obtains ServletContext
        ServletContext context = getServletContext();
         
        // gets MIME type of the file
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {        
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
         
        // modifies response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
         
        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
         
        // obtains response's output stream
        OutputStream outStream = response.getOutputStream();
         
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
         
        inStream.close();
        outStream.close();     
    }
}