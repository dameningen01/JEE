package appJobs.servlets.crud;

import java.io.IOException;
import java.sql.SQLException;
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
 * Servlet implementation class TimetableCRUD
 */

public class CRUDtimetable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDtimetable() {
        super();
        // TODO Auto-generated constructor stub
    }

private IntTimetableDao timetableDao ;
    
    public void init() throws ServletException {
    	timetableDao = new TimetableDao();
    	
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 

    		throws ServletException, IOException {
    	
    	 HttpSession session = request.getSession();
    	 
    	 if(session.getAttribute("id")== null) 
    	 {
    		 response.sendRedirect("/Timetable_App/");
    	 }
    	
    	
	 String action = request.getRequestURI();
    	try {
			switch (action) {
			case "/Timetable_App/timetable/new":
			showNewForm(request, response);
			break;
			case "/Timetable_App/timetable/insert":
			inserttimetable(request, response);
			break;
			case "/Timetable_App/timetable/delete":
			deletetimetable(request, response);
			break;
			case "/Timetable_App/timetable/edit":
			showEditForm(request, response);
			break;
			case "/Timetable_App/timetable/update":
			updatetimetable(request, response);
			break;
			default:
			listtimetable(request, response);
			break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
			} 
    	
	}
    
    /**
	 * "/timetable/"
	 */
    private void listtimetable(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException
    {
    	//HttpSession session = request.getSession();
    	 //session.setAttribute("username", "ikrame");
         //session.setAttribute("usertype", "admin");
         //session.setAttribute("id", 1L);
         

    	Timetable timetable = new Timetable();
    	List<Timetable> listTimetable = timetableDao.selectTimetable(timetable);
    	request.setAttribute("listTimetable", listTimetable);
    	//System.out.println(listTimetable);
    	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CRUDjsp/timetableList.jsp").forward(request, response);
    }
    
    /**
	 * "/timetable/new"
	 */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException 
    {
    	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CRUDjsp/timetableForm.jsp").forward(request, response);
    }
    
    /**
	 * "/timetable/edit?id=1"
	 */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, ServletException, IOException
    {
    	Long  id = Long .parseLong (request.getParameter("id"));
    	Timetable timetable = new Timetable();
    	timetable.setTimetableId(id);
    	List<Timetable> listtimetable = timetableDao.selectTimetable(timetable);
    	Timetable existingtimetable = listtimetable.get(0);
    	request.setAttribute("timetable", existingtimetable);
    	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CRUDjsp/timetableForm.jsp").forward(request, response);
    }
    
    /**
	 * "/timetable/insert"
	 */
    private void inserttimetable(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException
    {
    	/**
    	 * 
    	private Long id;
		private Long user_fk;           // from session
		private int nb_days;
		private int nb_periods ;
		private int  hours_per_period;
		private String description;
		private String free_time;
		private String summary;*/
    	System.out.println("aweeeleeeeeeeey");
    	 HttpSession session = request.getSession();
         Long user_fk = (Long) session.getAttribute("id");
    	
         System.out.println(user_fk);
         System.out.println("aweeeleeeeeeeey");
    	//int  nb_days = Integer .parseInt (request.getParameter("nb_days"));
    	//int  nb_periods = Integer .parseInt (request.getParameter("nb_periods"));
    	//int  hours_per_period = Integer .parseInt (request.getParameter("hours_per_period"));
		String description = request.getParameter("description");
		String free_time = request.getParameter("free_time");
		//String summary = request.getParameter("summary");
	
	
		Timetable timetable = new Timetable(); 
		
		timetable.setTimetableUserFk(user_fk);
		//timetable.setTimetableNbDays(nb_days);
		//timetable.setTimetableNbPeriods(nb_periods);
		//timetable.setTimetableHoursPerPeriod(hours_per_period);
		timetable.setTimetableDescription(description);
		timetable.setTimetableFreeTime(free_time);
		//timetable.setTimetableSummary(summary);             //??
		
		timetableDao.insertTimetable(timetable);
		
		response.sendRedirect("/Timetable_App/timetable/");
	
    }
    
    /**
	 * "/timetable/update"
	 */
    private void updatetimetable(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException
    {
		Long id = Long.parseLong(request.getParameter("id")); //hidden
		Long user_fk = Long.parseLong(request.getParameter("user_fk")); //hidden
		
    	int  nb_days = Integer .parseInt (request.getParameter("nb_days"));
    	int  nb_periods = Integer .parseInt (request.getParameter("nb_periods"));
    	int  hours_per_period = Integer .parseInt (request.getParameter("hours_per_period"));
		String description = request.getParameter("description");
		String free_time = request.getParameter("free_time");
		String summary = request.getParameter("summary");
	
				
		Timetable timetable = new Timetable( id, description,  user_fk,  free_time,  summary,  nb_days,  nb_periods,   hours_per_period);
		timetableDao.updateTimetable(timetable);
		response.sendRedirect("/Timetable_App/timetable/");
    }
    /**
	 * "/timetable/delete?id=1"
	 */
    private void deletetimetable(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException
    {
		Long id = Long.parseLong(request.getParameter("id"));
		Timetable timetable = new Timetable(id);
		timetableDao.deleteTimetable(timetable);
		response.sendRedirect("/Timetable_App/timetable/");
    }

	

}
