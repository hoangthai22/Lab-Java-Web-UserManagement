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
import javax.servlet.http.HttpSession;
import thai.dtos.PromotionObj;
import static thai.util.Constant.ERROR;
import static thai.util.Constant.GETALL;
import static thai.util.Constant.LOGGER;
import static thai.util.Constant.PROMOTIONPAGE;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "DeleteUserPromotionController", urlPatterns = {"/DeleteUserPromotionController"})
public class DeleteUserPromotionController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            HttpSession session = request.getSession();
            if (action.equals("Clear")) {
                session.removeAttribute("Promotion");
                url = GETALL;
            } else {
                String userId = request.getParameter("txtId");
                PromotionObj promotion = (PromotionObj) session.getAttribute("Promotion");
                if (promotion.removeDeviceInCart(userId)) {
                    url = PROMOTIONPAGE;
                }
            }
        } catch (Exception e) {
            log("ERROR at DeleteUserPromotionController: " + e.getMessage());
            LOGGER.info("ERROR at DeleteUserPromotionController:" + e.getMessage());
        } finally {
            response.sendRedirect(url);
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
