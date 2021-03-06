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

public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public EditServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		try{  
			HttpSession session = request.getSession(true);
			String CustomerID=request.getParameter("CustomerID");
			String from_Place=request.getParameter("from_Place");
			String to_Destination=request.getParameter("to_Destination");
			int amount=Integer.parseInt(request.getParameter("amount"));
			String travel_date=request.getParameter("travel_date");
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@MLW0224.corpindia.local:1521:xe",
			"system","root");  
			con.setAutoCommit(true);
			System.out.println("Connection established");
			PreparedStatement ps=con.prepareStatement("update ticketDetails set from_Place=?,"
					+ "to_Destination=?,amount=?,travel_date=? where CustomerID=?");
			System.out.println("updating here");
			ps.setString(1, from_Place);
			ps.setString(2, to_Destination);
			ps.setInt(3, amount);
			ps.setString(4,travel_date);
			ps.setString(5, CustomerID);
			int i=ps.executeUpdate();
			
			System.out.println("Ticket updated");
			out.println("<font color='green'>Ticket Updated</font>");
			response.sendRedirect("dashboard.html");
	}catch (ClassNotFoundException e) {
		throw new ServletException(e);
	} catch (SQLException ex) {
		out.println("<font color='red'>Ticket Not Updated</font>");
		throw new ServletException(ex);
	}

	}
	}


