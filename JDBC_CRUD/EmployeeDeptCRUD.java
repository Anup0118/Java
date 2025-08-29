import java.sql.*;
import java.util.Scanner;

public class EmployeeDeptCRUD {
    static class DBConnection {
        private static final String URL = "jdbc:mysql://localhost:3306/yourdb";
        private static final String USER = "root";
        private static final String PASSWORD = "password";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection();
             Scanner sc = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n1. Insert Employee\n2. Update Employee\n3. Delete Employee\n4. View Employees\n5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> insertEmployee(conn, sc);
                    case 2 -> updateEmployee(conn, sc);
                    case 3 -> deleteEmployee(conn, sc);
                    case 4 -> viewEmployees(conn);
                    case 5 -> { System.exit(0); }
                    default -> System.out.println("Invalid choice!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID: "); int emp_id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Employee Name: "); String emp_name = sc.nextLine();
        System.out.print("Enter Dept ID: "); int dept_id = sc.nextInt();
        System.out.print("Enter Salary: "); double salary = sc.nextDouble();

        String sql = "INSERT INTO Employee(emp_id, emp_name, dept_id, salary) VALUES(?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, emp_id); ps.setString(2, emp_name); ps.setInt(3, dept_id); ps.setDouble(4, salary);
            ps.executeUpdate();
            System.out.println("Employee inserted successfully!");
        }
    }

    private static void updateEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID to update: "); int emp_id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter new Employee Name: "); String emp_name = sc.nextLine();
        System.out.print("Enter new Dept ID: "); int dept_id = sc.nextInt();
        System.out.print("Enter new Salary: "); double salary = sc.nextDouble();

        String sql = "UPDATE Employee SET emp_name=?, dept_id=?, salary=? WHERE emp_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp_name); ps.setInt(2, dept_id); ps.setDouble(3, salary); ps.setInt(4, emp_id);
            ps.executeUpdate();
            System.out.println("Employee updated successfully!");
        }
    }

    private static void deleteEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID to delete: "); int emp_id = sc.nextInt();
        String sql = "DELETE FROM Employee WHERE emp_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, emp_id);
            ps.executeUpdate();
            System.out.println("Employee deleted successfully!");
        }
    }

    private static void viewEmployees(Connection conn) throws SQLException {
        String sql = "SELECT e.emp_id, e.emp_name, e.salary, d.dept_name FROM Employee e LEFT JOIN Department d ON e.dept_id = d.dept_id";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("ID\tName\tSalary\tDepartment");
            while (rs.next()) {
                System.out.println(rs.getInt("emp_id") + "\t" + rs.getString("emp_name") + "\t" +
                        rs.getDouble("salary") + "\t" + rs.getString("dept_name"));
            }
        }
    }
}
