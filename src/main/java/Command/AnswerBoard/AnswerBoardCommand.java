package Command.AnswerBoard;

import org.springframework.web.multipart.MultipartFile;

public class AnswerBoardCommand {
	
	String boardName;
	String boardPass;
	String boardSubject;
	String boardContent;
	MultipartFile [] report;
	
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getBoardPass() {
		return boardPass;
	}
	public void setBoardPass(String boardPass) {
		this.boardPass = boardPass;
	}
	public String getBoardSubject() {
		return boardSubject;
	}
	public void setBoardSubject(String boardSubject) {
		this.boardSubject = boardSubject;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public MultipartFile [] getReport() {
		return report;
	}
	public void setReport(MultipartFile [] report) {
		this.report = report;
	}

}
