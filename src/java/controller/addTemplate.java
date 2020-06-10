/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import clientModal.ClientImpl;
import clientModal.clientInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author 11141
 */
 @MultipartConfig(fileSizeThreshold=1024*1024,
    maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
 
 @WebServlet(
        name = "addTemplate", 
        urlPatterns = {"/addTemplate"}
    )
public class addTemplate extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addTemplate</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Some Error occured While uploading the template</h1>");
            out.println("<a href='MainContent.jsp'>Back to Page</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       String temName=request.getParameter("Tempname");
       String userID=request.getParameter("userID");
         
        Part part = request.getPart("customFile1");
         InputStream stream= part.getInputStream();
            InputStreamReader reader=new InputStreamReader(stream,StandardCharsets.UTF_8);
            
        BufferedReader br=new BufferedReader(reader);
         String line=  br.readLine();
            String content=null;
            while (line!=null)
            {                
             line=br.readLine();
             content+=line+'\n';
              }
            
 String strNew = content.replace("null", ""); // strNew is 'bcdDCBA123'
 
     byte[]  str= strNew.getBytes();
  String tempEncode=Base64.encodeBase64String(str);
         Random r=new Random();
        int ids=r.nextInt()*new Random().nextInt();
        clientInterface i=new ClientImpl();
        i.addTemplate(Long.parseLong(userID), ids, temName, tempEncode);
        
         response.sendRedirect("MainContent.jsp");

        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
