package com.lsy;

import javax.servlet.http.*;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.gjt.mm.mysql.*;

import java.sql.*;

public class Prodata extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 中文输出html页面
			response.setContentType("text/xml;charset=gb2312");
			// 准备输出流
			PrintWriter pw = response.getWriter();
			// 准备连接数据库
			Connection cn = DataBase.getConnection();
			// 接收当前页码
			String page = request.getParameter("page");
			// 安全性，防止在地址栏恶意访问
			int nowPage = 1;
			try {
				nowPage = Integer.parseInt(page);
			} catch (Exception e) {
			}
			// 查询数据
			Statement st = cn.createStatement();
			String sql = "select image,name,price,gift,down,info,pid from products";
			ResultSet rs = st.executeQuery(sql);
			//查询总行数
			Statement sth = cn.createStatement();
			String sqlh="select count(pid) from products";
			ResultSet rsh=sth.executeQuery(sqlh);
			// 查询评论数
			String sqlc = "select count(*) from comments where pid=?";
			PreparedStatement ps = cn.prepareStatement(sqlc);
			//跳过不需要的商品
			for (int j = 0; j < (nowPage - 1) * 8; j++) {
				rs.next();
			}
			// 输出XML标记
			pw.println("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
			pw.println("<Products>");
			for (int i = 0; i < 8; i++) {
				if (rs.next()) {
					pw.println("<product>");
					pw.println("<img>");
					pw.println(changeCode(rs.getString(1)));
					pw.println("</img>");
					
					pw.println("<name>");
					pw.println(changeCode(rs.getString(2)));
					pw.println("</name>");
					
					pw.println("<info>");
					pw.println(changeCode(rs.getString(6)));
					pw.println("</info>");
					
					pw.println("<price>");
					pw.println(rs.getDouble(3));
					pw.println("</price>");
					
					pw.println("<commentCount>");
					ps.setInt(1, rs.getInt(7));
					ResultSet rsc=ps.executeQuery();
					rsc.next();
					pw.println(rsc.getInt(1));
					pw.println("</commentCount>");
					
					pw.println("<gift>");
					pw.println(rs.getInt(4));
					pw.println("</gift>");
					
					pw.println("<down>");
					pw.println(rs.getString(5));
					pw.println("</down>");
					
					pw.println("</product>");
				}
			}
			if(rsh.next()){
				System.out.println(rsh.getInt(1));
				pw.println("<count>");
				pw.println(rsh.getInt(1));
				pw.println("</count>");
			}
			pw.println("</Products>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String changeCode(String s) throws UnsupportedEncodingException {
		if (s == null) {
			return "";
		}
		return new String(s.getBytes("ISO-8859-1"), "GB2312");
	}
}
