package databaseService.dao.professeurDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databaseService.beans.Teacher;
import databaseService.dao.user.dao.ConnectionManager;

public class TeacherDao implements IntTeacherDao{

	@Override
	public boolean insertTeacher(Teacher p) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "INSERT INTO TEACHER (name,free_time,color,user_id)VALUES ('"+p.getTeacherName()+"','"+p.getTeacherFreeTime()+"','"+p.getTeacherColor()+"','"+p.getTeacherUserFk()+"')";
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
	public boolean deleteTeacher(Teacher p) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "DELETE FROM TEACHER WHERE ID = '"+p.getTeacherId()+"' ";
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
	public boolean updateTeacher(Teacher p) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "UPDATE TEACHER SET NAME = '"+p.getTeacherName()+"' , FREE_TIME = '"+p.getTeacherFreeTime()+"' , USER_ID = '"+p.getTeacherUserFk()+"' , COLOR = '"+p.getTeacherColor()+"' WHERE ID = '"+p.getTeacherId()+"' ";
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
	public List<Teacher> selectTeacher(Teacher p) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		List<Teacher> list = new ArrayList<Teacher>();
		try {
			c = cm.openConnection();
			String sql = "SELECT * FROM TEACHER WHERE 1=1 ";
			if(p.getTeacherId() != null) {
				sql+= " AND ID = " + p.getTeacherId();
			}
			if(p.getTeacherName() != null) {
				sql+= " AND NAME = '" + p.getTeacherName()+ "'";
			}
			if(p.getTeacherFreeTime() != null) {
				sql+= " AND FREE_TIME = '" + p.getTeacherFreeTime()+ "'";
			}
			if(p.getTeacherUserFk() != null) {
				sql+= " AND USER_ID = '" + p.getTeacherUserFk()+ "'";
			}
			if(p.getTeacherColor() != null) {
				sql+= " AND COLOR = '" + p.getTeacherColor()+ "'";
			}
			
			Statement st = c.createStatement();
			ResultSet resultats = st.executeQuery(sql);
			while(resultats.next()) {
				Teacher pl = new Teacher();
				pl.setTeacherId(resultats.getLong("Id"));
				pl.setTeacherName(resultats.getString("name"));
				pl.setTeacherFreeTime(resultats.getString("free_time"));
				pl.setTeacherUserFk(resultats.getLong("user_id"));
				pl.setTeacherColor(resultats.getString("color"));
				
				list.add(pl);
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
