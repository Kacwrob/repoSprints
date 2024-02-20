import java.sql.*;

public class InvoiceTest {

    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/Invoice";

    
    static final String USER = "root";
    static final String PASS = "root";
    
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            
           
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();
            
            String sql = "INSERT INTO Invoice (area_id, customer_id, town, order_date) " +
                         "VALUES (10, 2, 'Athlone', '2024-01-12')";
            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");

           
            System.out.println("Listing all invoices...");
            ResultSet rs = stmt.executeQuery("SELECT * FROM Invoice");
            while (rs.next()) {
                
                int area_id  = rs.getInt("area_id");
                int customer_id = rs.getInt("customer_id");
                String town = rs.getString("town");
                Date order_date = rs.getDate("order_date");

              
                System.out.print("Area ID: " + area_id);
                System.out.print(", Customer ID: " + customer_id);
                System.out.print(", Town: " + town);
                System.out.println(", Order Date: " + order_date);
            }
            rs.close();

           
            System.out.println("Updating an invoice...");
            sql = "UPDATE Invoice " +
                  "SET town = 'Dublin' WHERE area_id = 10";
            stmt.executeUpdate(sql);

            
            System.out.println("Deleting an invoice...");
            sql = "DELETE FROM Invoice WHERE area_id = 10";
            stmt.executeUpdate(sql);
            
            System.out.println("Test finished.");

        } catch (SQLException se) {
            
            se.printStackTrace();
        } catch (Exception e) {
          
            e.printStackTrace();
        } finally {
           
            try {
                if (stmt != null) conn.close();
            } catch (SQLException se) {
            } 
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } 
        } /
        System.out.println("Goodbye!");
    }
}
