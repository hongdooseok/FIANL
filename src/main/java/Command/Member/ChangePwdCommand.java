package Command.Member;

public class ChangePwdCommand {
	private String currentPassword;
	private String newPassword;
	private String reNewPassword;
	
	
	
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getReNewPassword() {
		return reNewPassword;
	}
	public void setReNewPassword(String reNewPassword) {
		this.reNewPassword = reNewPassword;
	}
	
	public boolean isPasswordEqualToConfirmPassword() {
		if(newPassword.equals(reNewPassword)) {
			return true;
		}
		return false;
	}
}
