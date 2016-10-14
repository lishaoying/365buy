package com.lsy;

import javax.servlet.http.*;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.gjt.mm.mysql.*;

import java.sql.*;

public class Prodata extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			// �������htmlҳ��
			response.setContentType("text/xml;charset=gb2312");
			// ׼�������
			PrintWriter pw = response.getWriter();
			// ׼���������ݿ�
			Connection cn = DataBase.getConnection();
			// ���յ�ǰҳ��
			String page = request.getParameter("page");
			// ��ȫ�ԣ���ֹ�ڵ�ַ���������
			int nowPage = 1;
			try {
				nowPage = Integer.parseInt(page);
			} catch (Exception e) {
			}
			// ��ѯ����
			Statement st = cn.createStatement();
			String sql = "select image,name,price,gift,down,info,pid from products";
			ResultSet rs = st.executeQuery(sql);
			//��ѯ������
			Statement sth = cn.createStatement();
			String sqlh="select count(pid) from products";
			ResultSet rsh=sth.executeQuery(sqlh);
			// ��ѯ������
			String sqlc = "select count(*) from comments where pid=?";
			PreparedStatement ps = cn.prepareStatement(sqlc);
			//��������Ҫ����Ʒ
			for (int j = 0; j < (nowPage - 1) * 8; j++) {
				rs.next();
			}
			// ���XML���
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
