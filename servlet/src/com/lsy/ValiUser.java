package com.lsy;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class ValiUser extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		 try{
			   //����������ģ���ȡ�����
			   response.setContentType("text/html;charset=gb2312");
			   PrintWriter pw=response.getWriter();
			   
			   //����������ύ���û���
			   String username=request.getParameter("username");
			   //�����ݿ��в�ѯ�û����������ݿ������֤�߼��������ؽ��
			   Connection cn=DataBase.getConnection();
			   String sql="select username from user where username=?";
			   PreparedStatement ps=cn.prepareStatement(sql);
			   ps.setString(1,username);
			   ResultSet rs=ps.executeQuery();
			   //������Ϣ��������
			   if(rs.next()){
				   pw.println("���û��Ѵ��ڣ��뻻һ�����֣�");
			   }else{
				   pw.println("���û������á�");
			   }
			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	}
  
}
