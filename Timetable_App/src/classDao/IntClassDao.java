package classDao;

import java.util.List;
import databaseService.beans.Class;

public interface IntClassDao {
	public boolean insertClass(Class cl);
	public boolean deleteClass(Class cl);
	public boolean updateClass(Class cl);
	public List<Class> selectClass(Class cl);
}
