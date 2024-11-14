package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO extends DAO {
    // Phương thức kiểm tra đăng nhập
    public boolean checkLogin(String userName, String passWord) {
        String sql = "SELECT * FROM Member WHERE userName = ? AND passWord = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, passWord);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Trả về true nếu tìm thấy user
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
