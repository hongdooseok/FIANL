package Model.DTO;

//이거는 로그인 정보를 저장하기 위한 클래스야 (세션을 하겠다는 소리야)
public class AuthInfo {

	private String id;
	
	private String email;
	private String name;
	private String pw;
	
	public AuthInfo() {
		
	}
	
	
	public AuthInfo(String id, String email, String name, String pw) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.pw = pw;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
	public String getPw() {
		return pw;
	}
	
}
