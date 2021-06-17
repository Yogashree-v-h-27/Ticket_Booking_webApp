package com.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewTicket
 */
public class ViewTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ViewTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession(true);
		
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@MLW0224.corpindia.local:1521:xe",
			"system","root");  
			String sql="select *from ticketDetails ";
			Statement stmt=con.createStatement(); 
			ResultSet rs=stmt.executeQuery(sql);
			out.println("<table cellspacing='0' width='350px' border='1'>");
			out.println("<tr>");
			out.println("<td>CustomerID</td>");
			out.println("<td>From</td>");
			out.println("<td>To</td>");
			out.println("<td>Ticket Amount</td>");
			out.println("<td>Date of Travel</td>");
			out.println("<td> Edit</td>");
			out.println("<td> Delete</td>");
			out.println("</tr>");
			while(rs.next())
			{    String CustomerID = (String)session.getAttribute("CustomerID");
			     if(CustomerID.equals(rs.getString(1))){
				out.println("<tr>");
				out.println("<td>"+ rs.getString("CustomerID") + "</td>");
				out.println("<td>"+ rs.getString("from_Place") + "</td>");
				out.println("<td>"+ rs.getString("to_Destination") + "</td>");
				out.println("<td>"+ rs.getInt("amount") + "</td>");
				out.println("<td>"+ rs.getString("travel_date") + "</td>");
				out.println("<td>"+ "<a href='EditTicket?CustomerID="+ rs.getString("CustomerID")+"'> Edit </a>"+  "</td>");
				out.println("<td>"+ "<a href='DeleteTicket?CustomerID="+ rs.getString("CustomerID")+"'> Delete </a>"+  "</td>");
				out.println("</tr>");
			     }
			}
			out.println("</table>");
			
			
			
			
	}catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		out.println("<font color='red'>Ticket Not Added</font>");
		e.printStackTrace();
	}

}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
