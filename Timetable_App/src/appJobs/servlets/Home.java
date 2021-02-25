package appJobs.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseService.beans.User;
import databaseService.dao.user.dao.IntUserDao;
import databaseService.dao.user.dao.UserDao;

/**
 * Servlet implementation class Home
 */

public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private IntUserDao userDao;

    public void init() throws ServletException {
    	 userDao = new UserDao();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  HttpSession session = request.getSession();
	
           String usertype = (String) session.getAttribute("usertype");
           
		  if( usertype != null)
		  {
			  User user = new User();
		      user.setUsername((String) session.getAttribute("username"));
		      List<User> l = userDao.selectUser(user);
		      User u = l.get(0);
		      
		      request.setAttribute("user_id", u.getUserID());
			  
			  if (usertype == "admin")
	           {
				  this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/admin_homepage.jsp").forward(request, response);
	           }
			  else if (usertype == "prof")
			  {
				  this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/teacher_homepage.jsp").forward(request, response); 
			  }
	             
		  }
		  else
			  this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/class_choice.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
