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

import databaseService.beans.Subject;
import databaseService.dao.subjectDao.IntSubjectDao;
import databaseService.dao.subjectDao.SubjectDao;

/**
 * Servlet implementation class CRUDsubject
 */

public class CRUDsubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDsubject() {
        super();
        // TODO Auto-generated constructor stub
    }
private IntSubjectDao subjectDao ;
    
    public void init() throws ServletException {
    	subjectDao = new SubjectDao();
    	
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
   	 
   	else {
    	String action = request.getRequestURI();
    	try {
			switch (action) {
			case "/Timetable_App/subject/new":
			showNewForm(request, response);
			break;
			case "/Timetable_App/subject/insert":
			insertsubject(request, response);
			break;
			case "/Timetable_App/subject/delete":
			deletesubject(request, response);
			break;
			case "/Timetable_App/subject/edit":
			showEditForm(request, response);
			break;
			case "/Timetable_App/subject/update":
			updatesubject(request, response);
			break;
			case "/Timetable_App/subject" :
			listsubject(request, response);
			break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
			}
   		}
	}
    
    /**
	 * "/subject/"
	 */
    private void listsubject(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException
    {
    	Subject subject = new Subject();
    	List<Subject> listSubject = subjectDao.selectSubject(subject);
    	request.setAttribute("listSubject", listSubject);
    	//System.out.println(listSubject);
    	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CRUDjsp/subjectList.jsp").forward(request, response);
    }
    
    /**
	 * "/subject/new"
	 */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException 
    {
    	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CRUDjsp/subjectForm.jsp").forward(request, response);
    }
    
    /**
	 * "/subject/edit?id=1"
	 */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, ServletException, IOException
    {
    	Long  id = Long .parseLong (request.getParameter("id"));
    	Subject subject = new Subject();
    	subject.setSubjectId(id);
    	List<Subject> listsubject = subjectDao.selectSubject(subject);
    	Subject existingsubject = listsubject.get(0);
    	request.setAttribute("subject", existingsubject);
    	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CRUDjsp/subjectForm.jsp").forward(request, response);
    }
    
    /**
	 * "/subject/insert"
	 */
    private void insertsubject(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException
    {
    	/**
    	 * 
    	private Long id;
		private String module;
		private String submodule;
		private String type;
		private String abrev;
		private String color;*/
	    
    	String module = request.getParameter("module");
    	String submodule = request.getParameter("submodule");
    	String type = request.getParameter("type");
		String abrev = request.getParameter("abrev");
		String color = request.getParameter("color");
	
		
		Subject subject = new Subject(); 
		
		subject.setSubjectAbrev(abrev);
		subject.setSubjectColor(color);
		subject.setSubjectModule(module);
		subject.setSubjectSubmodule(submodule);
		subject.setSubjectType(type);
		
		subjectDao.insertSubject(subject);
		
		response.sendRedirect("/Timetable_App/subject/");
	
    }
    
    /**
	 * "/subject/update"
	 */
    private void updatesubject(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException
    {
		Long id = Long.parseLong(request.getParameter("id")); //hidden
		
		String module = request.getParameter("module");
    	String submodule = request.getParameter("submodule");
    	String type = request.getParameter("type");
		String abrev = request.getParameter("abrev");
		String color = request.getParameter("color");
	
		Subject subject = new Subject(id, module , submodule,type,abrev, color);
		subjectDao.updateSubject(subject);
		response.sendRedirect("/Timetable_App/subject/");
    }
    /**
	 * "/subject/delete?id=1"
	 */
    private void deletesubject(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException
    {
		Long id = Long.parseLong(request.getParameter("id"));
		Subject subject = new Subject(id);
		subjectDao.deleteSubject(subject);
		response.sendRedirect("/Timetable_App/subject/");
    }

	

}
