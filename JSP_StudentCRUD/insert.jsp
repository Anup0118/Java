<%@ page import="java.sql.*" %>
<html>
<body>
<h3>Insert Student</h3>
<form method="post">
    Roll No: <input type="text" name="roll_no"><br>
    Name: <input type="text" name="name"><br>
    Email: <input type="text" name="email"><br>
    Program: <input type="text" name="program"><br>
    <input type="submit" value="Add Student">
</form>

<%
    if(request.getMethod().equalsIgnoreCase("POST")){
        String roll_no = request.getParameter("roll_no");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String program = request.getParameter("program");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdb","root","password");
            PreparedStatement ps = con.prepareStatement("INSERT INTO student(roll_no,name,email,program) VALUES(?,?,?,?)");
            ps.setString(1, roll_no); ps.setString(2, name); ps.setString(3, email); ps.setString(4, program);
            ps.executeUpdate();
            out.println("Student added successfully!");
            con.close();
        } catch(Exception e){ out.println(e); }
    }
%>
</body>
</html>
