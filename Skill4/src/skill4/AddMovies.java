package skill4;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddMovies
 */
@WebServlet("/AddMovies")
public class AddMovies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMovies() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String moviename = request.getParameter("moviename");    
	    String dateofrel = request.getParameter("dateofrel");
	    String ticketPrice = request.getParameter("ticketPrice");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				"jfsd","jfsd123");
		System.out.println("Connected to database");
		 Statement st = con.createStatement();
		 
		 String sql = "insert into movies values(myseq2.NEXTVAL,?,?,?)";
		 PreparedStatement pst = con.prepareStatement(sql);
		    pst.setString(1, moviename);
		    pst.setString(2, dateofrel);
		    pst.setString(3, ticketPrice);
		    ResultSet resultSet = pst.executeQuery();
		    PrintWriter out = response.getWriter();
			response.setContentType("text/html");
		    if(resultSet.next()) {
				out.println("<h2>Movie added Successfull</h2>");
				out.println("<h2>Click <a href='addingMovies.html'>here</a></h2>");
		    }else {
		    	out.println("<h2>movie adding Failed</h2>");
		    	out.println("<h2>Click <a href='addingMovies.html'>here</a></h2>");
		    }
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
