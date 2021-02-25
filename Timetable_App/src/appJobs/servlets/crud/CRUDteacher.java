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

import databaseService.beans.Teacher;
import databaseService.beans.User;
import databaseService.dao.professeurDao.IntTeacherDao;
import databaseService.dao.professeurDao.TeacherDao;
import databaseService.dao.user.dao.IntUserDao;
import databaseService.dao.user.dao.UserDao;

/**
 * Servlet implementation class CRUDteacher
 */

public class CRUDteacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDteacher() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private IntTeacherDao teacherDao ;
    private IntUserDao userDao ;
    
    public void init() throws ServletException {
    	teacherDao = new TeacherDao();
    	userDao = new UserDao();
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
			case "/teacher/new":
			showNewForm(request, response);
			break;
			case "/teacher/insert":
			insertteacher(request, response);
			break;
			case "/teacher/delete":
			deleteteacher(request, response);
			break;
			case "/teacher/edit":
			showEditForm(request, response);
			break;
			case "/teacher/update":
			updateteacher(request, response);
			break;
			default:
			listteacher(request, response);
			break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
			}
	}
    
    /**
	 * "/teacher/"
	 */
    private void listteacher(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException
    {
    	Teacher t = new Teacher();
    	List<Teacher> listTeacher = teacherDao.selectTeacher(t);
    	request.setAttribute("listTeacher", listTeacher);
    	this.getServletContext().getRequestDispatcher("     ").forward(request, response);
    }
    
    /**
	 * "/teacher/new"
	 */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException 
    {
    	this.getServletContext().getRequestDispatcher("     ").forward(request, response);
    }
    
    /**
	 * "/teacher/edit?id=1"
	 */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, ServletException, IOException
    {
    	Long  id = Long .parseLong (request.getParameter("id"));
    	Teacher t = new Teacher();
    	t.setTeacherId(id);
    	List<Teacher> listTeacher = teacherDao.selectTeacher(t);
    	Teacher existingteacher = listTeacher.get(0);
    	request.setAttribute("teacher", existingteacher);
    	this.getServletContext().getRequestDispatcher("     ").forward(request, response);
    }
    
    /**
	 * "/teacher/insert"
	 */
    private void insertteacher(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException
    {
    	/**private Long id;
    	private Long user_fk;
    	private String name;
    	private String free_time;
    	private String color;
    	
    	private Long id;
		private String username;
		private String user_type;
		private String password;*/
    	
		String name = request.getParameter("name");
		String free_time = request.getParameter("free_time");
		String color = request.getParameter("color");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User();  //create new user with null usertype
		user.setUsername(username);
		user.setPassword(password);
		user.setPassword("null");
		userDao.insertUser(user);
		
		
		User u = new User(); // search the user with null usertype
		u.setUsername("null");
		List<User> listUser= userDao.selectUser(u);
		
		
		User user1 = listUser.get(0);  //update the usertype and get the id
		User user2 = new User(user1.getUserID(),user1.getUsername(),user1.getPassword(),"prof");
		userDao.updateUser(user2);
		
		Teacher teacher = new Teacher(); //insert the teacher
		teacher.setTeacherUserFk(user1.getUserID());
		teacher.setTeacherColor(color);
		teacher.setTeacherFreeTime(free_time);
		teacher.setTeacherName(name);
		
		teacherDao.insertTeacher(teacher);
		
		response.sendRedirect("/Timetable_App/teacher/");
	
    }
    
    /**
	 * "/teacher/update"
	 */
    private void updateteacher(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException
    {
		Long id = Long.parseLong(request.getParameter("id")); //hidden
		Long user_fk = Long.parseLong(request.getParameter("user_fk"));  //hidden
		String name = request.getParameter("name");
		String free_time = request.getParameter("free_time");
		String color = request.getParameter("color");
				
		Teacher teacher = new Teacher(id, user_fk,name, free_time, color);
		teacherDao.updateTeacher(teacher);
		response.sendRedirect("/Timetable_App/teacher/");
    }
    /**
	 * "/teacher/delete?id=1"
	 */
    private void deleteteacher(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException
    {
		Long id = Long.parseLong(request.getParameter("id"));
		Teacher teacher = new Teacher(id);
		teacherDao.deleteTeacher(teacher);
		response.sendRedirect("/Timetable_App/teacher/");
    }

}
