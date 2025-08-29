<%@ page import="java.sql.*" %>
<html>
<body>
<h3>Student Records</h3>
<table border="1">
    <tr><th>Roll No</th><th>Name</th><th>Email</th><th>Program</th></tr>
    <%
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdb","root","password");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM student");
            while(rs.next()){
    %>
    <tr>
        <td><%=rs.getString("roll_no")%></td>
        <td><%=rs.getString("name")%></td>
        <td><%=rs.getString("email")%></td>
        <td><%=rs.getString("program")%></td>
    </tr>
    <%
            }
            con.close();
        } catch(Exception e){ out.println(e); }
    %>
</table>
</body>
</html>
