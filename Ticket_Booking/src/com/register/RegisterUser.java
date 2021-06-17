package com.register;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterUser extends HttpServlet {
		private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String CustomerID=request.getParameter("CustomerID");
		String CustomerName=request.getParameter("CustomerName");
		String CustomerAddress=request.getParameter("CustomerAddress");
		String password=request.getParameter("password");
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@MLW0224.corpindia.local:1521:xe","system","root");  
			  
			PreparedStatement ps=con.prepareStatement(  
			"insert into Customer values(?,?,?,?)");  
			ps.setString(1, CustomerID);  
			ps.setString(2,CustomerName);  
			ps.setString(3,CustomerAddress);
			ps.setString(4, password);
			
			int i=ps.executeUpdate(); 
			if(i>0)
			out.print("You are successfully registered");  
		response.sendRedirect("login.html");
			          
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.close();  
			}  
	
		
	}


