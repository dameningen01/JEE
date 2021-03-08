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

import databaseService.beans.Class;
import databaseService.beans.Lesson;
import databaseService.beans.Room;
import databaseService.beans.Subject;
import databaseService.beans.Teacher;
import databaseService.beans.Timetable;
import databaseService.dao.classDao.ClassDao;
import databaseService.dao.classDao.IntClassDao;
import databaseService.dao.facultyDao.FacultyDao;
import databaseService.dao.facultyDao.IntFacultyDao;
import databaseService.dao.lessonDao.IntLessonDao;
import databaseService.dao.lessonDao.LessonDao;
import databaseService.dao.professeurDao.IntTeacherDao;
import databaseService.dao.professeurDao.TeacherDao;
import databaseService.dao.roomDao.IntRoomDao;
import databaseService.dao.roomDao.RoomDao;
import databaseService.dao.subjectDao.IntSubjectDao;
import databaseService.dao.subjectDao.SubjectDao;
import databaseService.dao.timetableDao.IntTimetableDao;
import databaseService.dao.timetableDao.TimetableDao;

/**
 * Servlet implementation class lessonCRUD
 */

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
    	HttpSession session = request.getSession();
     	 
     	 if(session.getAttribute("id")== null) 
     	 {
     		 response.sendRedirect("/Timetable_App/");
     	 }
     	 else if(!session.getAttribute("usertype").equals("admin")) 
     	 {
     		 response.sendRedirect("/Timetable_App/");
     	 }
    	String action = request.getRequestURI();
    	try {
			switch (action) {
			case "/Timetable_App/lesson/new":
			showNewForm(request, response);
			break;
			case "/Timetable_App/lesson/insert":
			insertlesson(request, response);
			break;
			case "/Timetable_App/lesson/delete":
			deletelesson(request, response);
			break;
			case "/Timetable_App/lesson/edit":
			showEditForm(request, response);
			break;
			case "/Timetable_App/lesson/update":
			updatelesson(request, response);
			break;
			case "/Timetable_App/lesson":
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
    	List<Lesson> listlesson = lessonDao.selectDetailsLesson(lesson);
    	//System.out.println(listlesson);
    	request.setAttribute("listlesson", listlesson);
    	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CRUDjsp/lessonList.jsp").forward(request, response);
    }
    
    /**
	 * "/lesson/new"
	 */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException 
    {
    	Teacher teacher = new Teacher();
    	IntTeacherDao teacherDao = new TeacherDao();
    	List<Teacher> listTeacher = teacherDao.selectTeacher(teacher);
    	request.setAttribute("listTeacher", listTeacher);
    	
    	Class cl = new Class();
    	IntClassDao classDao = new ClassDao();
    	List<Class> listClass = classDao.selectClass(cl);
    	request.setAttribute("listClass", listClass);
    	
    	Room rooom = new Room();
    	IntRoomDao roomDao = new RoomDao();
    	List<Room> listRoom = roomDao.selectRoom(rooom);
    	request.setAttribute("listRoom", listRoom);
    	
    	Subject subject = new Subject();
    	IntSubjectDao subjectDao = new SubjectDao();
    	List<Subject> listSubject = subjectDao.selectSubject(subject);
    	request.setAttribute("listSubject", listSubject);
    	
    	Timetable timetable = new Timetable();
    	IntTimetableDao timetableDao = new TimetableDao();
    	List<Timetable> listTimetable = timetableDao.selectTimetable(timetable);
    	request.setAttribute("listTimetable", listTimetable);
    	
    	
    	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CRUDjsp/lessonForm.jsp").forward(request, response);
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
    	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CRUDjsp/lessonForm.jsp").forward(request, response);
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
