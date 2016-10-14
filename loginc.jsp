<%@page contentType="text/html; charset=GB2312"%>
<%@page import="com.lsy.*" %>
<%@page import="java.sql.*,java.io.*"%>
<%
  String u=request.getParameter("username");
  String p=request.getParameter("password");
  Connection cn=DataBase.getConnection();
  String sql="select username,password from user where username=?";
  PreparedStatement ps=cn.prepareStatement(sql);
  ps.setString(1,u);
  ResultSet rs=ps.executeQuery();
  if(rs.next()){
    session.setAttribute("user",u);
    Cookie username=new Cookie("user",u);
    username.setMaxAge(60*60*24);
    response.addCookie(username);
    %>
    <jsp:forward page="products.jsp"/>
    <%
    }else{
    %>
    <jsp:forward page="reg.html"/>
    <%
    }
    %>
