import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ViewAllUser")
public class ViewAllUser extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    
    public ViewAllUser() 
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
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select *from empreg");
			
			ResultSetMetaData rsmd=rs.getMetaData();
			
			int n=rsmd.getColumnCount();
			
			out.println("<table border='1' style='border-collapse: collapse'>");
			for(int i=1;i<=n;i++)
			{
				out.println("<th>"+rsmd.getColumnName(i)+"</th>");
			}
				out.println("<tr>");
				while(rs.next())
				{
					for(int j=1;j<=n;j++)
					{
						out.println("<td>"+rs.getString(j)+"</td>");
					}
					out.println("</tr>");
				}
				

			out.println("</table>");
			con.close();
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

}
