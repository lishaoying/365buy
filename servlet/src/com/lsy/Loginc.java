package com.lsy;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.*;

public class Loginc extends HttpServlet{
    public void doPost(HttpServletRequest request,HttpServletResponse response){
    	try{
    		response.setContentType("text/html;charset=GB2312");
        	
        	String u=request.getParameter("username");
        	System.out.println(u);
        	String p=request.getParameter("password");
        	if(u.equals("")||p.equals("")){
        		response.sendRedirect("login.html");
        	}else{
        		SQLTool sqlc=new SQLTool();
            	String[] upc=sqlc.query(u);
            	
            	if(upc[0].equals(u)){
            		if(upc[1].equals(p)){
            			//验证成功
            			HttpSession session=request.getSession();
            			session.setAttribute("user", u);
            			response.sendRedirect("products.jsp");
            		}else{
            			//验证失败，密码错误
            			response.sendRedirect("login.html");
            		}
            	}else{
            		//验证失败，用户名不存在
            		response.sendRedirect("login.html");
            	}
        	}
    	}catch(Exception e){
           e.printStackTrace();
    	}
    }
    
    /*private String changeCode(String s) throws UnsupportedEncodingException {
		if (s == null) {
			return "";
		}
		return new String(s.getBytes("ISO-8859-1"), "UTF-8");
	}*/
    
   
}
