package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PMHome extends JFrame {

    public PMHome() {
        setTitle("PM Home");
        setSize(400, 350); // Kích thước cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Cửa sổ mở ở giữa màn hình

        // Tạo JPanel với FlowLayout để căn giữa các thành phần
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());  // Thay đổi layout thành BorderLayout để dễ dàng quản lý vị trí các thành phần

        // Tạo một panel con để chứa các nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50)); // FlowLayout căn giữa

        // Tạo nút Quản lý nhà tài trợ
        JButton manageSponsorsButton = new JButton("Quản lý nhà tài trợ");
        manageSponsorsButton.setPreferredSize(new Dimension(200, 50));
        
        // Tạo nút Ký hợp đồng với nhà tài trợ
        JButton signContractButton = new JButton("Ký hợp đồng với nhà tài trợ");
        signContractButton.setPreferredSize(new Dimension(200, 50));

        // Tạo nút Đăng xuất
        JButton logoutButton = new JButton("Đăng xuất");
        logoutButton.setPreferredSize(new Dimension(200, 50));

        // Thêm ActionListener cho nút "Quản lý nhà tài trợ"
        manageSponsorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chuyển sang màn hình InformationSponsors
                new InformationSponsors();
                dispose(); // Đóng cửa sổ hiện tại
            }
        });

        // Thêm ActionListener cho nút "Đăng xuất"
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quay lại màn hình đăng nhập
                new Login().setVisible(true);;  // Bạn cần tạo lớp Login.java nếu chưa có
                dispose(); // Đóng cửa sổ hiện tại
            }
        });

        // Thêm các nút vào panel
        buttonPanel.add(manageSponsorsButton);
        buttonPanel.add(signContractButton);
        buttonPanel.add(logoutButton);  // Thêm nút đăng xuất vào panel

        // Thêm panel vào cửa sổ
        panel.add(buttonPanel, BorderLayout.CENTER); // Đặt panel nút vào giữa
        panel.add(new JPanel(), BorderLayout.SOUTH); // Vị trí cuối cùng

        // Thêm panel vào cửa sổ
        add(panel);

        // Hiển thị cửa sổ
        setVisible(true);
    }

    public static void main(String[] args) {
        // Tạo đối tượng PMHome và hiển thị
        new PMHome();
    }
}
