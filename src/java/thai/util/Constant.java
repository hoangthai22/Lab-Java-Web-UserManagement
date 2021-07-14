/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thai.util;

import java.io.Serializable;
import org.apache.log4j.Logger;
import thai.controller.LoginController;

/**
 *
 * @author ASUS
 */
public class Constant implements Serializable {

    public static final String SHOW = "ShowInfoController";
    public static final String HISTORY = "GetHistoryController";
    public static final String GETALL = "GetAllUserController";
    public static final String LOADTOHOME = "LoadUserToHomeControler";

    
    public static final String ERROR = "error.jsp";
    public static final String PROMOTIONPAGE = "promotion.jsp";
    public static final String EDITPAGE = "edit.jsp";
     public static final String INSERTPAGE = "insert.jsp";
     public static final String HISTORYPAGE = "history.jsp";
     public static final String HOMEPAGE = "home.jsp";
     public static final String LOGIN = "index.jsp";
     public static final String CHANGEPASSWORDPAGE = "changepassword.jsp";
     
     public static final Logger LOGGER = Logger.getLogger(LoginController.class);
}
