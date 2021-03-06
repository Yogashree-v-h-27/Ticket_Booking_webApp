package com.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginUser() {
        super();
        
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String CustomerID=request.getParameter("CustomerID");
		String password=request.getParameter("password");
		String dbID=null;
		String dbPassword=null;
		String sql="select * from Customer where CustomerID=? and password=? ";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@MLW0224.corpindia.local:1521:xe","system","root");
		    PreparedStatement ps=con.prepareStatement(sql);
		    ps.setString(1, CustomerID);
		    ps.setString(2, password);
		    ResultSet rs=ps.executeQuery();
		    PrintWriter out=response.getWriter();
		    while(rs.next()){
		    	dbID=rs.getString("CustomerID");
		    	dbPassword=rs.getString("password");
		    }
		    if(CustomerID.equals(dbID) && password.equals(dbPassword)){
		    	out.println("You have registered");
		    	HttpSession session = request.getSession(true);
		    	session.setAttribute("CustomerID",CustomerID);
		    	response.sendRedirect("dashboard.html");
		    }else{
		    	response.sendRedirect("login.html");
		    	
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
	

