/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thai.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thai.dtos.PromotionObj;
import thai.dtos.UserObj;
import static thai.util.Constant.ERROR;
import static thai.util.Constant.HOMEPAGE;
import static thai.util.Constant.LOGGER;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "LoadUserToHomeControler", urlPatterns = {"/LoadUserToHomeControler"})
public class LoadUserToHomeControler extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            List<UserObj> listUser = (List<UserObj>) request.getAttribute("listUser");
            HttpSession session = request.getSession();
            PromotionObj promotion = (PromotionObj) session.getAttribute("Promotion");
            if (listUser != null) {         
                if (promotion != null) {
                    for (UserObj list : listUser) {
                        for (Map.Entry<String, Float> entry : promotion.getPromotionList().entrySet()) {
                            if (entry.getKey().equals(list.getUserId())) {
                                list.setChecked(true);
                            }
                        }
                    }
                }
                url = HOMEPAGE;
            }
        } catch (Exception e) {
            log("ERROR at LoadUserToHomeControler: " + e.getMessage());
            LOGGER.info("ERROR at LoadUserToHomeControler:" + e.getMessage());
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
