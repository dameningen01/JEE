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
import databaseService.beans.Timetable;
import databaseService.dao.lessonDao.IntLessonDao;
import databaseService.dao.lessonDao.LessonDao;
import databaseService.dao.timetableDao.IntTimetableDao;
import databaseService.dao.timetableDao.TimetableDao;

/**
 * Servlet implementation class ApiLesson
 */

public class ApiLesson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApiLesson() {
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
     
 // "/api/lesson/?tt_id=2&class=1" or "/api/lesson/?tt_id=2" or "/api/lesson/?tt_id=2&prof=1"
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**
		 * this function returns a list of lessons ID 
		 */
       //List<Long> lessonsID = new ArrayList<Long>();
		   List<Lesson> listOflessons = new ArrayList<Lesson>() ;
		   
       if(request.getParameter("tt_id") != null)
       {
    	   
    	   Long tt = Long.parseLong(request.getParameter("tt_id"));
    	   Lesson L = new Lesson();
    	   
		   L.setLessonTimetableFk(tt);
    	   
    	   if(request.getParameter("class") != null)  //    ""/api/lesson/?tt_id=2&class=1"
           {
           	
	           	Long cl = Long.parseLong(request.getParameter("class"));
	           	
	           	
	           	L.setLessonClassFk(cl);
	           	//System.out.println(lessonDao.selectLesson(L));
	           	listOflessons = lessonDao.selectLesson(L);
	           	
	           	/*for(Lesson lesson : listOflessons)
	           	{
	           		lessonsID.add(lesson.getLessonId());
	           	}*/
           	
           }
    	   else if(request.getParameter("prof") != null)    //      "/api/lesson/?tt_id=2&prof=1"
           {
        	   Long prof = Long.parseLong(request.getParameter("prof"));
	           	
	           	
	           	L.setLessonTeacherFk(prof);
	           	
	           	listOflessons = lessonDao.selectLesson(L);
	           	
	           	/*for(Lesson lesson : listOflessons)
	           	{
	           		lessonsID.add(lesson.getLessonId());
	           	}*/
           }

           else if(request.getParameter("user") != null)   //  "/api/lesson/?tt_id" !!!!!!!!!!!!!!! mn la session
   			{ 
   			
   				Timetable timetable = new Timetable();
   				timetable.setTimetableId(tt);
   				List<Timetable> listOftimetables;
   				listOftimetables = timetableDao.selectTimetable(timetable);
   				
   				Timetable t = listOftimetables.get(0);
   				
   				HttpSession session = request.getSession();
   			
   				
   				if(t.getTimetableUserFk() == session.getAttribute("id") )   //cheks if timetable with id = tt_id had user_id = session user
   				{
   			
   		           	listOflessons = lessonDao.selectLesson(L);
	   		           	
   		         /*for(Lesson lesson : listOflessons)
		 	           	{
		 	           		lessonsID.add(lesson.getLessonId());
		 	           	}*/
   				}
  
   			}
           //System.out.println(listOflessons);
           String listOflessonsJsonString = this.gson.toJson(listOflessons);
           PrintWriter out = response.getWriter();
           response.setContentType("application/json");
           response.setCharacterEncoding("UTF-8");
           out.print(listOflessonsJsonString);
           out.flush();
   	
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
