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

public class EditTicket extends HttpServlet {
	ResultSet rs;
	private static final long serialVersionUID = 1L;
   
    public EditTicket() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String CustomerID=request.getParameter("CustomerID");
		HttpSession session = request.getSession(true);
		
		try{  session.setAttribute("CustomerID", CustomerID);
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@MLW0224.corpindia.local:1521:xe",
			"system","root");  
			PreparedStatement ps=con.prepareStatement("select *from ticketDetails where CustomerID=?");
			ps.setString(1, CustomerID);
			rs=ps.executeQuery();
			while(rs.next()){
				out.println("<form action='EditServlet' method='POST'");
				out.println("<table>");
				out.println("<tr> <td> Customer ID</td>   <td> <input type='text' name='CustomerID' id='CustomerID' value='" +rs.getString("CustomerID")+"'/></td></tr>");
				out.println("<tr> <td>From</td>   <td> <input type='text' name='from_Place' id='from_Place' value='" +rs.getString("from_Place")+"'/></td></tr>");
				out.println("<tr> <td> To</td>   <td> <input type='text' name='to_Destination' id='to_Destination' value='" +rs.getString("to_Destination")+"'/></td></tr>");
				out.println("<tr> <td> Amount</td>   <td> <input type='text' name='amount' id='amount' value='" +rs.getInt("amount")+"'/></td></tr>");
				out.println("<tr> <td> Date of Travel</td>   <td> <input type='text' name='travel_date' id='travel_date' value='" +rs.getString("travel_date")+"'/></td></tr>");
				out.println("<tr> <td colspan='2'> <input type='submit' value='Edit'/></td></tr>");
				out.println("</table>");
				out.println("</form>");
			}
			
			
			
	}catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
