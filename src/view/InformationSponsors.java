package view;

import DAO.SponsorDAO;
import model.Sponsor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class InformationSponsors extends JFrame {

    private DefaultTableModel tableModel;
    private JTable sponsorsTable;

    public InformationSponsors() {
        setTitle("Thông tin các nhà tài trợ");
        setSize(600, 400); // Kích thước cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Cửa sổ mở ở giữa màn hình

        // Tạo JPanel để chứa các thành phần chính
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Tạo panel cho nút quay lại và panel tìm kiếm
        JPanel topPanel = new JPanel(new BorderLayout());
        
        // Nút quay lại ở góc trái trên
        JButton backButton = new JButton("Quay lại");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quay lại màn hình PMHome
                new PMHome();
                dispose(); // Đóng cửa sổ hiện tại
            }
        });

        // Panel chứa các thành phần tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // FlowLayout căn trái
        JTextField searchField = new JTextField(15);
        JButton searchButton = new JButton("Tìm");
        JButton newButton = new JButton("Thêm mới");

        // Thêm sự kiện click vào nút thêm mới
        newButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AddSposnor();
                dispose();
            }
        });

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(newButton);

        // Thêm nút quay lại và panel tìm kiếm vào topPanel
        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        // Tạo bảng hiển thị danh sách nhà tài trợ
        String[] columns = {"ID", "Tên", "Địa chỉ", "Xếp hạng", "Ghi chú"};
        tableModel = new DefaultTableModel(columns, 0);
        sponsorsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(sponsorsTable);

        // Lấy danh sách nhà tài trợ từ SponsorDAO
        SponsorDAO sponsorDAO = new SponsorDAO();
        List<Sponsor> sponsors = sponsorDAO.getAllSponsors();
       
        // Thêm dữ liệu vào bảng
        for (Sponsor sponsor : sponsors) {
            Object[] row = {
                sponsor.getId(),
                sponsor.getName(),
                sponsor.getAddress(),
                sponsor.getRank(),
                sponsor.getNote()
            };
            tableModel.addRow(row);
        }

        // Thêm topPanel và bảng vào panel chính
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Thêm panel chính vào cửa sổ
        add(panel);

        // Thêm sự kiện click vào một dòng trong bảng
        sponsorsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = sponsorsTable.getSelectedRow();
                if (row >= 0) {
                    // Lấy ID nhà tài trợ được chọn
                    int sponsorId = (int) tableModel.getValueAt(row, 0);
                    // Mở cửa sổ chi tiết nhà tài trợ
                    new InformationSponsor(sponsorId);
                    dispose(); // Đóng cửa sổ hiện tại
                }
            }
        });

        // Xử lý sự kiện click nút tìm
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchName = searchField.getText().trim(); // Lấy tên từ ô tìm kiếm
                if (!searchName.isEmpty()) {
                    // Xóa các dòng hiện tại trong bảng
                    tableModel.setRowCount(0);
                    // Gọi DAO để tìm kiếm nhà tài trợ theo tên
                    List<Sponsor> sponsors = sponsorDAO.searchSponsorByName(searchName);

                    // Nếu có kết quả tìm kiếm
                    if (!sponsors.isEmpty()) {
                        for (Sponsor sponsor : sponsors) {
                            Object[] row = {
                                sponsor.getId(),
                                sponsor.getName(),
                                sponsor.getAddress(),
                                sponsor.getRank(),
                                sponsor.getNote()
                            };
                            tableModel.addRow(row);
                        }
                    } else {
                        // Hiển thị thông báo nếu không có kết quả
                        JOptionPane.showMessageDialog(null, "Không có nhà tài trợ phù hợp.");
                    }
                } else {
                    // Nếu ô tìm kiếm trống
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập tên nhà tài trợ.");
                }
            }
        });

        // Hiển thị cửa sổ
        setVisible(true);
    }

    public static void main(String[] args) {
        // Tạo đối tượng InformationSponsors và hiển thị
        new InformationSponsors();
    }
}
