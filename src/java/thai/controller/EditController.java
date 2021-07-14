/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thai.controller;

import java.io.File;
import java.io.IOException;
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
import static thai.util.Constant.ERROR;
import static thai.util.Constant.LOGGER;
import static thai.util.Constant.SHOW;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "EditController", urlPatterns = {"/EditController"})
public class EditController extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);

            if (isMultiPart) {
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

                String fileName = "";
                String RealPath;
                String userImg = "";

                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                    } else {
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
                String txtUsername = (String) params.get("txtUsername");
                String txtPassword = (String) params.get("txtPassword");
                String txtPasswordOld = (String) params.get("txtPasswordOld");
                String txtName = (String) params.get("txtName");
                String txtEmail = (String) params.get("txtEmail");
                String txtPhone = (String) params.get("txtPhone");
                String txtRole = (String) params.get("txtRole");
                String txtImgOld = (String) params.get("txtImgOld");
                if (fileName.equals("")) {
                    userImg = txtImgOld;
                }
                if (!txtPasswordOld.equals(txtPassword)) {
                    txtPassword = UserObj.SHA_256(txtPassword);
                } 
                UserObj dto = new UserObj(txtUsername, txtName, txtPassword, userImg, txtEmail, txtPhone, txtRole);
                if (dao.updateUser(dto)) {
                    url = SHOW;
                    request.setAttribute("txtId", txtUsername);
                } else {
                    url = ERROR;
                    
                }
            }
        } catch (Exception e) {
            log("ERROR at EditController: " + e.getMessage());
            LOGGER.info("ERROR at EditController:" + e.getMessage());
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
