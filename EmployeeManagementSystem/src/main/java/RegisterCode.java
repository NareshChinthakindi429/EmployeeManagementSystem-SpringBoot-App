import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterCode")
public class RegisterCode extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
   
    public RegisterCode() 
    {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		try
		{
		String name=request.getParameter("name");
		String password=request.getParameter("psw");
		String email=request.getParameter("mail");
		String gender=request.getParameter("gender");
		long mobile=Long.parseLong(request.getParameter("mobile"));
		String state=request.getParameter("state");
		String country=request.getParameter("country");
		String address=request.getParameter("address");
		
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mndb","mndb");
		PreparedStatement ps=con.prepareStatement("insert into empreg values(?,?,?,?,?,?,?,?)");
		ps.setString(1,name);
		ps.setString(2,password);
		ps.setString(3,email);
		ps.setString(4,gender);
		ps.setLong(5,mobile);
		ps.setString(6,state);
		ps.setString(7,country);
		ps.setString(8,address);
		
		int i=ps.executeUpdate();
		
		if(i>0)
		{
			out.print("Registration Successfully.....");
		}
		else
		{
			out.println("Not Registered");
		}
		
		con.close();
		}
		catch(Exception e)
		{
			out.println(e);
			//e.printStackTrace();
		}
	}

}
