package Command.Member;

import java.util.Date;

public class MemberCommand {
//String이여도 됨 왜냐하면 쿼리스트링이니까.
	//			    service
	//view => command => dto => db
	//						 dao
	//command랑 dto 따로 만들어주는 이유는 만약 파일같은경우로 보면
	//command에서 db로 바로 저장이 안되니까 dto에 저장을 해서 보내야 되니까 !!
	String userId;
	String userPw;
	String userPwCon;	
	String userName;
	String userBirth;
	String userGender;
	String userRmail;
	String userAddr;
	String userPh1;
	String userPh2;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserPwCon() {
		return userPwCon;
	}
	public void setUserPwCon(String userPwCon) {
		this.userPwCon = userPwCon;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserRmail() {
		return userRmail;
	}
	public void setUserRmail(String userRmail) {
		this.userRmail = userRmail;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public String getUserPh1() {
		return userPh1;
	}
	public void setUserPh1(String userPh1) {
		this.userPh1 = userPh1;
	}
	public String getUserPh2() {
		return userPh2;
	}
	public void setUserPh2(String userPh2) {
		this.userPh2 = userPh2;
	}
	
	//확인하자!!!
	public boolean isPasswordEqualToConfirmPassword() {
		
		if(userPwCon.equals(userPw)) {
			return true;
		}
		
		return false;
	}
	
}
