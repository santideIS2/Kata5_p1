package k5p1;

import java.sql.*;
import java.util.List;

public class DBUtils {

    private List<String> lista;

    public DBUtils(List<String> lista){
        this.lista = lista;
    }
    
    private Connection connect() {
        String url = "jdbc:sqlite:Kata5.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    void selectAll() {
        String sql = "SELECT * FROM People";
        try (Connection conn = this.connect();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t"
                        + rs.getString("Name") + "\t"
                        + rs.getString("Apellidos") + "\t"
                        + rs.getString("Departamento") + "\t");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void createNewTable(){
        String url = "jdbc:sqlite:Kata5.db";

        String sql = "CREATE TABLE IF NOT EXISTS Email (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " mail text NOT NULL);";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void insert() {
        String sql = "INSERT INTO Email(direccion) VALUES(?)";
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (String string : lista) {
                pstmt.setString(1, string);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
