/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thai.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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
@WebServlet(name = "AddToPromotionController", urlPatterns = {"/AddToPromotionController"})
public class AddToPromotionController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            
            String[] list = request.getParameterValues("txtCheck");
            HttpSession session = request.getSession();
            PromotionObj promotion = (PromotionObj) session.getAttribute("Promotion");
            if (promotion == null) {
                promotion = new PromotionObj();
            }
            if (list != null) {
                List listUser = Arrays.asList(list);
                for (int i = 0; i < listUser.size(); i++) {
                    promotion.AddToPromotionList((String) listUser.get(i));
                }
                session.setAttribute("Promotion", promotion);
                url = PROMOTIONPAGE;
            } else {
                request.setAttribute("error", "Please select the user you want to add");
                url = GETALL;
            }

        } catch (Exception e) {
            log("ERROR at AddToPromotionController: " + e.getMessage());
            LOGGER.info("ERROR at AddToPromotionController:" + e.getMessage());
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
