package databaseService.dao.roomDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databaseService.beans.Room;
import databaseService.dao.user.dao.ConnectionManager;

public class RoomDao implements IntRoomDao{

	@Override
	public boolean insertRoom(Room rm) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "INSERT INTO ROOM (abrev,free_time,color)VALUES ('"+rm.getRoomAbrev()+"','"+rm.getRoomFreeTime()+"','"+rm.getRoomColor()+"')";
			Statement st = c.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("statement failed to parse");
			e.printStackTrace();
		}finally {
			cm.closeConnection(c);
		}
		return false;
	}

	@Override
	public boolean deleteRoom(Room rm) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "DELETE FROM ROOM WHERE ID = '"+rm.getRoomId()+"' ";
			Statement st = c.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("statement failed to parse");
			e.printStackTrace();
		}finally {
			cm.closeConnection(c);
		}
		return false;
	}

	@Override
	public boolean updateRoom(Room rm) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "UPDATE ROOM SET ABREV = '"+rm.getRoomAbrev()+"' , FREE_TIME = '"+rm.getRoomFreeTime()+"' , COLOR = '"+rm.getRoomColor()+"' WHERE ID = '"+rm.getRoomId()+"' ";
			Statement st = c.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("statement failed to parse");
			e.printStackTrace();
		}finally {
			cm.closeConnection(c);
		}
		return false;
	}

	@Override
	public List<Room> selectRoom(Room rm) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		List<Room> list = new ArrayList<Room>();
		try {
			c = cm.openConnection();
			String sql = "SELECT * FROM ROOM WHERE 1=1 ";
			if(rm.getRoomId() != null) {
				sql+= " AND ID = " + rm.getRoomId();
			}
			if(rm.getRoomAbrev() != null) {
				sql+= " AND ABREV = '" + rm.getRoomAbrev()+ "'";
			}
			if(rm.getRoomFreeTime() != null) {
				sql+= " AND FREE_TIME = '" + rm.getRoomFreeTime()+ "'";
			}
			if(rm.getRoomColor() != null) {
				sql+= " AND COLOR = '" + rm.getRoomColor()+ "'";
			}
			
			Statement st = c.createStatement();
			ResultSet resultats = st.executeQuery(sql);
			while(resultats.next()) {
				Room rml = new Room();
				rml.setRoomId(resultats.getLong("Id"));
				rml.setRoomAbrev(resultats.getString("abrev"));
				rml.setRoomFreeTime(resultats.getString("free_time"));
				rml.setRoomColor(resultats.getString("color"));
				
				list.add(rml);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("statement failed to parse");
			e.printStackTrace();
		}finally {
			cm.closeConnection(c);
		}
		return list;
	}

}
