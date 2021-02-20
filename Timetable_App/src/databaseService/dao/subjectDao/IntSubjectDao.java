package databaseService.dao.subjectDao;

import java.util.List;

import databaseService.beans.Subject;

public interface IntSubjectDao {
	public boolean insertSubject(Subject sb);
	public boolean deleteSubject(Subject sb);
	public boolean updateSubject(Subject sb);
	public List<Subject> selectSubject(Subject sb);
}
