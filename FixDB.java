import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class FixDB {
    public static void main(String[] args) {
        String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=TestQT_60;encrypt=false;trustServerCertificate=true";
        String USER = "sa";
        String PASS = "12345";
        
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            
            String sql1 = "UPDATE Category SET categoryname = ? WHERE CategoryId = 1";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, "Phim Hàn");
            ps1.executeUpdate();
            
            String sql2 = "UPDATE Category SET categoryname = ? WHERE CategoryId = 2";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(2, "Phim Kiếm Hiệp");
            ps2.executeUpdate();
            
            System.out.println("FIX_SUCCESS");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
