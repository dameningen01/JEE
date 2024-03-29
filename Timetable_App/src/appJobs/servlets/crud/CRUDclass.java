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
import databaseService.beans.Faculty;
import databaseService.dao.classDao.ClassDao;
import databaseService.dao.classDao.IntClassDao;
import databaseService.dao.facultyDao.FacultyDao;
import databaseService.dao.facultyDao.IntFacultyDao;

/**
 * Servlet implementation class classCRUD
 */

public class CRUDclass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDclass() {
        super();
        // TODO Auto-generated constructor stub
    }

private IntClassDao classDao ;
    
    public void init() throws ServletException {
    	classDao = new ClassDao();
    	
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
   	 }else {
    	
    	String action = request.getRequestURI();
    	//System.out.println(action);
    	try {
			switch (action) {
			case "/Timetable_App/class/new":
			showNewForm(request, response);
			break;
			case "/Timetable_App/class/insert":
			insertclass(request, response);
			break;
			case "/Timetable_App/class/delete":
			deleteclass(request, response);
			break;
			case "/Timetable_App/class/edit":
			showEditForm(request, response);
			break;
			case "/Timetable_App/class/update":
			updateclass(request, response);
			break;
			case "/Timetable_App/class/":
			listclass(request, response);
			break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
			}
   	 }
   	 
	}
    
    /**
	 * "/class/"
	 */
    private void listclass(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException
    {
    	Class cl = new Class();
    	List<Class> listClass = classDao.selectClass(cl);
    	request.setAttribute("listClass", listClass);
    	System.out.println(listClass);
    	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CRUDjsp/classList.jsp").forward(request, response);
    }
    
    /**
	 * "/class/new"
	 */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException 
    {
    	Faculty faculty = new Faculty();
    	IntFacultyDao facultyDao = new FacultyDao();
    	List<Faculty> listFaculties = facultyDao.selectFaculty(faculty);
    	request.setAttribute("listFaculties", listFaculties);
    	
    	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CRUDjsp/classForm.jsp").forward(request, response);
    }
    
    /**
	 * "/class/edit?id=1"
	 */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, ServletException, IOException
    {
    	
    	Long  id = Long .parseLong (request.getParameter("id"));
    	Class cl = new Class();
    	cl.setClassId(id);
    	List<Class> listClass = classDao.selectClass(cl);
    	Class existingclass= listClass.get(0);
    	request.setAttribute("cl", existingclass);
    	
    	
    	Faculty faculty = new Faculty();
    	IntFacultyDao facultyDao = new FacultyDao();
    	List<Faculty> listFaculties = facultyDao.selectFaculty(faculty);
    	request.setAttribute("listFaculties", listFaculties);
    	
    	
    	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CRUDjsp/classForm.jsp").forward(request, response);
    }
    
    /**
	 * "/class/insert"
	 */
    private void insertclass(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException
    {
    	/**
    	 * 
    	private Long id;
		private Long faculty_fk;
		private Integer group;
		private String free_time;
		private String color;*/
    	
    	Long  faculty_fk = Long .parseLong (request.getParameter("faculty_fk"));	
    	int group = Integer.parseInt(request.getParameter("group"));
    	String free_time = request.getParameter("free_time");
		String color = request.getParameter("color");
	
		Class cl = new Class();
		
		cl.setClassColor(color);
		cl.setClassFacultyFk(faculty_fk);
		cl.setClassFreeTime(free_time);
		cl.setClassGroup(group);
		
		classDao.insertClass(cl);
		
		response.sendRedirect("/Timetable_App/class/");
	
    }
    
    /**
	 * "/class/update"
	 */
    private void updateclass(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException
    {
		Long id = Long.parseLong(request.getParameter("id")); //hidden
		
		Long  faculty_fk = Long .parseLong (request.getParameter("faculty_fk"));	
    	int group = Integer.parseInt(request.getParameter("group"));
    	String free_time = request.getParameter("free_time");
		String color = request.getParameter("color");
				
		Class cl = new Class(id, faculty_fk , group, free_time,color);
		classDao.updateClass(cl);
		response.sendRedirect("/Timetable_App/class/");
    }
    /**
	 * "/class/delete?id=1"
	 */
    private void deleteclass(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException
    {
		Long id = Long.parseLong(request.getParameter("id"));
		Class cl = new Class(id);
		classDao.deleteClass(cl);
		response.sendRedirect("/Timetable_App/class/");
    }


	
}
