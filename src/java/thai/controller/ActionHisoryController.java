/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thai.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thai.daos.UserDAO;
import static thai.util.Constant.ERROR;
import static thai.util.Constant.HISTORY;
import static thai.util.Constant.LOGGER;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ActionHisoryController", urlPatterns = {"/ActionHisoryController"})
public class ActionHisoryController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;
        try {
            String action = request.getParameter("action");
            UserDAO dao = new UserDAO();
            if (action.equals("DeleteHistory")) {
                String id = request.getParameter("txtId");
                if (dao.updateStatusPromotion(id)) {
                    request.setAttribute("removeSuccess", "Successfully deleted " + id + " account from the promotion list");

                } else {
                    request.setAttribute("error", "An error has occurred!!");
                }

            } else if (action.equals("EditHistory")) {
                boolean checkNull = true;
                String txtIdEdit = request.getParameter("txtIdEdit");
                String txtRankEdit = request.getParameter("txtRankEdit");
                if (txtIdEdit.equals("") || txtRankEdit.equals("")) {
                    checkNull = false;
                    request.setAttribute("error", "Please select the user you want to edit!!");
                } else if (Float.parseFloat(txtRankEdit) > 10 || Float.parseFloat(txtRankEdit) < 0) {
                    checkNull = false;
                    request.setAttribute("error", "Please enter rank have value 1 to 10!");
                    request.setAttribute("txtRankEdit", txtRankEdit);
                    request.setAttribute("txtIdEdit", txtIdEdit);
                }
                if (checkNull) {
                    if (dao.updateRankPromotionById(txtIdEdit, Float.parseFloat(txtRankEdit))) {
                        request.setAttribute("updateSuccess", "Successfully edited " + txtIdEdit + " account from the promotion list");
                    } else {
                        request.setAttribute("error", "An error has occurred!!");
                    }
                } 
            }
            url = HISTORY;
        } catch (Exception e) {
            log("ERROR at ActionHisoryController: " + e.getMessage());
            LOGGER.info("ERROR at ActionHisoryController:" + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
