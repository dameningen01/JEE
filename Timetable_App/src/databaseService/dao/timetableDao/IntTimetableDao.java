package databaseService.dao.timetableDao;

import java.util.List;

import databaseService.beans.Timetable;

public interface IntTimetableDao {
	public boolean insertTimetable(Timetable tb);
	public boolean deleteTimetable(Timetable tb);
	public boolean updateTimetable(Timetable tb);
	public List<Timetable> selectTimetable(Timetable tb);
	public List<Timetable> selectUniqueTimetable(Timetable tb);
}
