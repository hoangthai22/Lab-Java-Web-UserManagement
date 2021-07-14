/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thai.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thai.daos.UserDAO;
import thai.dtos.PromotionObj;
import thai.dtos.UserObj;
import static thai.util.Constant.ERROR;
import static thai.util.Constant.HISTORY;
import static thai.util.Constant.LOGGER;
import static thai.util.Constant.PROMOTIONPAGE;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ConfirmPromotionController", urlPatterns = {"/ConfirmPromotionController"})
public class ConfirmPromotionController extends HttpServlet {

    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String[] rank = request.getParameterValues("txtRank");
            if (rank.length > 0) {
                HttpSession session = request.getSession();
                PromotionObj promotion = (PromotionObj) session.getAttribute("Promotion");
                UserDAO dao = new UserDAO();
                int i = 0;
                boolean checkPromotionStatus = true;
                boolean checkRank = true;
                List<UserObj> user = new ArrayList<>();
                for (Map.Entry<String, Float> entry : promotion.getPromotionList().entrySet()) {
                    UserObj dto = new UserObj();
                    Float promotionRank = Float.parseFloat(rank[i++]);
                    if (!dao.checkStatusPromotion(entry.getKey())) {
                        dto.setError(true);
                        checkPromotionStatus = false;
                        request.setAttribute("error", "The account are already exists in the promotion list:");
                        request.setAttribute("userError", user);
                    }
                    dto.setUserId(entry.getKey());
                    dto.setPromotionRank(promotionRank);
                    user.add(dto);
                    url = PROMOTIONPAGE;
                }
                if (checkPromotionStatus) {
                    for (int j = 0; j < user.size(); j++) {
                        promotion.updateCart(user.get(j).getUserId(), user.get(j).getPromotionRank());
                        if (user.get(j).getPromotionRank() > 10 || user.get(j).getPromotionRank() < 0) {
                            checkRank = false;
                        }
                    }
                    if (checkRank) {
                        for (UserObj userObj : user) {
                            dao.addUserToPromotionList(userObj.getUserId(), userObj.getPromotionRank());
                        }
                        session.removeAttribute("Promotion");
                        url = HISTORY;
                    } else {
                        request.setAttribute("error", "Please enter rank have value 1 to 10!");
                    }
                }

            }
        } catch (Exception e) {
            log("ERROR at ConfirmPromotionController: " + e.getMessage());
            LOGGER.info("ERROR at ConfirmPromotionController:" + e.getMessage());
            request.setAttribute("error", "Please enter rank have value 1 to 10!");
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
