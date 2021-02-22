package databaseService.beans;

public class User implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String user_type;
	private String password;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", user_type=" + user_type + ", password=" + password
				+ "]";
	}

	//Constructeur pour le test de la requette DELETE et SELECT (on n'a besoin que du ID)
	public User(Long id) {
		this.id = id;
	}
	
	//Constructeur pour le test de la requette INSERT (on n'a pas besoin de ID car il est auto incrementer)
	public User(String username, String password, String user_type) {
		this.username = username;
		this.password = password;
		this.user_type = user_type;
	}	
		
	//Constructeur pour le test de la requette UPDATE 
	public User(Long id,String username, String password, String user_type) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.user_type = user_type;
	}
	// Constructeur vide pour avoir la list des resultats de la requete SELECT
	public User() {
		// TODO Auto-generated constructor stub
	}

	public Long getUserID() {
		return id;
	}
	public String getUsername(){
		return username;
	}
	public String getUserType(){
		return user_type;
	}
	public String getPassword(){
		return password;
	}
	public void setId(Long id){
		this.id = id;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public void setUserType(String user_type){
		this.user_type = user_type;
	}
	public void setPassword(String password){
		this.password = password;
	}
}
