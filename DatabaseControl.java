import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseControl {
    static final String URL = "jdbc:postgresql://ep-patient-pond-a240rs3p.eu-central-1.aws.neon.tech/AITUstudents?sslmode=require";
    static String USERNAME = "AmadeoMartell";
    static String PASSWORD = "nJ2w6aCUZhkM";
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

    public ArrayList selectAll() {
        String SQL = "SELECT * FROM studentdb";
        ArrayList<Student> students = new ArrayList<>();
        try (Statement stmt = conn.createStatement();

             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                students.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("major")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  students;
    }

    public void insertRecord(String value1, String value2 , String value3, String value4) {
        String SQL = "INSERT INTO studentdb(id, name, age, major) VALUES(?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, Integer.parseInt(value1));
            pstmt.setString(2, value2);
            pstmt.setInt(3, Integer.parseInt(value3));
            pstmt.setString(4, value4);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("A new record was inserted successfully.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }



    public void updateStudentField(String id, String field, String newValue) {
        String SQL = "UPDATE studentdb SET " + field + " = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            if (field.equals("age")) {
                pstmt.setInt(1, Integer.parseInt(newValue));
            } else {
                pstmt.setString(1, newValue);
            }
            pstmt.setInt(2,Integer.parseInt(id));

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println(field + " updated successfully.");
            } else {
                System.out.println("No record found to update.");
            }
        } catch (SQLException ex) {
            System.out.println("Update error: " + ex.getMessage());
        }
    }
    public void deleteStudent(String id) {
        String SQL = "DELETE FROM studentdb WHERE id = ? ";

        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, Integer.parseInt(id));

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Student with ID " + id + " was deleted successfully.");
            } else {
                System.out.println("No record found with ID " + id);
            }
        } catch (SQLException ex) {
            System.out.println("Deletion error: " + ex.getMessage());
        }
    }
    public String searchStudent(String id){
        String SQL = "SELECT * FROM studentdb WHERE id = " + id;
        Student student = new Student(404, "error", 0, "ERROR");
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                student = new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("major"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return student.toString();
    }
}
