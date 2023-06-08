package com.clothss;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class logib extends HttpServlet
{
		
	  @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		 String phonenumm=req.getParameter("phonenum");
		 String passwordd=req.getParameter("Password");
		 System.out.println(phonenumm);
		 System.out.println(passwordd);
		 System.out.println("done");
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shop" ,"root" ,"root");
				String k="select * from shopsummary where mobile='"+phonenumm+"';" ;
				PreparedStatement pstmt=con.prepareStatement(k);
				 ResultSet rs = pstmt.executeQuery();
				 
				 while(rs.next())
				 {
					String mobile = rs.getString("mobile");
					System.out.println(mobile);
					String pass = rs.getString("password");
					System.out.println(pass);
					
					if(phonenumm.equals(mobile))
					{
						if(passwordd.equals(pass))
						{
							RequestDispatcher rrs = req.getRequestDispatcher("shop.html");
							rrs.forward(req, resp);
						}
				 }
			}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
}
