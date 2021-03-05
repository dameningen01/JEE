package appJobs.servlets.crud;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseService.beans.Faculty;
import databaseService.dao.facultyDao.FacultyDao;
import databaseService.dao.facultyDao.IntFacultyDao;



/**
 * Servlet implementation class facultyCRUD
 */

public class CRUDfaculty extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDfaculty() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private IntFacultyDao facultyDao ;
    
    public void init() throws ServletException {
    	facultyDao = new FacultyDao();
    	
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
			case "/faculty/new":
			showNewForm(request, response);
			break;
			case "/faculty/insert":
			insertfaculty(request, response);
			break;
			case "/faculty/delete":
			deletefaculty(request, response);
			break;
			case "/faculty/edit":
			showEditForm(request, response);
			break;
			case "/faculty/update":
			updatefaculty(request, response);
			break;
			default:
			listfaculty(request, response);
			break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
			}
	}
    
    /**
	 * "/faculty/"
	 */
    private void listfaculty(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException
    {
    	Faculty fac = new Faculty();
    	List<Faculty> listFaculty = facultyDao.selectFaculty(fac);
    	request.setAttribute("listFaculty", listFaculty);
    	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CRUDjsp/facultyList.jsp").forward(request, response);
    }
    
    /**
	 * "/faculty/new"
	 */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException 
    {
    	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CRUDjsp/facultyForm.jsp").forward(request, response);
    }
    
    /**
	 * "/faculty/edit?id=1"
	 */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, ServletException, IOException
    {
    	Long  id = Long .parseLong (request.getParameter("id"));
    	Faculty fac = new Faculty();
    	fac.setFacultyId(id);
    	List<Faculty> listFaculty = facultyDao.selectFaculty(fac);
    	Faculty existingfaculty = listFaculty.get(0);
    	request.setAttribute("faculty", existingfaculty);
    	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CRUDjsp/facultyForm.jsp").forward(request, response);
    }
    
    /**
	 * "/faculty/insert"
	 */
    private void insertfaculty(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException
    {
    	/**
    	 * 
    	private Long id;
		private String name;
		private String abrev;
		private String year;*/
	    	
    	String name = request.getParameter("name");
    	String abrev = request.getParameter("abrev");
		int year = Integer .parseInt ( request.getParameter("year"));
	
		Faculty fac = new Faculty();
		
		fac.setFacultyAbrev(abrev);
		fac.setFacultyName(name);
		fac.setFacultyYear(year);
		
		facultyDao.insertFaculty(fac);
		
		response.sendRedirect("/Timetable_App/faculty/");
	
    }
    
    /**
	 * "/faculty/update"
	 */
    private void updatefaculty(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException
    {
		Long id = Long.parseLong(request.getParameter("id")); //hidden
		
		String name = request.getParameter("name");
    	String abrev = request.getParameter("abrev");
    	int year = Integer .parseInt ( request.getParameter("year"));
				
		Faculty fac = new Faculty(id, name , abrev, year);
		facultyDao.updateFaculty(fac);
		response.sendRedirect("/Timetable_App/faculty/");
    }
    /**
	 * "/faculty/delete?id=1"
	 */
    private void deletefaculty(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException
    {
		Long id = Long.parseLong(request.getParameter("id"));
		Faculty fac = new Faculty(id);
		facultyDao.deleteFaculty(fac);
		response.sendRedirect("/Timetable_App/faculty/");
    }


	
}
