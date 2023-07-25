import java.sql.*;
public class miprimerSelect {
    static final String DB_URL="jdbc:mysql://localhost/UNIVERSIDAD";
    static final String USER="root";
    static final String PASS="root_bas3";
    static final String QUERY= "SELECT * FROM ESTUDIANTES";
    public static void main(String[] args) {
        try (
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs= stmt.executeQuery(QUERY);
        ){while(rs.next()){
            System.out.println("id.: "+rs.getInt("id"));
            System.out.println("nombre: "+rs.getString("nombre"));
        }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
