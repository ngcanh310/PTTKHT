package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.SponsorDAO;
import model.Sponsor;

public class EditSponsor extends JFrame {

    public EditSponsor(int sponsorId) {
        setTitle("Sửa thông tin nhà tài trợ");
        setSize(400, 300); // Kích thước cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Cửa sổ mở ở giữa màn hình

        // Lấy thông tin chi tiết nhà tài trợ từ SponsorDAO
        SponsorDAO sponsorDAO = new SponsorDAO();
        Sponsor sponsor = sponsorDAO.getSponsorById(sponsorId);

        // Tạo JPanel để chứa các thành phần chính
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2)); // GridLayout để hiển thị thông tin

        // Thêm các label và text field để hiển thị và chỉnh sửa thông tin chi tiết
        panel.add(new JLabel("ID:"));
        panel.add(new JLabel(String.valueOf(sponsor.getId())));

        panel.add(new JLabel("Tên:"));
        JTextField nameField = new JTextField(sponsor.getName());
        panel.add(nameField);

        panel.add(new JLabel("Địa chỉ:"));
        JTextField addressField = new JTextField(sponsor.getAddress());
        panel.add(addressField);

        panel.add(new JLabel("Xếp hạng:"));
        JTextField rankField = new JTextField(sponsor.getRank());
        panel.add(rankField);

        panel.add(new JLabel("Ghi chú:"));
        JTextField noteField = new JTextField(sponsor.getNote());
        panel.add(noteField);

        // Tạo nút Lưu và Hủy
        JButton saveButton = new JButton("Lưu");
        JButton cancelButton = new JButton("Hủy");

        // Thêm ActionListener cho nút Lưu
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn lưu?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Lấy dữ liệu từ các ô nhập
                    sponsor.setName(nameField.getText());
                    sponsor.setAddress(addressField.getText());
                    sponsor.setRank(rankField.getText());
                    sponsor.setNote(noteField.getText());

                    // Cập nhật vào cơ sở dữ liệu
                    if (sponsorDAO.updateSponsor(sponsor)) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công");
                        new InformationSponsor(sponsorId); // Quay lại trang chi tiết nhà tài trợ
                        dispose(); // Đóng cửa sổ hiện tại
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật thông tin", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Thêm ActionListener cho nút Hủy
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InformationSponsor(sponsorId); // Quay lại trang chi tiết nhà tài trợ
                dispose(); // Đóng cửa sổ hiện tại
            }
        });

        // Thêm các nút vào panel
        panel.add(saveButton);
        panel.add(cancelButton);

        // Thêm panel vào cửa sổ
        add(panel);

        // Hiển thị cửa sổ
        setVisible(true);
    }
}
