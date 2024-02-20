import org.junit.Assert;
import org.junit.Test;
import java.sql.*;

public class PublicationsDBTest {

    private Connection getConnection() throws SQLException {
        
        String url = "jdbc:mysql://localhost:3306/Publications";
        return DriverManager.getConnection(url, "root", "root");
    }

    @Test
    public void testAddPublication() {
        
        String sql = "INSERT INTO Publications (title, author, genre, publication_date, price, stock) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, "Effective Java");
            pstmt.setString(2, "Joshua Bloch");
            pstmt.setString(3, "Programming");
            pstmt.setString(4, "2018-05-28");
            pstmt.setDouble(5, 45.00);
            pstmt.setInt(6, 10);

            int affectedRows = pstmt.executeUpdate();
            Assert.assertEquals(1, affectedRows);

            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long newId = generatedKeys.getLong(1);
                    Assert.assertTrue(newId > 0);
                } else {
                    Assert.fail("Failed to create publication with a unique identifier.");
                }
            }
        } catch (SQLException ex) {
            Assert.fail("Error occurred: " + ex.getMessage());
        }
    }

    @Test
    public void testBrowsePublications() throws SQLException {
        List<String> publicationTitles = new ArrayList<>();
        String query = "SELECT title FROM Publications";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
             
            while (rs.next()) {
                publicationTitles.add(rs.getString("title"));
            }
            Assert.assertFalse("No publications found.", publicationTitles.isEmpty());
        }
    }

    @Test
    public void testUpdatePublication() throws SQLException {
        
        String updateSql = "UPDATE Publications SET price = ? WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
             
            pstmt.setDouble(1, 55.00); 
            pstmt.setInt(2, 1); 
            int affectedRows = pstmt.executeUpdate();
            Assert.assertEquals(1, affectedRows);
        }
    }

    @Test
    public void testDeletePublication() throws SQLException {
        
        String insertSql = "INSERT INTO Publications (title, author, genre, publication_date, price, stock) VALUES ('Delete Me', 'Author', 'Genre', '2024-01-01', 10.00, 5)";
        String deleteSql = "DELETE FROM Publications WHERE title = 'Delete Me'";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
             
            
            stmt.executeUpdate(insertSql, Statement.RETURN_GENERATED_KEYS);
            
            
            int affectedRows = stmt.executeUpdate(deleteSql);

            
            Assert.assertEquals(1, affectedRows);
        }
    }

}
