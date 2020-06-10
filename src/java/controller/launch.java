/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import clientModal.ClientImpl;
import clientModal.clientInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author 11141
 */

@WebServlet(
        name = "launch", 
        urlPatterns = {"/launch"}
    )
public class launch extends HttpServlet {

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
            out.println("<title>Servlet launch</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet launch at " + request.getContextPath() + "</h1>");
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
                 String userID=request.getParameter("userID");
         String emailfrom=request.getParameter("emailfrom");
         String sendername=request.getParameter("sendername");
         String emailto=request.getParameter("emailto");
         String recname=request.getParameter("recname");
         
         
         System.out.print("user user user  :"+userID+" "+emailfrom+" "+sendername+" "+emailto+" "+recname+" ");
         HttpSession s= request.getSession(false);
        if(s!=null)
        {
             int campID=(int)s.getAttribute("campID");
             List <String> list=(List)s.getAttribute("subList");
            System.out.println("here session scope value :"+campID+" "+list);
 
                 clientInterface i=new ClientImpl();
                 String content=i.getSelectedTemp(campID);
                String emailPass= i.getEmailPass(Long.parseLong(userID));
                 System.out.println("emailPass :"+emailPass+" content "+ content);
                  String[] context = emailPass.split(":"); 

                    byte[] passDeocde=Base64.decodeBase64(context[1]);
                   String password=new String(passDeocde);
                   
                    byte[] tempDeocde=Base64.decodeBase64(content);
                   String temp=new String(tempDeocde);

                 if(content!=null && emailPass!=null)
                 {
                     for(String sub:list)
                     {
                      System.out.println("not null here");
 
                      i.sendEmail(context[0], password, temp, sub, emailto);
                      }
                  }
                 else
                 {
                   response.setContentType("text/plain");
                   response.getWriter().write("error"); 

                 }
        }
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
