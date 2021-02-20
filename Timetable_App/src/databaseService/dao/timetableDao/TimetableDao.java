package databaseService.dao.timetableDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import databaseService.beans.Timetable;
import databaseService.dao.user.dao.ConnectionManager;

public class TimetableDao implements IntTimetableDao{

	@Override
	public boolean insertTimetable(Timetable tb) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "INSERT INTO TIMETABLE (user_id, description, nb_days, nb_periods, hours_per_period, free_time, summary) VALUES ('"+tb.getTimetableUserFk()+"','"+tb.getTimetableDescription()+"','"+tb.getTimetableNbDays()+"','"+tb.getTimetableNbPeriods()+"','"+tb.getTimetableHoursPerPeriod()+"','"+tb.getTimetableFreeTime()+"','"+tb.getTimetableSummary()+"')";
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
	public boolean deleteTimetable(Timetable tb) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "DELETE FROM TIMETABLE WHERE ID = '"+tb.getTimetableId()+"' ";
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
	public boolean updateTimetable(Timetable tb) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		try {
			c = cm.openConnection();
			String sql = "UPDATE TIMETABLE SET USER_ID = '"+tb.getTimetableUserFk()+"' , DESCRIPTION = '"+tb.getTimetableDescription()+"' , NB_DAYS = '"+tb.getTimetableNbDays()+"' , NB_PERIODS = '"+tb.getTimetableNbPeriods()+"' , HOURS_PER_PERIOD = '"+tb.getTimetableHoursPerPeriod()+"' , FREE_TIME = '"+tb.getTimetableFreeTime()+"' , SUMMARY = '"+tb.getTimetableSummary()+"' WHERE ID = '"+tb.getTimetableId()+"' ";
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
	public List<Timetable> selectTimetable(Timetable tb) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		List<Timetable> list = new ArrayList<Timetable>();
		try {
			c = cm.openConnection();
			String sql = "SELECT * FROM TIMETABLE WHERE 1=1 ";
			if(tb.getTimetableId() != null) {
				sql+= " AND ID = " + tb.getTimetableId();
			}
			if(tb.getTimetableUserFk() != null) {
				sql+= " AND USER_ID = " + tb.getTimetableUserFk();
			}
			if(tb.getTimetableDescription() != null) {
				sql+= " AND DESCRIPTION = '" + tb.getTimetableDescription()+ "'";
			}
			if(tb.getTimetableNbDays() != 0) {
				sql+= " AND NB_DAYS = '" + tb.getTimetableNbDays()+ "'";
			}
			if(tb.getTimetableHoursPerPeriod() != 0) {
				sql+= " AND  HOURS_PER_PERIOD = '" + tb.getTimetableHoursPerPeriod()+ "'";
			}
			if(tb.getTimetableNbPeriods() != 0) {
				sql+= " AND NB_PERIODS = '" + tb.getTimetableNbPeriods()+ "'";
			}
			if(tb.getTimetableFreeTime() != null) {
				sql+= " AND FREE_TIME = '" + tb.getTimetableFreeTime()+ "'";
			}
			if(tb.getTimetableSummary() != null) {
				sql+= " AND SUMMARY = '" + tb.getTimetableSummary()+ "'";
			}
			
			Statement st = c.createStatement();
			ResultSet resultats = st.executeQuery(sql);
			while(resultats.next()) {
				Timetable tbl = new Timetable();
				tbl.setTimetableId(resultats.getLong("Id"));
				tbl.setTimetableUserFk(resultats.getLong("user_id"));
				tbl.setTimetableDescription(resultats.getString("description"));
				tbl.setTimetableNbDays(resultats.getInt("nb_days"));
				tbl.setTimetableNbPeriods(resultats.getInt("nb_periods"));
				tbl.setTimetableHoursPerPeriod(resultats.getInt("hours_per_period"));
				tbl.setTimetableFreeTime(resultats.getString("free_time"));
				tbl.setTimetableSummary(resultats.getString("summary"));
				
				list.add(tbl);
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

	@Override
	public List<Timetable> selectUniqueTimetable(Timetable tb) {
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection c = null;
		List<Timetable> list = new ArrayList<Timetable>();
		try {
			c = cm.openConnection();
			String sql = "SELECT DISTINCT * FROM TIMETABLE WHERE 1=1 ";
			if(tb.getTimetableUserFk() != null) {
				sql+= " AND USER_ID = " + tb.getTimetableId();
			}
			
			Statement st = c.createStatement();
			ResultSet resultats = st.executeQuery(sql);
			while(resultats.next()) {
				Timetable tbl = new Timetable();
				tbl.setTimetableId(resultats.getLong("Id"));
				tbl.setTimetableUserFk(resultats.getLong("user_id"));
				tbl.setTimetableDescription(resultats.getString("description"));
				tbl.setTimetableNbDays(resultats.getInt("nb_days"));
				tbl.setTimetableNbPeriods(resultats.getInt("nb_periods"));
				tbl.setTimetableHoursPerPeriod(resultats.getInt("hours_per_period"));
				tbl.setTimetableFreeTime(resultats.getString("free_time"));
				tbl.setTimetableSummary(resultats.getString("summary"));
				
				list.add(tbl);
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
