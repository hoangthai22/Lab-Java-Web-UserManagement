package thai.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import thai.dtos.UserObj;
import thai.util.DBUtil;
import static thai.util.Constant.LOGGER;

/**
 *
 * @author ASUS
 */
public class UserDAO {

    private Connection CONN = null;
    private PreparedStatement PRE = null;
    private ResultSet RS = null;

    private void closeConnection() {
        try {
            if (RS != null) {
                RS.close();
            }
            if (PRE != null) {
                PRE.close();
            }
            if (CONN != null) {
                CONN.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.info("ERROR at LoadUserToHomeControler:" + e.getMessage());
        }
    }

    public UserObj checkLogin(String username, String password) throws Exception {

        UserObj dto = new UserObj();
        try {

            String sql = "SELECT userId, userName, userImage, userEmail, roleId, userPhone, userStatus\n"
                    + "FROM [dbo].[User]"
                    + "Where userId = ? AND userPassword = ? AND userStatus = 'active'";
            CONN = DBUtil.getMyConnection();
            PRE = CONN.prepareStatement(sql);
            PRE.setString(1, username);
            PRE.setString(2, password);
            RS = PRE.executeQuery();
            if (RS.next()) {
                String fullname = RS.getString("userName");
                String userId = RS.getString("userId");
                String email = RS.getString("userEmail");
                String userStatus = RS.getString("userStatus");
                String role = RS.getString("roleId");
                String userImage = RS.getString("userImage");
                String userPhone = RS.getString("userPhone");
                dto = new UserObj(userId, fullname, password, userImage, email, userPhone, role, userStatus, 0, "", "");
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean checkUsername(String username) throws Exception {
        boolean check = true;
        try {

            String sql = "SELECT userId\n"
                    + "FROM [dbo].[User]"
                    + "Where userId = ?";
            CONN = DBUtil.getMyConnection();
            PRE = CONN.prepareStatement(sql);
            PRE.setString(1, username);
            RS = PRE.executeQuery();
            if (RS.next()) {
                check = false;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<UserObj> getUser() throws SQLException, NamingException {
        List<UserObj> result = new ArrayList<>();

        try {
            String sql = "SELECT [userId],[userName],[userPassword],[userImage],[userEmail],[roleId],[userPhone],[userCreateDate],[promotionStatus],[promotionRank],[promotionDate]\n"
                    + "From [dbo].[User]\n"
                    + "WHERE [userStatus] = 'active'\n"
                    + "ORDER BY [userId]";

            CONN = DBUtil.getMyConnection();
            PRE = CONN.prepareStatement(sql);

            RS = PRE.executeQuery();
            while (RS.next()) {
                String userId = RS.getString("userId");
                String userName = RS.getString("userName");
                String userImage = RS.getString("userImage");
                String userEmail = RS.getString("userEmail");
                String roleId = RS.getString("roleId");
                String userPhone = RS.getString("userPhone");
                String promotionStatus = RS.getString("promotionStatus");
                Timestamp userCreateDate = RS.getTimestamp("userCreateDate");
                float promotionRank = RS.getFloat("promotionRank");
                Timestamp promotionDate = RS.getTimestamp("promotionDate");
                UserObj pro = new UserObj(userId, userName, userImage, userEmail, userPhone, roleId, userCreateDate, promotionRank, promotionStatus, "" + promotionDate);
                result.add(pro);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<UserObj> getUserWithRole(String roleId) throws SQLException, NamingException {
        List<UserObj> result = new ArrayList<>();
        try {
            String sql = "SELECT [userId],[userName],[userPassword],[userImage],[userEmail],[roleId],[userPhone],[userCreateDate],[promotionStatus],[promotionRank],[promotionDate]\n"
                    + "From [dbo].[User]\n"
                    + "WHERE [userStatus] = 'active'\n AND roleId = ?\n"
                    + "ORDER BY [userId]\n";
            CONN = DBUtil.getMyConnection();
            PRE = CONN.prepareStatement(sql);
            PRE.setString(1, roleId);
            RS = PRE.executeQuery();
            while (RS.next()) {
                String userId = RS.getString("userId");
                String userName = RS.getString("userName");
                String userImage = RS.getString("userImage");
                String userEmail = RS.getString("userEmail");
                String role = RS.getString("roleId");
                String userPhone = RS.getString("userPhone");
                String promotionStatus = RS.getString("promotionStatus");
                Timestamp userCreateDate = RS.getTimestamp("userCreateDate");
                float promotionRank = RS.getFloat("promotionRank");
                Timestamp promotionDate = RS.getTimestamp("promotionDate");
                UserObj pro = new UserObj(userId, userName, userImage, userEmail, userPhone, role, userCreateDate, promotionRank, promotionStatus, "" + promotionDate);
                result.add(pro);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertUser(UserObj dto) throws Exception {
        boolean check = false;
        try {
            String sql = "INSERT INTO [dbo].[User] ([userId],[userName],[userPassword],[userImage],[userEmail],[roleId],[userPhone],[userCreateDate]"
                    + ",[userStatus],[promotionStatus],[promotionRank])\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            CONN = DBUtil.getMyConnection();
            PRE = CONN.prepareStatement(sql);
            PRE.setString(1, dto.getUserId());
            PRE.setString(2, dto.getFullName());
            PRE.setString(3, dto.getPassword());
            PRE.setString(4, dto.getUserImg());
            PRE.setString(5, dto.getUserEmail());
            PRE.setString(6, dto.getRoleId());
            PRE.setString(7, dto.getUserPhone());
            PRE.setTimestamp(8, dto.getUserCreateDate());
            PRE.setString(9, dto.getUserStatus());
            PRE.setString(10, dto.getPromotionStatus());
            PRE.setFloat(11, dto.getPromotionRank());

            check = PRE.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteUser(String id) throws Exception {
        boolean result = false;
        String sql = "Update [dbo].[User] set userStatus = 'inactive'\n"
                + "where userId = ?";
        try {
            CONN = DBUtil.getMyConnection();
            PRE = CONN.prepareStatement(sql);
            PRE.setString(1, id);

            result = PRE.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean addUserToPromotionList(String userId, float rank) throws Exception {
        boolean result = false;
        String sql = "Update [dbo].[User] set [promotionStatus] = 'active',[promotionRank] = ?,[promotionDate] = ?\n"
                + "where userId = ?";
        try {
            Timestamp promotionDate = Timestamp.valueOf(LocalDateTime.now());
            CONN = DBUtil.getMyConnection();
            PRE = CONN.prepareStatement(sql);
            PRE.setFloat(1, rank);
            PRE.setTimestamp(2, promotionDate);
            PRE.setString(3, userId);

            result = PRE.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateStatusPromotion(String userId) throws Exception {
        boolean result = false;
        String sql = "Update [dbo].[User] set [promotionStatus] = 'inactive'\n"
                + "where userId = ?";
        try {

            CONN = DBUtil.getMyConnection();
            PRE = CONN.prepareStatement(sql);
            PRE.setString(1, userId);
            result = PRE.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateRankPromotionById(String userId, float rank) throws Exception {
        boolean result = false;
        String sql = "Update [dbo].[User] set promotionRank = ?\n"
                + "where userId = ?";
        try {

            CONN = DBUtil.getMyConnection();
            PRE = CONN.prepareStatement(sql);
            PRE.setFloat(1, rank);
            PRE.setString(2, userId);
            result = PRE.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public UserObj getUserById(String id) throws SQLException, NamingException {
        UserObj dto = new UserObj();
        try {
            String sql = "SELECT [userId],[userName],[userPassword],[userImage],[userEmail],[roleId],[userPhone],userStatus,[promotionStatus],[promotionRank],[promotionDate]\n"
                    + "FROM [dbo].[User] \n"
                    + "WHERE [userId] = ? ";
            CONN = DBUtil.getMyConnection();
            PRE = CONN.prepareStatement(sql);
            PRE.setString(1, id);
            RS = PRE.executeQuery();

            if (RS.next()) {
                String userId = RS.getString("userId");
                String userName = RS.getString("userName");
                String userPassword = RS.getString("userPassword");
                String userImage = RS.getString("userImage");
                String userEmail = RS.getString("userEmail");
                String roleId = RS.getString("roleId");
                String userPhone = RS.getString("userPhone");
                String promotionStatus = RS.getString("promotionStatus");
                String userStatus = RS.getString("userStatus");
                float promotionRank = RS.getFloat("promotionRank");
                Timestamp promotionDate = RS.getTimestamp("promotionDate");
                dto = new UserObj(userId, userName, userPassword, userImage, userEmail, userPhone, roleId, userStatus, promotionRank, promotionStatus, "" + promotionDate);

            }

        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean updateUser(UserObj user) throws Exception {
        boolean result = false;
        String sql = "Update [dbo].[User] set [userName] = ?,[userPassword] = ?,[userImage] = ?,[userEmail] = ?,[roleId] = ?,[userPhone] = ?\n"
                + "where userId = ?";
        try {
            CONN = DBUtil.getMyConnection();
            PRE = CONN.prepareStatement(sql);
            PRE.setString(1, user.getFullName());
            PRE.setString(2, user.getPassword());
            PRE.setString(3, user.getUserImg());
            PRE.setString(4, user.getUserEmail());
            PRE.setString(5, user.getRoleId());
            PRE.setString(6, user.getUserPhone());
            PRE.setString(7, user.getUserId());
            result = PRE.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateUserPassword(String userId, String pass) throws Exception {
        boolean result = false;
        String sql = "Update [dbo].[User] set [userPassword] = ?\n"
                + "where userId = ?";
        try {
            CONN = DBUtil.getMyConnection();
            PRE = CONN.prepareStatement(sql);
            PRE.setString(1, pass);
            PRE.setString(2, userId);
            result = PRE.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<UserObj> findUserByPrimaryKey(String key, String role) throws Exception {
        List<UserObj> result = new ArrayList<>();
        UserObj dto = null;
        try {
            String sql = "Select [userId],[userName],[userImage],[userEmail],[roleId],[userPhone]\n"
                    + "From [dbo].[User]\n"
                    + "Where [userName] LIKE ? AND userStatus = 'active'";
            if (role.equals("EM")) {
                sql = sql.concat("AND [roleId] = '" + role + "'");
            } else if (role.equals("MA")) {
                sql = sql.concat("AND [roleId] = '" + role + "'");
            }
            CONN = DBUtil.getMyConnection();
            PRE = CONN.prepareStatement(sql);
            PRE.setString(1, "%" + key + "%");

            RS = PRE.executeQuery();
            while (RS.next()) {
                String userId = RS.getString("userId");
                String userName = RS.getString("userName");
                String userImage = RS.getString("userImage");
                String userEmail = RS.getString("userEmail");
                String roleId = RS.getString("roleId");
                String userPhone = RS.getString("userPhone");
                dto = new UserObj(userId, userName, "", userImage, userEmail, userPhone, roleId);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean checkStatusPromotion(String userId) throws SQLException, NamingException {
        boolean check = true;
        try {
            String sql = "SELECT userId\n"
                    + "FROM [dbo].[User]"
                    + "Where userId = ? AND promotionStatus = 'active'";
            CONN = DBUtil.getMyConnection();
            PRE = CONN.prepareStatement(sql);
            PRE.setString(1, userId);
            RS = PRE.executeQuery();
            if (RS.next()) {
                check = false;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<UserObj> getPromotionHistory() throws SQLException, NamingException {
        List<UserObj> result = new ArrayList<>();
        try {
            String sql = "SELECT [userId],[roleId],[userImage],[promotionStatus],[promotionRank],[promotionDate]\n"
                    + "From [dbo].[User]\n"
                    + "WHERE [userStatus] = 'active'\n AND promotionStatus = 'active'\n"
                    + "ORDER BY [promotionDate] DESC\n";

            CONN = DBUtil.getMyConnection();
            PRE = CONN.prepareStatement(sql);
            RS = PRE.executeQuery();
            while (RS.next()) {
                String userId = RS.getString("userId");
                String role = RS.getString("roleId");
                String promotionStatus = RS.getString("promotionStatus");
                String userImage = RS.getString("userImage");
                float promotionRank = RS.getFloat("promotionRank");
                Timestamp promotionDate = RS.getTimestamp("promotionDate");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String string = dateFormat.format(promotionDate);
                UserObj pro = new UserObj(userId, userImage, role, promotionRank, promotionStatus, string);
                result.add(pro);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

}
