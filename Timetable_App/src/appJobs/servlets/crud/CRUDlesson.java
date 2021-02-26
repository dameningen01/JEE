package appJobs.servlets.crud;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseService.beans.Lesson;
import databaseService.dao.lessonDao.IntLessonDao;
import databaseService.dao.lessonDao.LessonDao;

/**
 * Servlet implementation class lessonCRUD
 */
@WebServlet("/lesson/*")
public class CRUDlesson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDlesson() {
        super();
        // TODO Auto-generated constructor stub
    }

private IntLessonDao lessonDao ;
    
    public void init() throws ServletException {
    	lessonDao = new LessonDao();
    	
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
    	
    	String action = request.getServletPath();
    	try {
			switch (action) {
			case "/lesson/new":
			showNewForm(request, response);
			break;
			case "/lesson/insert":
			insertlesson(request, response);
			break;
			case "/lesson/delete":
			deletelesson(request, response);
			break;
			case "/lesson/edit":
			showEditForm(request, response);
			break;
			case "/lesson/update":
			updatelesson(request, response);
			break;
			default:
			listlesson(request, response);
			break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
			}
	}
    
    /**
	 * "/lesson/"
	 */
    private void listlesson(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException
    {
    	Lesson lesson = new Lesson();
    	List<Lesson> listlesson = lessonDao.selectLesson(lesson);
    	request.setAttribute("listlesson", listlesson);
    	this.getServletContext().getRequestDispatcher("     ").forward(request, response);
    }
    
    /**
	 * "/lesson/new"
	 */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException 
    {
    	this.getServletContext().getRequestDispatcher("     ").forward(request, response);
    }
    
    /**
	 * "/lesson/edit?id=1"
	 */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, ServletException, IOException
    {
    	Long  id = Long .parseLong (request.getParameter("id"));
    	Lesson lesson = new Lesson();
    	lesson.setLessonId(id);
    	List<Lesson> listlesson = lessonDao.selectLesson(lesson);
    	Lesson existinglesson = listlesson.get(0);
    	request.setAttribute("lesson", existinglesson);
    	this.getServletContext().getRequestDispatcher("     ").forward(request, response);
    }
    
    /**
	 * "/lesson/insert"
	 */
    private void insertlesson(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException
    {
    	/**
    	 * 
    	private Long id;
		private Long teacher_fk;
		private Long class_fk;
		private Long room_fk ;
		private Long  subject_fk;
		private Long timetable_fk;
		private int total_lessons;
		private String lesson_occ;
		private String lesson_link;
		private String color;*/
	    	
    	Long  teacher_fk = Long .parseLong (request.getParameter("teacher_fk"));
    	Long  class_fk = Long .parseLong (request.getParameter("class_fk"));
    	Long  room_fk = Long .parseLong (request.getParameter("room_fk"));
    	Long  subject_fk = Long .parseLong (request.getParameter("subject_fk"));
    	Long  timetable_fk = Long .parseLong (request.getParameter("timetable_fk"));
    	int  total_lessons = Integer .parseInt (request.getParameter("total_lessons"));
		String lesson_occ = request.getParameter("lesson_occ");
		String lesson_link = request.getParameter("lesson_link");
		String color = request.getParameter("color");
	
		
		Lesson lesson = new Lesson(); 
		
		lesson.setLessonTeacherFk(teacher_fk);
		lesson.setLessonClassFk(class_fk);
		lesson.setLessonRoomFk(room_fk);
		lesson.setLessonSubjectFk(subject_fk);
		lesson.setLessonTimetableFk(timetable_fk);
		lesson.setTotalLessons(total_lessons);
		lesson.setLessonOcc(lesson_occ);
		lesson.setLessonLink(lesson_link);
		lesson.setLessonColor(color);
			
		lessonDao.insertLesson(lesson);
		
		response.sendRedirect("/Timetable_App/lesson/");
	
    }
    
    /**
	 * "/lesson/update"
	 */
    private void updatelesson(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException
    {
		Long id = Long.parseLong(request.getParameter("id")); //hidden
		
		Long  teacher_fk = Long .parseLong (request.getParameter("teacher_fk"));
    	Long  class_fk = Long .parseLong (request.getParameter("class_fk"));
    	Long  room_fk = Long .parseLong (request.getParameter("room_fk"));
    	Long  subject_fk = Long .parseLong (request.getParameter("subject_fk"));
    	Long  timetable_fk = Long .parseLong (request.getParameter("timetable_fk"));
    	int  total_lessons = Integer .parseInt (request.getParameter("total_lessons"));
		String lesson_occ = request.getParameter("lesson_occ");
		String lesson_link = request.getParameter("lesson_link");
		String color = request.getParameter("color");
				
		Lesson lesson = new Lesson(id, teacher_fk,  class_fk,  room_fk,  subject_fk,  timetable_fk,  total_lessons,  lesson_occ,  lesson_link,  color);
		lessonDao.updateLesson(lesson);
		response.sendRedirect("/Timetable_App/lesson/");
    }
    /**
	 * "/lesson/delete?id=1"
	 */
    private void deletelesson(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException
    {
		Long id = Long.parseLong(request.getParameter("id"));
		Lesson lesson = new Lesson(id);
		lessonDao.deleteLesson(lesson);
		response.sendRedirect("/Timetable_App/lesson/");
    }

	

}
