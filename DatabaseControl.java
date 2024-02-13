package FinalProject;
import java.sql.*;

public class DatabaseControl {
    static final String URL = "jdbc:postgresql://localhost:5432/StudentsDB";
    static final String USERNAME = "postgres";
    static final String PASSWORD = "Vladislave";
    private Connection conn;

    public DatabaseControl() {
        try {
            this.conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully.");
            // Здесь можно выполнить запросы к базе данных
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void Close() {
        try {
            this.conn.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Connection getConn() {
        return conn;
    }

    public void selectAll() {
        String SQL = "SELECT * FROM studentdb";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getInt("age") +
                        "\t" + rs.getString("major"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void insertRecord(Integer value1, String value2 , Integer value3, String value4) {
        String SQL = "INSERT INTO studentdb(id, name, age, major) VALUES(?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, value1);
            pstmt.setString(2, value2);
            pstmt.setInt(3, value3);
            pstmt.setString(4, value4);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("A new record was inserted successfully.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateStudent(Integer id){
        String SQL = "SELECT * FROM studentdb WHERE id=" + id;
        try(Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(SQL)){
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getInt("age") +
                        "\t" + rs.getString("major"));
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
