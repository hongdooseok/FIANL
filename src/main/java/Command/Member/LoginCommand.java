package Command.Member;

public class LoginCommand {
	private String id1;
	private boolean idStore;
	private boolean autoLogin;
	private String pw;
	
	public String getId1() {
		return id1;
	}
	public void setId1(String id1) {
		this.id1 = id1;
	}
	public boolean getIdStore() {
		return idStore;
	}
	public void setIdStore(boolean idStore) {
		this.idStore = idStore;
	}
	public boolean getAutoLogin() {
		return autoLogin;
	}
	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
}
