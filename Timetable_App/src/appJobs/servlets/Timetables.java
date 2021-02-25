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
import databaseService.dao.timetableDao.IntTimetableDao;
import databaseService.dao.timetableDao.TimetableDao;

/**
 * Servlet implementation class Timetables
 */

public class Timetables extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Timetables() {
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
    
    // '/timetables?tt_id=1'
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("tt_id") != null)
		{
			Long tt_id;
			tt_id = Long.parseLong(request.getParameter("tt_id"));
			
			HttpSession session = request.getSession();
			if(session.getAttribute("id") == null)
			{
				request.setAttribute("access-denied", "reserved for admins");
				this.getServletContext().getRequestDispatcher("/Timetable_App/").forward(request, response);
			}
			else 
			{
				Timetable timetable = new Timetable();
				timetable.setTimetableId(tt_id);
				List<Timetable> l = timetableDao.selectTimetable(timetable);
				Timetable tt = l.get(0);
				
				if(session.getAttribute("usertype") == "admin" && tt.getTimetableUserFk() == session.getAttribute("id") )
				{
					request.setAttribute("timetable", tt_id);
					request.setAttribute("user", tt.getTimetableUserFk());
					this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/admin_timetable.jsp").forward(request, response);
				}
				else
				{
					request.setAttribute("access-denied", "Access denied");
					this.getServletContext().getRequestDispatcher("/Timetable_App/").forward(request, response);
				}
				
			}
			
		}
		else
		{
			response.sendRedirect("/Timetable_App/"); 
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
