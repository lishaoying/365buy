package com.lsy;

import javax.servlet.http.*;

public class Regc extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String u = request.getParameter("username");
			String p = request.getParameter("password");
			String e = request.getParameter("email");
			String vali = request.getParameter("vali");
			int i = -1;
			// 识别验证码
			HttpSession session = request.getSession();
			String valic = (String) session.getAttribute("vali");

			if (vali == null || vali.equals("")) {
				response.sendRedirect("reg.html");
				return;
			}
			if (!(valic.equals(vali))) {
				response.sendRedirect("reg.html");
				return;
			}
			User user = new User();
			user.setUsername(u);
			user.setPassword(p);
			user.setEmail(e);

			SQLTool sqlt = new SQLTool();
			i = sqlt.add(user);

			if (i > 0) {
				Cookie username=new Cookie("user",u);
			    username.setMaxAge(60*60*24);
			    response.addCookie(username);
				
				session.setAttribute("user", u);
				response.sendRedirect("products.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
