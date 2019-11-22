package Model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import Command.Member.ListCommand;
import Command.Member.LoginCommand;
import Model.DTO.MemberDTO;

public class MemberDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	//왜 이렇게 사용을해?? select의 List가 지금이야 2번이니까 ㄱㅊ은거지 여러개면 매번 반복을 해야하잖아 .. 그걸 그냥 여기 만들어서 사용하자 이런식.
	private RowMapper<MemberDTO> memRowMapper = new RowMapper<MemberDTO>() { 

		public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException { //mapRow 얘를 누가 실행시켜?? 얘는 RowMapper얘가 생성될떄 자동으로 실행되는거 그래서 오버라이드..
			
			MemberDTO member = new MemberDTO(); 
			
			member.setUserId(rs.getString("user_id"));
			member.setUserPw(rs.getString("user_pw"));
			member.setUserName(rs.getString("user_name"));
			member.setUserBirth(rs.getTimestamp("user_birth"));
			member.setUserGender(rs.getString("user_gender"));
			member.setUserRmail(rs.getString("user_rmail"));
			member.setUserAddr(rs.getString("user_addr"));
			member.setUserPh1(rs.getString("user_ph1"));
			member.setUserPh2(rs.getString("user_ph2"));
			member.setUserRegist(rs.getTimestamp("user_regist"));
			return member;
		}
		
	};
	
	@Autowired
	public MemberDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Integer inserMember(MemberDTO memberDTO) {
		String sql = "insert into Member(user_id, user_pw, user_name, user_birth, user_gender, user_rmail, user_addr, user_ph1, user_ph2, user_regist) values(?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
		int i = jdbcTemplate.update(sql, memberDTO.getUserId(), memberDTO.getUserPw(), memberDTO.getUserName(), memberDTO.getUserBirth(), memberDTO.getUserGender(), memberDTO.getUserRmail(), memberDTO.getUserAddr(), memberDTO.getUserPh1(), memberDTO.getUserPh2());
		return i;
	}
	
	public MemberDTO selectByUserId(LoginCommand loginCommand) {
		MemberDTO member = null;
		String sql ="select user_id, user_pw, user_name, user_birth, user_gender, user_rmail, user_addr, user_ph1, user_ph2, user_regist from member where user_id = ?";
		//아이디만 가져온 이유는 아이디가 잘못되었는지 비밀번호가 잘못되었는지 알기 위해서 둘다가져오면 뭔지 모르니까.
		List<MemberDTO> results = jdbcTemplate.query(sql, memRowMapper, loginCommand.getId1());
		member = results.isEmpty()? null : results.get(0);
		return member;
	}
	
	//수정하기 위해서 사용하는 메소드 입니다.!!!
	public Integer memberModify(MemberDTO memberDTO) {
		String sql ="update member set user_rmail = ?, user_addr = ?, user_ph1 = ?, user_ph2 = ? where user_id = ? and user_pw = ?";
		Integer i = jdbcTemplate.update(sql, memberDTO.getUserRmail(), memberDTO.getUserAddr(), memberDTO.getUserPh1(), memberDTO.getUserPh2(), memberDTO.getUserId(), memberDTO.getUserPw());
		return i;
	}
	
	//비밀번호?
	public Integer pwUpdate(String id, String pw, String newPw) {
		String sql ="update member set user_pw = ? where user_id = ? and user_pw = ?";
		Integer i = jdbcTemplate.update(sql, newPw, id, pw); //newPw로 바꾸려고 하는거니까.
		return i;
	}
	
	//회원 리스트.
	public List<MemberDTO> selectList(ListCommand listCommand) {
		String sql ="select user_id, user_pw, user_name, user_birth, user_gender, user_rmail, user_addr, user_ph1, user_ph2, user_regist from member where user_regist between ? and ?";
		
		List<MemberDTO> results = jdbcTemplate.query(sql, memRowMapper, listCommand.getFrom(), listCommand.getTo());
		
		return results;
	}

}
