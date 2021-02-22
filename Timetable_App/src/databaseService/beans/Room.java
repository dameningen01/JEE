package databaseService.beans;

public class Room implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String abrev;
	private String free_time;
	private String color;
	
		//Constructeur pour le test de la requette DELETE et SELECT (on n'a besoin que du ID)
		public Room(Long id) {
			this.id = id;
		}
		
		//Constructeur pour le test de la requette INSERT (on n'a pas besoin de ID car il est auto incrementer)
		public Room(String abrev, String free_time, String color) {
			this.setRoomAbrev(abrev);
			this.free_time = free_time;
			this.color = color;
		}	
			
		//Constructeur pour le test de la requette UPDATE 
		public Room(Long id, String abrev, String free_time, String color) {
			this.id = id;
			this.setRoomAbrev(abrev);
			this.free_time = free_time;
			this.color = color;
		}
		// Constructeur vide pour avoir la list des resultats de la requete SELECT
		public Room() {
			// TODO Auto-generated constructor stub
		}
	
	public Long getRoomId() {
		return id;
	}
	public void setRoomId(Long id) {
		this.id = id;
	}
	public String getRoomFreeTime() {
		return free_time;
	}
	public void setRoomFreeTime(String free_time) {
		this.free_time = free_time;
	}
	public String getRoomColor() {
		return color;
	}
	public void setRoomColor(String color) {
		this.color = color;
	}

	public String getRoomAbrev() {
		return abrev;
	}

	public void setRoomAbrev(String abrev) {
		this.abrev = abrev;
	}
	
}
