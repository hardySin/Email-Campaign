package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import clientModal.clientmodal;
import clientModal.clientInterface;
import clientModal.ClientImpl;

import java.util.Random;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author 11141
 */

@WebServlet(
        name = "clientAdd", 
        urlPatterns = {"/clientAdd"}
    )
public class clientAdd extends HttpServlet {

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
        String ClientName=request.getParameter("ClientName");
        String desc=request.getParameter("desc");
        String userID=request.getParameter("userID");
        String clientID=request.getParameter("clientID");
        String clientID2=request.getParameter("clientID2");
        String clientID3=request.getParameter("clientID3");
        
 
        if(clientID!=null)
        {       
                clientInterface c=new ClientImpl();
              int i=c.deleteClient(Integer.parseInt(clientID));
                System.out.println("clientID :"+clientID);
                if(i>0)
                {
                 response.setContentType("text/plain");
                response.getWriter().write("Done"); 
                 }
         }
        else if(clientID2!=null)
        {
            
         String clientName=request.getParameter("clientName");
        String Description=request.getParameter("Description");
                        System.out.println("here here in edit mode: "+Description);

           clientInterface c=new ClientImpl();
              int i=c.updateClient(Integer.parseInt(clientID2), clientName, Description);
                if(i>0)
                {
                    System.out.println("edit mode Completed:");
                  response.setContentType("text/plain");
                response.getWriter().write("Done"); 
                 }
         }
         else
        {
         System.out.println("desc desc:::"+ userID);
         clientmodal m=new clientmodal(); 
         m.setUserId(Long.parseLong(userID));
         Random r=new Random();
         m.setClientId(r.nextInt()*new Random().nextInt());
         m.setClientName(ClientName);
         m.setDescription(desc);
         clientInterface i=new ClientImpl();
         i.addClient(m);
         response.sendRedirect("MainContent.jsp");
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
