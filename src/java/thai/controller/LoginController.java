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

import thai.daos.UserDAO;
import thai.dtos.UserObj;
import static thai.util.Constant.GETALL;
import static thai.util.Constant.LOGGER;
import static thai.util.Constant.LOGIN;
import static thai.util.Constant.SHOW;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN;
        try {
            String txtUsername = request.getParameter("txtUsername");
            String txtPassword = request.getParameter("txtPassword");
            UserDAO dao = new UserDAO();
            if (!txtUsername.equals("") && !txtPassword.equals("")) {
                String passwordHash = UserObj.SHA_256(txtPassword);
                UserObj user = dao.checkLogin(txtUsername, passwordHash);
                if (user.getUserId() != null && user.getUserStatus().equals("active")) {
                    if (user.getRoleId().equals("MA")) {
                        url = GETALL;
                    } else if (user.getRoleId().equals("EM")) {
                        url = SHOW;
                        request.setAttribute("txtId", user.getUserId());
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                } else {
                    url = LOGIN;
                    request.setAttribute("id", txtUsername);
                    request.setAttribute("error", "Username or password is invalid!!");
                }

            } else {
                request.setAttribute("id", txtUsername);
                request.setAttribute("error", "Please enter your username and password");
                url = LOGIN;
            }
        } catch (Exception e) {
            log("ERROR at LoginController: " + e.getMessage());
            LOGGER.info("ERROR at LoginController:" + e.getMessage());
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
