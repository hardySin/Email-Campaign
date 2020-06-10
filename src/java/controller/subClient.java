/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import subClient.subClientInter;
import subClient.subClientmodal;
import subClient.subclientImpl;

/**
 *
 * @author 11141
 */
@WebServlet(
        name = "subClient", 
        urlPatterns = {"/subClient"}
    )
public class subClient extends HttpServlet {

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
                 String clientID3=request.getParameter("clientID3");
                  String subClientID=request.getParameter("subClientID");
                    String updateSid=request.getParameter("updateSid");
                    
                  List<subClientmodal> c1=null;
                 if(clientID3!=null)
                 {
                  System.out.println("clientID3lololololoo:: "+clientID3);
                   subClientInter i=new subclientImpl();
                      c1= i.subclients(Integer.valueOf(clientID3));
                     if(c1!=null)
                     {
                        JSONArray array=new JSONArray();
                         for(subClientmodal c:c1)
                     {
                         JSONObject j=new JSONObject();   
                         j.put("subclientID", c.getSubclientId());
                         j.put("programName", c.getProgramName());
                         j.put("description", c.getDescription());
                         array.put(j);
                      System.out.println("result All:: "+c.getSubclientId()+" "+ c.getProgramName()+" "+ c.getDescription());
                     }
                                 response.setContentType("application/json");
                                    PrintWriter out = response.getWriter();
                                     out.write(array.toString());
                      }
                     else
                     {
                         System.out.println("c1 Is null");
                     }
                    }
                 else if(subClientID!=null)
                 {
                     System.out.println("here comes in the delete Section");
                     subClientInter i=new subclientImpl();
                      int val= i.deleteSubClient(Integer.parseInt(subClientID));
                      if(val>0)
                      {
                          System.out.println("delete done");
                            response.setContentType("text/plain");
                             PrintWriter out = response.getWriter();
                            out.write("Done");
                       }
                   }
                 else if(updateSid!=null)
                 {
                     System.out.println("here come in the update part");
                    String EditProgName=request.getParameter("EditProgName");
                    String pdesc=request.getParameter("pdesc");
  
                        subClientmodal c=new subClientmodal();
                      c.setProgramName(EditProgName);
                     c.setDescription(pdesc);
                     c.setSubclientId(Integer.valueOf(updateSid));    

                    subClientInter i=new subclientImpl();
                     subClientmodal up= i.updatesubClient(c);
                     if(up!=null)
                     {
                         JSONArray array=new JSONArray();
                         JSONObject j=new JSONObject();   
                         j.put("subclientID", up.getSubclientId());
                         j.put("programName", up.getProgramName());
                         j.put("description", up.getDescription());
                         array.put(j);
                         
                         response.setContentType("application/json");
                                    PrintWriter out = response.getWriter();
                                     out.write(array.toString());
                      }
                  }
                 else
                 {
                 String clientID=request.getParameter("clientID");
                 String ProgName=request.getParameter("ProgName");
                 String progdesc=request.getParameter("progdesc");
                 System.out.println("clientID3:: "+clientID+" "+ ProgName+" "+ progdesc);

                 int subclientID=new Random().nextInt();
                     subClientmodal c=new subClientmodal();
                     c.setClientId(Integer.valueOf(clientID));
                     c.setProgramName(ProgName);
                     c.setDescription(progdesc);
                     c.setSubclientId(subclientID);
                    
                     JSONArray array=new JSONArray();
  
                     subClientInter i=new subclientImpl();
                    subClientmodal c2= i.addSubClient(c);
                    System.out.println("results:: "+c2.getProgramName()+" "+ c2.getDescription()+" "+ c2.getSubclientId());
                        JSONObject j=new JSONObject();   
                         j.put("subclientID", c2.getSubclientId());
                         j.put("programName", c2.getProgramName());
                         j.put("description", c2.getDescription());
                         array.put(j);
                         
                         response.setContentType("application/json");
                                    PrintWriter out = response.getWriter();
                                     out.write(array.toString());

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
