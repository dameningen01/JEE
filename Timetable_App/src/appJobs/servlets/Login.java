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
 * Servlet implementation class Login
 */

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private IntUserDao userDao;

    public void init() throws ServletException {
    	 userDao = new UserDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        
        List<User> l = userDao.selectUser(user);
        if(l==null)  //username not found
        {
        	request.setAttribute("failed-attempt", "wrong username or password");
        	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
        else 
        {
        	
        	 User u = l.get(0);
        	 
         	if(u.getPassword().equals(password))
         	{
         		//System.out.println("hi");
         		HttpSession session = request.getSession();

                session.setAttribute("username", username);
                session.setAttribute("usertype", u.getUserType());
                session.setAttribute("id", u.getUserID());
                
                //redirect to home page
                response.sendRedirect("/Timetable_App/"); 
         	}
         	else  //incorrect password
         	{
         		request.setAttribute("failed-attempt", "wrong username or password");
            	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
         	}
  	 
        }
 
	}

}
