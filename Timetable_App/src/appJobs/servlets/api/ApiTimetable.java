package appJobs.servlets.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import databaseService.beans.Lesson;
import databaseService.beans.Teacher;
import databaseService.beans.Timetable;
import databaseService.dao.lessonDao.IntLessonDao;
import databaseService.dao.lessonDao.LessonDao;
import databaseService.dao.timetableDao.IntTimetableDao;
import databaseService.dao.timetableDao.TimetableDao;


/**
 * Servlet implementation class ApiTimetable
 */

public class ApiTimetable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	

    public ApiTimetable() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private IntLessonDao lessonDao;
    private IntTimetableDao timetableDao;
	
    public void init() throws ServletException {
    	lessonDao = new LessonDao();
    	timetableDao = new TimetableDao();
    }

     private Gson gson = new Gson();
    
	// "/api/timetable/?class=1" or "/api/timetable/?user=1" or "/api/timetable/?prof=1"
     
     
     
    //"/api/timetable/?class=1" or "/api/timetable/?id=1" directly
     
     //or "/api/timetable/(depends on prof or admin)" 
     
  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
       
		/**
		 * this function returns a list of timetables ID 
		 */
       //List<Long> timetablesID = new ArrayList<Long>();
		
		List<Timetable> listOfTimetables = new ArrayList<Timetable>();
  
       if(request.getParameter("class") != null)  //    "/api/timetable/?class=1"
        {
    	   /**
   		 * return all unique timetables from lesson where class_fk = class
   		 */
        	Lesson L = new Lesson();
        	Long cl = Long.parseLong(request.getParameter("class"));
        	L.setLessonClassFk(cl);
        	List<Lesson> listOflessons;
        	listOflessons = lessonDao.selectUniqueLessonTimetable(L);
        	
        	for(Lesson lesson : listOflessons)
        	{
        		Timetable tt = new Timetable();
        		tt.setTimetableId(lesson.getLessonTimetableFk());
        		
        		List<Timetable> l = timetableDao.selectTimetable(tt);
        		
        		Timetable timetable = l.get(0);
        		listOfTimetables.add(timetable);
        	}
        	
        }
       else if(request.getParameter("id") != null)
       {
    	   /**
      		 * return all  timetables from timetable where id = id
      		 */
    	    Timetable t = new Timetable();
			Long id =  Long.parseLong(request.getParameter("id"));
			t.setTimetableId(id);
			listOfTimetables = timetableDao.selectTimetable(t);
    	   
       }

       /* else if(request.getParameter("user") != null)   //  "/api/timetable/?user=1"
		{ 
        	 /**
       		 * return all unique timetables from timetable where user_fk = user
       		 
        	
			HttpSession session = request.getSession();
			if(session.getAttribute("id") != null && session.getAttribute("usertype")== "admin")   // user must be an admin 
			{
				Timetable t = new Timetable();
				Long user = Long.parseLong(request.getParameter("user"));
				t.setTimetableUserFk(user);
				
				listOfTimetables = timetableDao.selectUniqueTimetable(t);
				
				
			}
		}
        /*else if(request.getParameter("prof") != null)    //      "/api/timetable/?prof=1" returns timetables where lesson.proffk = iduser
        {
        	  /**
       		 * return all unique timetables from lesson where prof_fk = prof
       		 
        	HttpSession session = request.getSession();
			if(session.getAttribute("id") != null && session.getAttribute("usertype")== "prof")   // user must be a teacher
			{
				Lesson L = new Lesson();
	        	Long prof = Long.parseLong(request.getParameter("prof"));
	        	L.setLessonTeacherFk(prof);
	        	List<Lesson> listOflessons;
	        	listOflessons = lessonDao.selectUniqueLessonTimetable(L);
	        	
	        	for(Lesson lesson : listOflessons)
	        	{
	        		Timetable tt = new Timetable();
	        		tt.setTimetableId(lesson.getLessonTimetableFk());
	        		
	        		List<Timetable> l = timetableDao.selectTimetable(tt);
	        		
	        		Timetable timetable = l.get(0);
	        		listOfTimetables.add(timetable);
	        	}
			}
        }*/
        else 
        {
        	HttpSession session = request.getSession();
        	if(session.getAttribute("id")!= null)
        	{
        		if(session.getAttribute("usertype")== "prof")
        		{
        			/**
               		 * return all unique timetables from lesson where prof_fk = prof in session
               		 */
        			Lesson L = new Lesson();
    	        	Long prof = (Long) session.getAttribute("id");
    	        	L.setLessonTeacherFk(prof);
    	        	List<Lesson> listOflessons;
    	        	listOflessons = lessonDao.selectUniqueLessonTimetable(L);
    	        	
    	        	for(Lesson lesson : listOflessons)
    	        	{
    	        		Timetable tt = new Timetable();
    	        		tt.setTimetableId(lesson.getLessonTimetableFk());
    	        		
    	        		List<Timetable> l = timetableDao.selectTimetable(tt);
    	        		
    	        		Timetable timetable = l.get(0);
    	        		listOfTimetables.add(timetable);
    	        	}
        		}
        		else if (session.getAttribute("usertype")== "admin")
        		{
        			/**
               		 * return all unique timetables from timetable where user_fk = user in session
               		 */
                	
        			Timetable t = new Timetable();
    				Long user =  (Long) session.getAttribute("id");
    				t.setTimetableUserFk(user);
    				
    				listOfTimetables = timetableDao.selectUniqueTimetable(t);
    				
        		}
        			
        	}
        }
        String listOfTimetablesJsonString = this.gson.toJson(listOfTimetables);
    	PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(listOfTimetablesJsonString);
        out.flush();
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
