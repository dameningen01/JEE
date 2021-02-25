package appJobs.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseService.beans.Timetable;
import databaseService.beans.User;
import databaseService.dao.timetableDao.IntTimetableDao;
import databaseService.dao.timetableDao.TimetableDao;

/**
 * Servlet implementation class Professor
 */

public class Professor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Professor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	    private IntTimetableDao timetableDao;
	
	    public void init() throws ServletException {
	    	timetableDao = new TimetableDao();
	    }
    //teachers?t_id=1&tt_id=1
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long t_id;
		Long tt_id;
		t_id = Long.parseLong(request.getParameter("t_id"));
		tt_id = Long.parseLong(request.getParameter("tt_id"));
		
		HttpSession session = request.getSession();
		if(session.getAttribute("id") != null)
		{
			
			  Timetable tt = new Timetable();
			  tt.setTimetableId(tt_id);
			  
			  List<Timetable> l = timetableDao.selectUniqueTimetable(tt);
			  Timetable timetable = l.get(0);
			  
			  
			  if (session.getAttribute("usertype") == "admin" && timetable.getTimetableUserFk() != t_id)
	           {
				  request.setAttribute("access-denied", "Timetable of another user");
				  this.getServletContext().getRequestDispatcher("/Timetable_App/").forward(request, response);
				    
	           }
			  else
			  {
				  request.setAttribute("timetable", tt_id);
				  request.setAttribute("teacher", t_id);
				  request.setAttribute("user", timetable.getTimetableUserFk());
				  this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/teacher_timetable.jsp").forward(request, response);
			  }
			  
			  
		}
		else
			//redirect to Login
            response.sendRedirect("/Timetable_App/login"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
