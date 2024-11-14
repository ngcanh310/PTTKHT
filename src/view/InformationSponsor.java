package view;

import DAO.SponsorDAO;
import model.Sponsor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InformationSponsor extends JFrame {

    public InformationSponsor(int sponsorId) {
        setTitle("Chi tiết nhà tài trợ");
        setSize(400, 300); // Kích thước cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Cửa sổ mở ở giữa màn hình

        // Lấy thông tin chi tiết nhà tài trợ từ SponsorDAO
        SponsorDAO sponsorDAO = new SponsorDAO();
        Sponsor sponsor = sponsorDAO.getSponsorById(sponsorId);

        // Tạo JPanel chính với BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Tạo nút Quay lại ở góc trên bên trái
        JButton backButton = new JButton("Quay lại");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InformationSponsors();
                dispose();
            }
        });
        
        // Tạo JPanel để chứa các thông tin nhà tài trợ
        JPanel infoPanel = new JPanel(new GridLayout(5, 2)); // GridLayout để hiển thị thông tin

        // Thêm các label và text field để hiển thị thông tin chi tiết
        infoPanel.add(new JLabel("ID:"));
        infoPanel.add(new JLabel(String.valueOf(sponsor.getId())));
        infoPanel.add(new JLabel("Tên:"));
        infoPanel.add(new JLabel(sponsor.getName()));
        infoPanel.add(new JLabel("Địa chỉ:"));
        infoPanel.add(new JLabel(sponsor.getAddress()));
        infoPanel.add(new JLabel("Xếp hạng:"));
        infoPanel.add(new JLabel(String.valueOf(sponsor.getRank())));
        infoPanel.add(new JLabel("Ghi chú:"));
        infoPanel.add(new JLabel(sponsor.getNote()));

        // Tạo nút Xóa và Sửa
        JButton deleteButton = new JButton("Xóa");
        JButton editButton = new JButton("Sửa");

        // Thêm ActionListener cho nút Sửa
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditSponsor(sponsorId);
                dispose();
            }
        });

        // Thêm ActionListener cho nút Xóa
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    sponsorDAO.deleteSponsor(sponsorId);
                    JOptionPane.showMessageDialog(null, "Nhà tài trợ đã bị xóa.");
                    new InformationSponsors(); // Quay lại trang InformationSponsors
                    dispose(); // Đóng cửa sổ hiện tại
                }
            }
        });

        // Tạo JPanel cho các nút Xóa và Sửa
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);

        // Thêm nút quay lại vào phía trên cùng, infoPanel ở giữa và buttonPanel phía dưới
        mainPanel.add(backButton, BorderLayout.NORTH);
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Thêm mainPanel vào cửa sổ
        add(mainPanel);

        // Hiển thị cửa sổ
        setVisible(true);
    }

    public static void main(String[] args) {
        // Tạo đối tượng InformationSponsor và hiển thị
        new InformationSponsor(2); // Thử với ID 1
    }
}
