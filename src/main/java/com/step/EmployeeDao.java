package com.step;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    // 172.15.145.32
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/emp_manager";
    // Database credentials
    static final String USER = "postgres";
    static final String PASS = "aozhnl";

    public EmployeeDao() {
        try {
            // Register JDBC driver
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Connection getConnection() throws SQLException {
        //Open a connection
        System.out.println("Connecting to a selected database...");
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Connected database successfully...");
        return conn;
    }

    public int add(Employee emp) {
        String sql = "INSERT INTO app.employee(name, address, phoneno) VALUES (?,?,?)";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, emp.getName());
            pstmt.setString(2, emp.getAddress());
            pstmt.setString(3, emp.getPhoneno());
            System.out.println(emp);
            System.out.println("Executed query =" + sql);
            int rows = pstmt.executeUpdate();
            System.out.println("Inserted " + rows + " records into the table...");
            return rows;

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
            return 0;
        }
    }

    public void add(String name, String address, String phoneno) {
        this.add(new Employee(name, address, phoneno));
    }

    public List<Employee> getAll() {
        String query = "SELECT * FROM app.employee";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            List<Employee> map = new ArrayList<>();
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                map.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            return map;
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
        return null;
    }

    public Employee getById(int id) {
        String query = "SELECT * FROM app.employee WHERE id = ?";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                return employee;
            }
            return null;

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }
        return null;
    }

    // delete records based on id
    public int deleteEmployee(int id) {
        String query = "DELETE FROM app.employee WHERE id = ?";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            // execute delete SQL stetement
            int rows = pstmt.executeUpdate();
            System.out.println(String.format("%d Rows deleted.", rows));
            return rows;
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
            return 0;
        }

    }

    // update records based on id
    public int update(int id, String name, String address, String phoneno) {
        String query = "UPDATE app.employee SET name = ?, address = ?, phoneno = ? " + " WHERE id = ?";
        try (Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, phoneno);
            pstmt.setInt(4, id);
            // execute update SQL stetement
            int rows = pstmt.executeUpdate();
            System.out.println(String.format("%d Rows updated.", rows));
            return rows;
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
            return 0;
        }

    }

}
