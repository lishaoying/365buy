package com.lsy;
import javax.imageio.ImageIO;
import javax.servlet.http.*;

import java.io.*;
import java.awt.*;
import java.awt.image.*;

public class Vali extends HttpServlet{
   //准备一个62位的字符串数组
	String ver[]=new String[62];
	public void init(){
		for(int i=0;i<10;i++){
			ver[i]=new Integer(i).toString();
		}
		for(int i=0;i<26;i++){
			ver[i+10]=new Character((char)(97+i)).toString();
		}
		for(int i=0;i<26;i++){
			ver[i+36]=new Character((char)(65+i)).toString();
		}
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response){
		try{
			//设置图像输出格式
			response.setContentType("image/jpeg");
			//获取输出流,图片是二进制流
			OutputStream  os=response.getOutputStream();
			//在内存中准备一张image
			BufferedImage image=new BufferedImage(50,20,BufferedImage.TYPE_INT_RGB);
			//画笔
			Graphics g=image.getGraphics();
			
			//作图
			g.setColor(new Color(200,200,200));
			g.fillRect(0, 0, 50, 20);
			//画干扰线
			g.setColor(new Color(150,150,150));
			for(int i=0;i<20;i++){
				int x1=(int)(Math.random()*50);
				int y1=(int)(Math.random()*20);
				int x2=(int)(Math.random()*50);
				int y2=(int)(Math.random()*20);
				g.drawLine(x1, y1, x2, y2);
			}
			//生成并绘制随机字符串
			String vali="";
			for(int i=0;i<4;i++){
				String v=ver[(int)(Math.random()*62)];
				vali+=v;
				g.setColor(new Color((int)(Math.random())*150,(int)(Math.random())*150,(int)(Math.random())*150));
				g.drawString(v, 8*i+10, 15);
			}
			//保存随机生成的验证码
			HttpSession session=request.getSession();
			session.setAttribute("vali",vali );
			
			g.dispose();
			//以image的形式输出
			
			ImageIO.write(image, "JPEG", os);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

