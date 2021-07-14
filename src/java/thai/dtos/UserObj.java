/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thai.dtos;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

/**
 *
 * @author ASUS
 */
public class UserObj {

    private String userId;
    private String fullName;
    private String password;
    private String userImg;
    private String userEmail;
    private String userPhone;
    private String roleId;
    private String userStatus;
    private Timestamp userCreateDate;
    private float promotionRank;
    private String promotionStatus;
    private String promotionDate;
    private boolean checked;
    private boolean error;

    public UserObj() {
    }

    public UserObj(String userId, String fullName, String password, String userImg, String userEmail, String userPhone, String roleId, String userStatus, Timestamp userCreateDate, float promotionRank, String promotionStatus) {
        this.userId = userId;
        this.fullName = fullName;
        this.password = password;
        this.userImg = userImg;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.roleId = roleId;
        this.userStatus = userStatus;
        this.userCreateDate = userCreateDate;
        this.promotionRank = promotionRank;
        this.promotionStatus = promotionStatus;
    }

    public UserObj(String userId, String fullName, String password, String userImg, String userEmail, String userPhone, String roleId, String userStatus, float promotionRank, String promotionStatus, String promotionDate) {
        this.userId = userId;
        this.fullName = fullName;
        this.password = password;
        this.userImg = userImg;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.roleId = roleId;
        this.userStatus = userStatus;
        this.promotionRank = promotionRank;
        this.promotionStatus = promotionStatus;
        this.promotionDate = promotionDate;
    }

    public UserObj(String userId, String fullName, String userImg, String userEmail, String userPhone, String roleId, Timestamp userCreateDate, float promotionRank, String promotionStatus, String promotionDate) {
        this.userId = userId;
        this.fullName = fullName;
        this.userImg = userImg;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.roleId = roleId;
        this.userCreateDate = userCreateDate;
        this.promotionRank = promotionRank;
        this.promotionStatus = promotionStatus;
        this.promotionDate = promotionDate;
    }

    public UserObj(String userId, String fullName, String password, String userImg, String userEmail, String userPhone, String roleId) {
        this.userId = userId;
        this.fullName = fullName;
        this.password = password;
        this.userImg = userImg;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.roleId = roleId;
    }

    public UserObj(String userId, String userImg, String roleId, float promotionRank, String promotionStatus, String promotionDate) {
        this.userId = userId;
        this.userImg = userImg;
        this.roleId = roleId;
        this.promotionRank = promotionRank;
        this.promotionStatus = promotionStatus;
        this.promotionDate = promotionDate;
    }

  

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isError() {
        return error;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setPromotionDate(String promotionDate) {
        this.promotionDate = promotionDate;
    }

    public String getPromotionDate() {
        return promotionDate;
    }

    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserImg() {
        return userImg;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public Timestamp getUserCreateDate() {
        return userCreateDate;
    }

    public float getPromotionRank() {
        return promotionRank;
    }

    public String getPromotionStatus() {
        return promotionStatus;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public void setUserCreateDate(Timestamp userCreateDate) {
        this.userCreateDate = userCreateDate;
    }

    public void setPromotionRank(float promotionRank) {
        this.promotionRank = promotionRank;
    }

    public void setPromotionStatus(String promotionStatus) {
        this.promotionStatus = promotionStatus;
    }

    public static String SHA_256(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                password.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
