import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Update()
    {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mndb","mndb");
			
			String name=request.getParameter("name");
			String password=request.getParameter("psw");
			String email=request.getParameter("mail");
			String address=request.getParameter("address");
			Long mobile=Long.parseLong(request.getParameter("mobile"));
			
			PreparedStatement ps=con.prepareStatement("update empreg set password=?, email=?,address=? ,mobile=? where name=?");
			ps.setString(1, password);
			ps.setString(2, email);
			ps.setString(3, address);
			ps.setLong(4, mobile);
			ps.setString(5, name);
			
			
			int i=ps.executeUpdate();
			
			if(i>0)
			{
				out.print("<h1 style='color:green'>User Updated Successfully</h1>");
			}
			else
			{
				out.print("<h1 style='color:red'>Inalid UserName</h1>");
			}
			
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

}
