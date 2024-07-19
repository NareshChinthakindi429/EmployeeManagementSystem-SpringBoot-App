

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Delete() 
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
			
			PreparedStatement ps=con.prepareStatement("delete from empreg where name=? ");
			ps.setString(1, name);
			
			int i=ps.executeUpdate();
			
			if(i>0)
			{
				out.println("User Deleted...");
			}
			else
			{
				out.print("<h2> Invalid username</h2>");
			}		
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

}
