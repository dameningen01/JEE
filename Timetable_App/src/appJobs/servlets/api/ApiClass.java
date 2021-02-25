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

import databaseService.beans.Class;
import databaseService.dao.classDao.ClassDao;
import databaseService.dao.classDao.IntClassDao;

/**
 * Servlet implementation class ApiClass
 */

public class ApiClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApiClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
     private IntClassDao classDao;
	
    public void init() throws ServletException {
    	classDao = new ClassDao();
    }

         //private Gson gson = new Gson();
         
    //'api/class/?faculty = 1'
         
         
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Class> listOfClasses;
		
		Class c = new Class();

		if(request.getParameter("faculty") != null)
		{ 
			Long faculty = Long.parseLong(request.getParameter("faculty"));
			c.setClassFacultyFk(faculty);
			listOfClasses = classDao.selectClass(c);
			//System.out.print(listOfClasses);
			
			String classesJsonString = new Gson().toJson(listOfClasses);
			//System.out.println(classesJsonString);

	        PrintWriter out = response.getWriter();
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        out.print(classesJsonString);
	        //out.print(" {\"ez\":69} ");
	        
	        
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
