package com.lsy;

import javax.servlet.http.*;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.gjt.mm.mysql.*;

import java.sql.*;

public class Products extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 中文输出html页面
			response.setContentType("text/html;charset=gb2312");
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
			// 输出HTML标记
			pw.println("<html>");
			pw.println("<link rel='stylesheet' href='show.css' type='text/css'>");
			pw.println("<script src='products.js'></script>");

			pw.println("<body onload='newObject("+nowPage+")'>");

			// 显示用户名
			HttpSession session = request.getSession();
			String user = (String) session.getAttribute("user");
			if (user == null) {
				pw.println("<a href='login.html'>请登录</a>");
			} else {
				pw.println("欢迎，" + user);
			}

			pw.println("<table id='t1' border='1' width='100%'>");
			pw.println("<script>");
			pw.println("for(var row=1;row<=2;row++){");
			pw.println("document.write(\"<tr align='center'>\");");
			pw.println("for(cell=1;cell<=4;cell++){");
			pw.println("document.write('<td></td>');");
			pw.println("}");
			pw.println("document.write('</tr>')");
			pw.println("}");
			pw.println("</script>");
			pw.println("</table>");
			pw.println("");
			pw.println("");
			pw.println("");
			// 显示页码
			// 获得总行数
			Statement st = cn.createStatement();
			String sqlh = "select count(pid) from products";
			ResultSet rs = st.executeQuery(sqlh);
			rs.next();
			int rowCount = rs.getInt(1);
			// 计算总页数
			int pageCount = 1;
			if (rowCount % 8 == 0) {
				pageCount = rowCount / 8;
			} else {
				pageCount = rowCount / 8 + 1;
			}
			for (int i = 1; i <= pageCount; i++) {
				pw.println("<a href='show.bin?page=" + i + "'>" + i + "</a>");
			}
			pw.println("</body>");
			pw.println("</html>");
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
