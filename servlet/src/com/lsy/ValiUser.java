package com.lsy;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class ValiUser extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		 try{
			   //设置输出中文，获取输出流
			   response.setContentType("text/html;charset=gb2312");
			   PrintWriter pw=response.getWriter();
			   
			   //接收浏览器提交的用户名
			   String username=request.getParameter("username");
			   //到数据库中查询用户名，由数据库完成验证逻辑，并返回结果
			   Connection cn=DataBase.getConnection();
			   String sql="select username from user where username=?";
			   PreparedStatement ps=cn.prepareStatement(sql);
			   ps.setString(1,username);
			   ResultSet rs=ps.executeQuery();
			   //发送信息给服务器
			   if(rs.next()){
				   pw.println("该用户已存在，请换一个名字！");
			   }else{
				   pw.println("此用户名可用。");
			   }
			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	}
  
}
