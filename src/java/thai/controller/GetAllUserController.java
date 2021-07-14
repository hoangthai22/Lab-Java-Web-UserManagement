/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thai.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thai.daos.UserDAO;
import thai.dtos.UserObj;
import static thai.util.Constant.ERROR;
import static thai.util.Constant.LOADTOHOME;
import static thai.util.Constant.LOGGER;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "GetAllUserController", urlPatterns = {"/GetAllUserController"})
public class GetAllUserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            List<UserObj> listSearchAll = (List<UserObj>) request.getAttribute("listSearchAll");
            if (action == null) {
                action = "GetAll";
            }
            if (listSearchAll != null) {
                request.setAttribute("listUser", listSearchAll);
            } else {
                UserDAO dao = new UserDAO();
                List<UserObj> listUser = null;
                if (action.equals("GetEM")) {
                    listUser = dao.getUserWithRole("EM");
                    if (listUser.size() > 0) {
                        request.setAttribute("page", "EM");
                    }
                } else if (action.equals("GetMA")) {
                    listUser = dao.getUserWithRole("MA");
                    request.setAttribute("page", "MA");
                } else {
                    listUser = dao.getUser();
                }
                if (listUser.size() > 0) {
                    request.setAttribute("listUser", listUser);
                }
                
            }
            url = LOADTOHOME;
        } catch (Exception e) {
            log("ERROR at GetAllUserController: " + e.getMessage());
            LOGGER.info("ERROR at GetAllUserController:" + e.getMessage());
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
