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
import thai.daos.UserDAO;
import static thai.util.Constant.ERROR;
import static thai.util.Constant.GETALL;
import static thai.util.Constant.LOGGER;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "DeleteListUserController", urlPatterns = {"/DeleteListUserController"})
public class DeleteListUserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            
            String[] list = request.getParameterValues("txtCheck");

            if (list != null) {
                List listUser = Arrays.asList(list);
                for (int i = 0; i < listUser.size(); i++) {
                    UserDAO dao = new UserDAO();
                    dao.deleteUser((String) listUser.get(i));
                }
            } else {
                request.setAttribute("error", "Please select the user you want to delete");
            }
             url = GETALL;

        } catch (Exception e) {
            log("ERROR at DeleteListUserController: " + e.getMessage());
            LOGGER.info("ERROR at DeleteListUserController:" + e.getMessage());
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
