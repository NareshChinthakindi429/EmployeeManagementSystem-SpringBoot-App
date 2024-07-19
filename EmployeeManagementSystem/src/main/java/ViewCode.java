import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ViewCode")
public class ViewCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ViewCode() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try
		{ 
			String name=request.getParameter("name");
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mndb","mndb");
			PreparedStatement ps=con.prepareStatement("Select *from empreg where name=?");
			ps.setString(1,name);
			
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			
			int n=rsmd.getColumnCount();
			out.println("<table border='1'style='border-collapse: collapse';>");
			//out.println("<tr>");
			for(int i=1;i<=n;i++)
			{
				out.print("<th>"+rsmd.getColumnName(i)+"</th>");
				
			}
			//out.println("</tr>");
				while(rs.next())
				{
					out.println("<tr>");
					for(int i=1;i<=n;i++)
					{
						out.print("<td>"+rs.getString(i)+"</td>");
					}
					out.println("</tr>");
				}
				out.println("</table>");
			
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

}
