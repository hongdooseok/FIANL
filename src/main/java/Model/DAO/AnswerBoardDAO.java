package Model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import Model.DTO.AnswerBoardDTO;
import Model.DTO.MemberDTO;

public class AnswerBoardDAO {
	
	private JdbcTemplate jdbcTemplate;
	private String sql;
	private final String COLUMN = "BOARD_NUM, USER_ID, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE, ORIGINAL_FILENAME, STORE_FILENAME, FILE_SIZE";
	
	private RowMapper<AnswerBoardDTO> boardRowMapper = new RowMapper<AnswerBoardDTO>() { 

		public AnswerBoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException { //mapRow 얘를 누가 실행시켜?? 얘는 RowMapper얘가 생성될떄 자동으로 실행되는거 그래서 오버라이드..
			
			AnswerBoardDTO answerBoard = new AnswerBoardDTO(); 
			
			answerBoard.setBoardNum(rs.getLong("BOARD_NUM"));
			answerBoard.setUserId(rs.getString("USER_ID"));
			answerBoard.setBoardName(rs.getString("BOARD_NAME"));
			answerBoard.setBoardPass(rs.getString("BOARD_PASS"));
			answerBoard.setBoardSubject(rs.getString("BOARD_SUBJECT"));
			answerBoard.setBoardContent(rs.getString("BOARD_CONTENT"));
			answerBoard.setBoardReRef(rs.getLong("BOARD_RE_REF"));
			answerBoard.setBoardReLev(rs.getLong("BOARD_RE_LEV"));
			answerBoard.setBoardReSeq(rs.getLong("BOARD_RE_SEQ")); //seq는 오름차순이잖아.
			answerBoard.setBoardReadcount(rs.getLong("BOARD_READCOUNT"));
			answerBoard.setBoardDate(rs.getTimestamp("BOARD_DATE"));
			answerBoard.setOriginalFilename(rs.getString("ORIGINAL_FILENAME"));
			answerBoard.setStoreFilename(rs.getString("STORE_FILENAME"));
			answerBoard.setFileSize(rs.getString("FILE_SIZE"));
			return answerBoard;
		}
		
	};
	
	@Autowired
	public AnswerBoardDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void boardInsert(AnswerBoardDTO answerBoardDTO) {
		 
		sql = "insert into boarder (" + COLUMN + ")"
				+ " values( board_seq.nextval, ? ,?, ?, ?, ?, board_seq.CURRVAL, 0, 0, 0, sysdate, ?, ?, ?)";
		
		jdbcTemplate.update(sql, answerBoardDTO.getUserId(), answerBoardDTO.getBoardName(), answerBoardDTO.getBoardPass(), answerBoardDTO.getBoardSubject(), answerBoardDTO.getBoardContent(),answerBoardDTO.getOriginalFilename(), answerBoardDTO.getStoreFilename(), answerBoardDTO.getFileSize());
		
	}
	
	public List<AnswerBoardDTO> boardListAll(int nowpage, int limit) {
		
		//page = 1, 1 ~ 10 / page = 2, 11 ~ 20
		sql = " select rn," + COLUMN
				+ " from (select rownum rn," + COLUMN  
				+ " from (select " + COLUMN + " from boarder order by BOARD_RE_REF DESC, BOARD_RE_SEQ ASC)) where rn >= ? and rn <= ? ";
		/*
		 	sql = " select rownum rn" + COLUMN
				+ " from (select rownum rn" + COLUMN  
				+ " from (select " + COLUMN + " from boarder order by BOARD_RE_REF DESC, BOARD_RE_SEQ ASC)) where rn <10";
		 rownum은 Top-n방식이야 << 이거는 뭐보다 작다로만 가능하다는거야 rn <5 이런시으로 
		 근데 우리가 원하는 방식은 범위야 그래서 rownum을 한번더 인라인 뷰로 만들어서 범위로 만들어 버리는거지

		 */
		int startrow = (nowpage -1) * limit + 1; // page = 1 이면 1을 가져오고 page =2 11을 가져오게
		int endrow = startrow + limit - 1;
		
		List<AnswerBoardDTO> list = jdbcTemplate.query(sql, boardRowMapper, startrow, endrow);
		return list;
	}
	
	public Integer getBoardCount() {
		sql = "select count(*) from boarder";
		return jdbcTemplate.queryForObject(sql, Integer.class); //object타입을 Integer형태로 형변환을 해주라고 하는거임 이게 queryForObject<< 
		
	}
	
	
	public AnswerBoardDTO boardDetail(Long boardNum) {
		
		sql = "select " + COLUMN + " from boarder where BOARD_NUM = ?";

		List<AnswerBoardDTO> list = jdbcTemplate.query(sql, boardRowMapper, boardNum);
		
		return list.isEmpty()? null : list.get(0); //공백이면 null이고 아니면 하나만 반환하니까 list.get(0)
	}
	
	public void boardReadcountUpdate(Long boardNum) {
		sql="update boarder set board_readcount = board_readcount + 1 where BOARD_NUM = ?";
		jdbcTemplate.update(sql, boardNum);
	}
	
	//게시판에 대한 내용
	public void boardReplyInsert(AnswerBoardDTO board) {
		//답글이 있는 경우 답글들의 순서를 1씩 증가.
		sql = "update boarder set board_re_seq = board_re_seq + 1 where board_re_ref = ? and board_re_seq > ?";
		jdbcTemplate.update(sql, board.getBoardReRef(), board.getBoardReSeq());
		//그럼 나는 부모보다 1크면 되잖아 1클게여.
		Long seq = board.getBoardReSeq() + 1;
		Long lev = board.getBoardReSeq() + 1;
		
		sql = "insert into boarder (" + COLUMN + ")"
				+ " values( board_seq.nextval, ? ,?, ?, ?, ?, ?, ?, ?, 0, sysdate, ?, ?, ?)";
		jdbcTemplate.update(sql, board.getUserId(), board.getBoardName(), board.getBoardPass(), board.getBoardSubject(), board.getBoardContent(), board.getBoardReRef(), lev, seq, board.getOriginalFilename(), board.getStoreFilename(), board.getFileSize());
		
	}

}
