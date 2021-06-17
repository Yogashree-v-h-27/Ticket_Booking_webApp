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
import javax.servlet.http.HttpSession;

public class DeleteTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DeleteTicket() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession(true);
		String CustomerID = (String)session.getAttribute("CustomerID");
		try{  
			
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@MLW0224.corpindia.local:1521:xe",
			"system","root");  
			PreparedStatement ps=con.prepareStatement("delete ticketDetails where CustomerID=?" );
			ps.setString(1, CustomerID);
			
			int i=ps.executeUpdate();
			out.println("<font color='green'>Ticket Deleted</font>");
			response.sendRedirect("dashboard.html");
	}catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		out.println("<font color='red'>Ticket Not Updated</font>");
		e.printStackTrace();
	}

	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	

	}
}

