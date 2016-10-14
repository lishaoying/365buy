package com.lsy;
import java.io.UnsupportedEncodingException;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.gjt.mm.mysql.*;

import java.sql.*;

public class SQLTool {
	Connection cn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	//�������ݿ�
	public SQLTool(){
		try{
			//�������ݿ��ǰ��׼��������Class��ľ�̬����Σ�Class.forName()ִֻ����ľ�̬����ε����ݶ���ִ�й��췽���е�����
			//Class.forName("org.gjt.mm.mysql.Driver");
			//?useUnicode=true&characterEncoding=utf8
			//DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/qq", "root", "123456");
			cn=DataBase.getConnection();
		}catch(Exception e){}
	}
	//�ر����ݿ�
	public void close(){
		try{
			
			ps.close();
			cn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//��ѯ����
	public String[] query(String u){
		String p[]=new String[2];
		try{
			String sql="select username,password from user where username=?";
			ps=cn.prepareStatement(sql);
			ps.setString(1, u);
			rs=ps.executeQuery();

			if(rs.next()){
				p[0]=rs.getString("username");
				p[1]=rs.getString("password");
				//p[0]=changeCode(rs.getString("username"));
				System.out.println(p[0]);
				//p[1]=changeCode(rs.getString("password"));
			}else{
				p[0]="";
				p[1]="";
			}	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			close();
		}
		return p;
	}
	//�������
	public int add(User user){
		int i=-1;
		try{
			String u=user.getUsername();
			String p=user.getPassword();
			
			String sql="insert into user(username,password) values(?,?)";
			ps=cn.prepareStatement(sql);
			ps.setString(1, u);
			ps.setString(2, p);
			
			i=ps.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return i;
		
	}
	/*private String changeCode(String s) throws UnsupportedEncodingException {
		if (s == null) {
			return "";
		}
		return new String(s.getBytes("ISO-8859-1"), "GB2312");
	}*/
}
