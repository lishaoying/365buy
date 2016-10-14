package com.lsy;
import javax.servlet.http.*;
import java.sql.*;
import org.gjt.mm.mysql.*;

public class DataBase extends HttpServlet{
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	public void init(){
		driver=this.getInitParameter("driver");
		url=this.getInitParameter("url");
		user=this.getInitParameter("user");
		password=this.getInitParameter("password");
	}
	public static Connection getConnection(){
		Connection cn=null;
		try{
			Class.forName(driver);
			cn=DriverManager.getConnection(url, user, password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return cn;
	}
}
