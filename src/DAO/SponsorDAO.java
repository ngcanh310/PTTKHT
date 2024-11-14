package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Sponsor;

public class SponsorDAO extends DAO {

    // Constructor gọi constructor của lớp DAO
    public SponsorDAO() {
        super();
    }

    // Phương thức tìm kiếm nhà tài trợ theo tên
    public Sponsor findSponsorByName(String name) {
        Sponsor sponsor = null;
        String sql = "SELECT * FROM sponsors WHERE name = ?";  // Giả sử bảng tài trợ tên là 'sponsors'

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                sponsor = new Sponsor(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("rank"),
                    rs.getString("note")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sponsor;
    }

    public List<Sponsor> getAllSponsors() {
        List<Sponsor> sponsors = new ArrayList<>();
        String sql = "SELECT * FROM sponsor"; 

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Sponsor sponsor = new Sponsor(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("rankSponsor"),
                    rs.getString("note")
                );
                sponsors.add(sponsor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sponsors;
    }
    
 // Phương thức lấy thông tin nhà tài trợ theo ID
    public Sponsor getSponsorById(int sponsorId) {
        Sponsor sponsor = null;
        String sql = "SELECT * FROM sponsor WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sponsorId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                sponsor = new Sponsor(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("rankSponsor"),
                    rs.getString("note")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return sponsor;
    }

    // Phương thức xóa nhà tài trợ
    public void deleteSponsor(int sponsorId) {
        String sql = "DELETE FROM sponsor WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sponsorId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean updateSponsor(Sponsor sponsor) {
        String sql = "UPDATE sponsor SET name = ?, address = ?, rankSponsor = ?, note = ? WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sponsor.getName());
            stmt.setString(2, sponsor.getAddress());
            stmt.setString(3, sponsor.getRank());  // Sử dụng rankSponsor như một chuỗi
            stmt.setString(4, sponsor.getNote());
            stmt.setInt(5, sponsor.getId());
            
            int rowsAffected = stmt.executeUpdate(); // Thực hiện câu lệnh cập nhật
            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng được cập nhật
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ SQL
            return false; // Trả về false nếu xảy ra lỗi
        }
    }

    // Hàm thêm nhà tài trợ mới vào cơ sở dữ liệu
    public boolean addSponsor(Sponsor sponsor) {
        String sql = "INSERT INTO sponsor (name, address, rankSponsor, note) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, sponsor.getName());  // Set tên nhà tài trợ
            stmt.setString(2, sponsor.getAddress());  // Set địa chỉ
            stmt.setString(3, sponsor.getRank());  // Set xếp hạng
            stmt.setString(4, sponsor.getNote());  // Set ghi chú

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Nếu có ít nhất 1 dòng bị ảnh hưởng, trả về true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Nếu có lỗi, trả về false
        }
    }

    public List<Sponsor> searchSponsorByName(String name) {
        List<Sponsor> sponsors = new ArrayList<>();
        String sql = "SELECT * FROM sponsor WHERE name LIKE ?"; // Tìm kiếm theo tên
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%"); // Thêm dấu % để tìm kiếm theo phần tên
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Sponsor sponsor = new Sponsor(0, "", "", "", "");
                sponsor.setId(rs.getInt("id"));
                sponsor.setName(rs.getString("name"));
                sponsor.setAddress(rs.getString("address"));
                sponsor.setRank(rs.getString("rankSponsor"));
                sponsor.setNote(rs.getString("note"));
                sponsors.add(sponsor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sponsors;
    }






    
}
