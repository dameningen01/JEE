package databaseService.dao.user.dao;
import java.util.List;

import databaseService.beans.User;

public interface IntUserDao {
	public boolean insertUser(User u);
	public boolean deleteUser(User u);
	public boolean updateUser(User u);
	public List<User> selectUser(User u);
}
