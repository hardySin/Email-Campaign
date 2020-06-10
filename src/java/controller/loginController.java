/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.codec.binary.Base64;
import registerModal.Interface;
import registerModal.registerImpl;

/**
 *
 * @author 11141
 */

@WebServlet(
        name = "loginController", 
        urlPatterns = {"/loginController"}
    )

public class loginController extends HttpServlet {

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
            out.println("<title>Servlet loginController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginController at " + request.getContextPath() + "</h1>");
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
  String username=request.getParameter("usrName");
  String password=request.getParameter("usrPass");
  String dbPass=null;
          Interface i= new registerImpl();
           
           
           dbPass=  i.ValidateUser(username);
           if(dbPass==null)
           {
               RequestDispatcher rd=request.getRequestDispatcher("LoginPage.jsp");  
                request.setAttribute("notExit", "NotExits");
                rd.forward(request, response);
            }
           else
           {
                 if(dbPass.split(":")[0].equalsIgnoreCase(password))
                    {
                              HttpSession session = request.getSession(true);
                              System.out.println("here here :"+dbPass.split(":")[1]+" "+dbPass.split(":")[2] );
                              session.setAttribute("username", dbPass.split(":")[1]);
                              session.setAttribute("userId", dbPass.split(":")[2]);

                              response.sendRedirect("MainContent.jsp");
                    }
                 else
                 {
                     RequestDispatcher rd=request.getRequestDispatcher("LoginPage.jsp");  
                request.setAttribute("password", "false");
                rd.forward(request, response);

                 }
           }
  //  byte[]  str= password.getBytes();
//  String passEncode=Base64.encodeBase64String(str);
//  byte[] passDeocde=Base64.decodeBase64(passEncode);
//  String newPass=new String(passDeocde);
//  System.out.println("here :::"+ username +" "+ password+" "+ passEncode+" "+  newPass);
       
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
