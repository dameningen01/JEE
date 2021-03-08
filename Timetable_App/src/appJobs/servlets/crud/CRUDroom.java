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

import databaseService.beans.Room;
import databaseService.dao.roomDao.IntRoomDao;
import databaseService.dao.roomDao.RoomDao;

/**
 * Servlet implementation class CRUDroom
 */

public class CRUDroom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDroom() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private IntRoomDao roomDao ;
    
    public void init() throws ServletException {
    	roomDao = new RoomDao();
    	
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
			case "/Timetable_App/room/new":
			showNewForm(request, response);
			break;
			case "/Timetable_App/room/insert":
			insertroom(request, response);
			break;
			case "/Timetable_App/room/delete":
			deleteroom(request, response);
			break;
			case "/Timetable_App/room/edit":
			showEditForm(request, response);
			break;
			case "/Timetable_App/room/update":
			updateroom(request, response);
			break;
			case "/Timetable_App/room/":
			listroom(request, response);
			break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
			}
    	 }
	}
    
    /**
	 * "/room/"
	 */
    private void listroom(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException
    {
    	Room room = new Room();
    	List<Room> listRoom = roomDao.selectRoom(room);
    	request.setAttribute("listRoom", listRoom);
    	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CRUDjsp/roomList.jsp").forward(request, response);
    }
    
    /**
	 * "/room/new"
	 */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException 
    {
    	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CRUDjsp/roomForm.jsp").forward(request, response);
    }
    
    /**
	 * "/room/edit?id=1"
	 */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, ServletException, IOException
    {
    	Long  id = Long .parseLong (request.getParameter("id"));
    	Room room = new Room();
    	room.setRoomId(id);
    	List<Room> listRoom = roomDao.selectRoom(room);
    	Room existingroom = listRoom.get(0);
    	request.setAttribute("room", existingroom);
    	this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CRUDjsp/roomForm.jsp").forward(request, response);
    }
    
    /**
	 * "/room/insert"
	 */
    private void insertroom(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException
    {
    	/**
    	 * 
    	private Long id;
		private String abrev;
		private String free_time;
		private String color;*/
	    	
		String abrev = request.getParameter("abrev");
		String free_time = request.getParameter("free_time");
		String color = request.getParameter("color");
	
		
		Room room = new Room(); 
		
		room.setRoomAbrev(abrev);
		room.setRoomColor(color);
		room.setRoomFreeTime(free_time);
		
		roomDao.insertRoom(room);
		
		response.sendRedirect("/Timetable_App/room/");
	
    }
    
    /**
	 * "/room/update"
	 */
    private void updateroom(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException
    {
		Long id = Long.parseLong(request.getParameter("id")); //hidden
		
		String abrev = request.getParameter("abrev");
		String free_time = request.getParameter("free_time");
		String color = request.getParameter("color");
				
		Room room = new Room(id, abrev , free_time, color);
		roomDao.updateRoom(room);
		response.sendRedirect("/Timetable_App/room/");
    }
    /**
	 * "/room/delete?id=1"
	 */
    private void deleteroom(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException
    {
		Long id = Long.parseLong(request.getParameter("id"));
		Room room = new Room(id);
		roomDao.deleteRoom(room);
		response.sendRedirect("/Timetable_App/room/");
    }

	

}
