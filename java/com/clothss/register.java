package com.clothss;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import java.sql.*;

public class register extends HttpServlet
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//getting a data from  browser
		String name=req.getParameter( "name");
		String surname=req.getParameter( "surname");
		String mobile=req.getParameter( "mobile");
		String pass=req.getParameter( "pass");
		
		/*out.print(name);*/
		// doing connection
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shop" ,"root" ,"root");
		System.out.println(con);
			String k="insert into shopsummary values(?,?,?,?);";
			PreparedStatement pstmt=con.prepareStatement(k);
			
			pstmt.setString(1, name);
			pstmt.setString(2, surname);
			pstmt.setString(3, mobile);
			pstmt.setString(4, pass);
			pstmt.execute();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Cookie ck=new Cookie("username", "name");
		resp.addCookie(ck);
		
		
		RequestDispatcher rs = req.getRequestDispatcher("NewFile.html");
		rs.forward(req, resp);
		
		
		
	}
}
