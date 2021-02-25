package appJobs.servlets.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import databaseService.beans.Teacher;
import databaseService.dao.professeurDao.IntTeacherDao;
import databaseService.dao.professeurDao.TeacherDao;

/**
 * Servlet implementation class ApiProf
 */

public class ApiProf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApiProf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private IntTeacherDao teacherDao;
	
    public void init() throws ServletException {
    	teacherDao = new TeacherDao();
    }

     private Gson gson = new Gson();
         
    // '/api/prof/?user=1'
         
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        List<Teacher> listOfTeachers;
		
        Teacher t = new Teacher();

		if(request.getParameter("user") != null)
		{ 
			HttpSession session = request.getSession();
			if(session.getAttribute("id") != null)   // user must be an admin or teacher
			{
				Long user = Long.parseLong(request.getParameter("user"));
				t.setTeacherUserFk(user);
				
				listOfTeachers = teacherDao.selectTeacher(t);
				
				Teacher teacher = listOfTeachers.get(0);
				
				String teacherJsonString = this.gson.toJson(teacher);

		        PrintWriter out = response.getWriter();
		        response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        out.print(teacherJsonString);
		        out.flush(); 
			}
			
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
