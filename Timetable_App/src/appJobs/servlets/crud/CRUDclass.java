package appJobs.servlets.crud;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseService.beans.Class;
import databaseService.dao.classDao.ClassDao;
import databaseService.dao.classDao.IntClassDao;

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
    	
    	String action = request.getServletPath();
    	try {
			switch (action) {
			case "/class/new":
			showNewForm(request, response);
			break;
			case "/class/insert":
			insertclass(request, response);
			break;
			case "/class/delete":
			deleteclass(request, response);
			break;
			case "/class/edit":
			showEditForm(request, response);
			break;
			case "/class/update":
			updateclass(request, response);
			break;
			default:
			listclass(request, response);
			break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
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
    	this.getServletContext().getRequestDispatcher("     ").forward(request, response);
    }
    
    /**
	 * "/class/new"
	 */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException 
    {
    	this.getServletContext().getRequestDispatcher("     ").forward(request, response);
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
    	request.setAttribute("class", existingclass);
    	this.getServletContext().getRequestDispatcher("     ").forward(request, response);
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
