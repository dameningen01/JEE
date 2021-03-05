package appJobs.servlets.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import databaseService.beans.Faculty;
import databaseService.dao.facultyDao.FacultyDao;
import databaseService.dao.facultyDao.IntFacultyDao;



/**
 * Servlet implementation class ApiFaculty
 */

public class ApiFaculty extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApiFaculty() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private IntFacultyDao facultyDao;
	
    public void init() throws ServletException {
    	facultyDao = new FacultyDao();
    }

         private Gson gson = new Gson();
         
    // '/api/faculty/' and /api/faculty/?year = 1A'
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		List<Faculty> listOfFaculties;
		Faculty faculty1;
		Faculty faculty2;
		
		if(request.getParameter("year") == null)
		{ 
			 faculty1 = new Faculty();	
			 listOfFaculties = facultyDao.selectFaculty(faculty1);
		}
		else
		{
			int year = Integer .parseInt ( request.getParameter("year"));
			//String year = request.getParameter("year");
			faculty2 = new Faculty();
			faculty2.setFacultyYear(year);
			
			listOfFaculties = facultyDao.selectFaculty(faculty2);
		}

        String facultiesJsonString = this.gson.toJson(listOfFaculties);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(facultiesJsonString);
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
