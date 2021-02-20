package databaseService.dao.facultyDao;

import java.util.List;

import databaseService.beans.Faculty;

public interface IntFacultyDao {
	public boolean insertFaculty(Faculty f);
	public boolean deleteFaculty(Faculty f);
	public boolean updateFaculty(Faculty f);
	public List<Faculty> selectFaculty(Faculty f);
}
