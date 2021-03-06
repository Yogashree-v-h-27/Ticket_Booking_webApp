package com.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Ticket extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Ticket() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String result="Ticket data entered successfully";
		String from_Place=request.getParameter("from_Place");
		String to_Destination=request.getParameter("to_Destination");
		String amount=request.getParameter("amount");
		String travel_date=request.getParameter("travel_date");
		HttpSession session = request.getSession(true);
		String CustomerID = (String)session.getAttribute("CustomerID");
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@MLW0224.corpindia.local:1521:xe",
			"system","root");  
			String stmt="insert into ticketDetails values(?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(stmt); 
			ps.setString(1, CustomerID);
			ps.setString(2, from_Place);
			ps.setString(3, to_Destination);
			ps.setInt(4, Integer.parseInt(amount));
			ps.setString(5, travel_date);
			
			int i=ps.executeUpdate(); 
			out.println("<font color='green'>Ticket Added</font>");
			response.sendRedirect("ViewTicket");
			
	}catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		out.println("<font color='red'>Ticket Not Added</font>");
		e.printStackTrace();
	}

}
}
