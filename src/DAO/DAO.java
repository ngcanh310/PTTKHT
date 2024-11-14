		package DAO;
		
		import java.sql.Connection;
		import java.sql.DriverManager;
		import java.sql.SQLException;
		
		public class DAO {
		    // Thông tin kết nối cơ sở dữ liệu
		    private final String jdbcURL = "jdbc:mysql://localhost:3306/chess";
		    private final String jdbcUsername = "root";
		    private final String jdbcPassword = "ngocanhnek1";
		
		    protected Connection connection;
		
		    // Constructor để khởi tạo kết nối
		    public DAO() {
		        try {
		            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		
		    // Đóng kết nối (nếu cần)
		    public void closeConnection() {
		        if (connection != null) {
		            try {
		                connection.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		}
		
