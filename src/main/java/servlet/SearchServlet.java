package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
        name = "SearchServlet", 
        urlPatterns = {"/SearchServlet"}
    )
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    		System.out.println("Enter Search Servlet");
        	String query =  "%" + req.getParameter("query") + "%";
        	System.out.println("The query is " + query);
        	
        	try {
    			
    			Class.forName("com.mysql.cj.jdbc.Driver");
    			
    			Connection conn = null;
    			ResultSet rs = null;
    			PreparedStatement ps = null;
    			
    			System.out.println("Trying to connect to the database...");
    			conn = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-02.cleardb.com:3306/heroku_2ee69ac199fd2d5?user=b002e739943731&password=7098f952&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC");
    			System.out.println("Succesfully connected to the database");
    			
    			System.out.println("running MySQL statement...");
    			ps = conn.prepareStatement("SELECT * FROM deftable WHERE Word LIKE ?");
    			ps.setString(1,query);
    			rs = ps.executeQuery();
    			System.out.println("MySQL statement ran succesfully");
    			
    			System.out.println("Print results to the console");
    			
    			Map<String, String> wordMap = new HashMap<String, String>(); 
    			
    			while(rs.next()) {
    				System.out.println( rs.getString("Word") + ": " + rs.getString("Definitions"));
    				wordMap.put(rs.getString("Word"), rs.getString("Definitions"));
    			}
    			
    			HttpSession session = req.getSession();
    			session.setAttribute("wordMap", wordMap);
    			
    			RequestDispatcher dispatcher = req.getRequestDispatcher("results.jsp");
    	        dispatcher.forward(req, resp);
    			
        	}catch(Exception e) {
        		System.out.println("ur shit is fucked");
        		System.out.println(e.getMessage());
        	}
    }
    
}
