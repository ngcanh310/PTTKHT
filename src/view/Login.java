package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DAO.MemberDAO;

public class Login extends JFrame {
    // Khai báo các thành phần giao diện
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    
    // Constructor để tạo giao diện
    public Login() {
        // Tiêu đề cửa sổ
        setTitle("Login");
        
        // Cấu hình cửa sổ
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Tạo JPanel để chứa các thành phần
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        
        // Tạo các Label và TextField
        JLabel lblUsername = new JLabel("Tên đăng nhập:");
        txtUsername = new JTextField(20);
        
        JLabel lblPassword = new JLabel("Mật khẩu:");
        txtPassword = new JPasswordField(20);
        
        btnLogin = new JButton("Đăng nhập");

        // Thêm các thành phần vào panel
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(new JLabel()); // Vị trí trống để căn chỉnh
        panel.add(btnLogin);
        
        // Thêm panel vào frame
        add(panel);
        
        // Xử lý sự kiện nhấn nút login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy tên người dùng và mật khẩu
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());
                
                // Kiểm tra đăng nhập thông qua MemberDAO
                MemberDAO memberDAO = new MemberDAO();
                if (memberDAO.checkLogin(username, password)) {
                    new PMHome().setVisible(true);
                    dispose(); // Đóng cửa sổ đăng nhập
                } else {
                    JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu.");
                }
            }
        });
    }
    
    // Hàm main để khởi động ứng dụng
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Tạo và hiển thị cửa sổ đăng nhập
                new Login().setVisible(true);
            }
        });
    }
}
