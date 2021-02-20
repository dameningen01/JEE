package databaseService.dao.roomDao;

import java.util.List;

import databaseService.beans.Room;

public interface IntRoomDao {
	public boolean insertRoom(Room rm);
	public boolean deleteRoom(Room rm);
	public boolean updateRoom(Room rm);
	public List<Room> selectRoom(Room rm);
}
