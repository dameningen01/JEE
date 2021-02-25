package appJobs.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ClassTimetables
 */

public class ClassTimetable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassTimetable() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // '/classTimetable?c_id=1&tt_id=1'

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long c_id;
		Long tt_id;
		c_id = Long.parseLong(request.getParameter("c_id"));
		tt_id = Long.parseLong(request.getParameter("tt_id"));
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("id") != null)
		{
			response.sendRedirect("/JEE-Project/");
		}
		else
		{
			request.setAttribute("timetable", tt_id );
			request.setAttribute("class", c_id );
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/class_timetable.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
