/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import campaign.campaignImpl;
import campaign.campaignModal;
import campaign.campiagnInter;
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

/**
 *
 * @author 11141
 */

@WebServlet(
        name = "campiagn", 
        urlPatterns = {"/campiagn"}
    )
public class campiagn extends HttpServlet {

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
                String subclientID=request.getParameter("subclientID"); 
                 String deleteCampID=request.getParameter("deleteCampID"); 
                    
                 String updateCampID=request.getParameter("updateCampID"); 
                           
                List<campaignModal> c1=null;
                if(subclientID!=null)
                {
                   System.out.println("subClientID:: "+subclientID);
                    campiagnInter i=new campaignImpl();
                      c1= i.getAllCampaign(Integer.parseInt(subclientID));
                      if(c1!=null)
                     {
                         JSONArray array=new JSONArray();
                         for(campaignModal c:c1)
                     {
                         JSONObject  j=new JSONObject();   
                         j.put("campiagnID", c.getCampiagnID());
                         j.put("campiagnName", c.getCampiagnName());
                         j.put("description", c.getDescription());
                         j.put("region", c.getRegion());
                         j.put("status", c.getStatus());
                         j.put("templateId", c.getTemplateId());
                          array.put(j);
                      System.out.println("result All:: "+c.getCampiagnID()+" "+ c.getCampiagnName()+" "+ c.getDescription());
                     }
                                 response.setContentType("application/json");
                                    PrintWriter out = response.getWriter();
                                     out.write(array.toString());
                      }

                }
                else if(updateCampID!=null)
                {
                             
                   String editCampName=request.getParameter("editCampName");
                  String editcampdesc=request.getParameter("editcampdesc");
                  String editregion=request.getParameter("editregion");
                  String editstatus=request.getParameter("editstatus");
                  String selecttemplate=request.getParameter("selecttemplate");

                    System.out.println("here the ID comes :"+Integer.parseInt(selecttemplate));
                     
                      campaignModal c=new campaignModal();
                      c.setCampiagnID(Integer.valueOf(updateCampID));
                      c.setCampiagnName(editCampName);
                      c.setDescription(editcampdesc);
                      c.setRegion(editregion);
                      c.setStatus(editstatus);
                      c.setTemplateId(Integer.parseInt(selecttemplate));
 
                    campiagnInter i=new campaignImpl();
                     campaignModal up= i.updateCampaign(c);
                     if(up!=null)
                     {
                         JSONArray array=new JSONArray();
                         JSONObject  j=new JSONObject();   
                         j.put("campiagnID", c.getCampiagnID());
                         j.put("campiagnName", c.getCampiagnName());
                         j.put("description", c.getDescription());
                         j.put("region", c.getRegion());
                         j.put("status", c.getStatus());
                         j.put("templateId", c.getTemplateId());

                         array.put(j);
                         
                         response.setContentType("application/json");
                                    PrintWriter out = response.getWriter();
                                     out.write(array.toString());
                      }
            
                }
                else if(deleteCampID!=null)
                {   
                    System.out.println("here the Campaign ID :"+ deleteCampID);
                      campiagnInter i=new campaignImpl();
                      int val=i.deleteCampaign(Integer.valueOf(deleteCampID));
                      if(val>0)
                      {
                          System.out.println("delete done");
                            response.setContentType("text/plain");
                             PrintWriter out = response.getWriter();
                            out.write("Done");
                       }
                }
                else
                {
                  String subClient=request.getParameter("subClient");
                 String CampName=request.getParameter("CampName");
                 String campdesc=request.getParameter("campdesc");
                 String region=request.getParameter("region");
                  String status=request.getParameter("status");
                  String template=request.getParameter("template");

                   
                  System.out.println("clientID3:: "+subClient+" "+ CampName+" "+ campdesc+" "+ region+" "+ status);

                 int campaignID=new Random().nextInt();
                     campaignModal c=new campaignModal();
                     c.setCampiagnID(campaignID);
                     c.setCampiagnName(CampName);
                     c.setDescription(campdesc);
                     c.setRegion(region);
                     c.setStatus(status);
                     c.setSubclientID(Integer.valueOf(subClient));
                     c.setTemplateId(Integer.parseInt(template));
                     JSONArray array=new JSONArray();
  
                    campiagnInter i=new campaignImpl();
                    campaignModal c2= i.addcampaign(c);
                    System.out.println("results:: "+c2.getCampiagnName()+" "+ c2.getDescription()+" "+ c2.getCampiagnID());
                        JSONObject j=new JSONObject();   
                         j.put("campiagnID", c.getCampiagnID());
                         j.put("campiagnName", c.getCampiagnName());
                         j.put("description", c.getDescription());
                         j.put("region", c.getRegion());
                          j.put("status", c.getStatus());
                          j.put("templateId", c.getTemplateId());
 
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
