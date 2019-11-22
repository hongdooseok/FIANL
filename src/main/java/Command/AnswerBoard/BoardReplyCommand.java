package Command.AnswerBoard;

import org.springframework.web.multipart.MultipartFile;

public class BoardReplyCommand {
	//부모글 번호 , 참조번호, 레벨과 , 시퀸스를 더 추가적으로 있어야하잖아 그래서 새로 만들어야해 ==> boardCommand랑 다르다는 이야기죠!!
	
	
	
		//여기 추가하려는것들이 AnswerBoardCommand에 있는것들에 추가 4개만 필요한거잖아 AnswerBoardCommand에 있는걸 굳이 내가 적어야해 ??
		//결국에는 AnswerBoardCommand라는 객체를 사용하면 되는거잖아. 그러면 boardReply.jsp파일에서 바꿔야해 =>글쓴이 제목 내용 다 
		private AnswerBoardCommand answerBoardCommand; 
		private String boardReRef;
		private String boardReLev;
		private String boardReSeq;
		private String boardNum;
		private MultipartFile [] reports;
		
		
		public BoardReplyCommand() { //이런식으로 생성자에 객체생성 해줘야 한데요 안하면 에러뜬데요 왜하는지는 모르겠어요 // ==>> 나는 그냥 추가할래 이런식 너무 복잡해!!
			answerBoardCommand = new AnswerBoardCommand();
		}
		
		public AnswerBoardCommand getAnswerBoardCommand() {
			return answerBoardCommand;
		}
		public void setAnswerBoardCommand(AnswerBoardCommand answerBoardCommand) {
			this.answerBoardCommand = answerBoardCommand;
		}
		public String getBoardReRef() {
			return boardReRef;
		}
		public void setBoardReRef(String boardReRef) {
			this.boardReRef = boardReRef;
		}
		public String getBoardReLev() {
			return boardReLev;
		}
		public void setBoardReLev(String boardReLev) {
			this.boardReLev = boardReLev;
		}
		public String getBoardReSeq() {
			return boardReSeq;
		}
		public void setBoardReSeq(String boardReSeq) {
			this.boardReSeq = boardReSeq;
		}
		public String getBoardNum() {
			return boardNum;
		}
		public void setBoardNum(String boardNum) {
			this.boardNum = boardNum;
		}
		public MultipartFile[] getReports() {
			return reports;
		}
		public void setReports(MultipartFile[] reports) {
			this.reports = reports;
		}

}
