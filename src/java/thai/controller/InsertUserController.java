/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thai.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import thai.daos.UserDAO;
import thai.dtos.UserObj;
import static thai.util.Constant.GETALL;
import static thai.util.Constant.INSERTPAGE;
import static thai.util.Constant.LOGGER;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "InsertUserController", urlPatterns = {"/InsertUserController"})
public class InsertUserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = INSERTPAGE;
        try {
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (!isMultiPart) {

            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                }
                Iterator iter = items.iterator();

                Hashtable params = new Hashtable();
                UserDAO dao = new UserDAO();
                boolean checkUser = true;
                String fileName;
                String RealPath;
                String txtUsername = "", txtPassword, txtName, txtEmail, txtPhone, txtRole, userImg = "", txtPasswordConfirm;

                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                        txtUsername = (String) params.get("txtUsername");
                        if (!dao.checkUsername(txtUsername)) {
                            checkUser = false;
                            request.setAttribute("error", "Username is exist!!  ");
                            request.setAttribute("txtUsername", txtUsername);
                        }
                    } else if (checkUser) {
                        try {
                            String itemName = item.getName();
                            fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                            RealPath = "C:\\Users\\ASUS\\Desktop\\LAB_WEB\\J3.L.P0017.UserManagement\\web\\assets\\img\\" + fileName;
                            userImg = "assets/img/" + fileName;
                            File savedFile = new File(RealPath);
                            if (!savedFile.exists()) {
                                item.write(savedFile);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                txtPassword = (String) params.get("txtPassword");
                txtPasswordConfirm = (String) params.get("txtPasswordConfirm");
                txtName = (String) params.get("txtName");
                txtEmail = (String) params.get("txtEmail");
                txtPhone = (String) params.get("txtPhone");
                txtRole = (String) params.get("txtRole");
                if (!txtPasswordConfirm.equals(txtPassword)) {
                    checkUser = false;
                    request.setAttribute("error", "Please enter the same password confirm!!  ");
                    request.setAttribute("txtUsername", txtUsername);
                }
                Timestamp creatDate = Timestamp.valueOf(LocalDateTime.now());
                String passwordHash = UserObj.SHA_256(txtPassword);
                UserObj dto = new UserObj(txtUsername, txtName, passwordHash, userImg, txtEmail, txtPhone, txtRole, "active", creatDate, 0, "inactive");
                if (checkUser) {
                    dao.insertUser(dto);
                    url = GETALL;
                } else {
                    request.setAttribute("dto", dto);
                }
            }
        } catch (Exception e) {
            log("ERROR at InsertUserController: " + e.getMessage());
            LOGGER.info("ERROR at InsertUserController:" + e.getMessage());
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
