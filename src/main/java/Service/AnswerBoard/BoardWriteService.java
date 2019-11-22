package Service.AnswerBoard;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Command.AnswerBoard.AnswerBoardCommand;
import Encrypt.Encrypt;
import Model.DAO.AnswerBoardDAO;
import Model.DTO.AnswerBoardDTO;
import Model.DTO.AuthInfo;

@Service
public class BoardWriteService {

	@Autowired
	AnswerBoardDAO answerBoardDAO;
	
	String original = null;
	String originalFileExtension = null;
	String store = null;
	String fileSize = null;
	
	
	public void boardWrite(AnswerBoardCommand answerBoardCommand, HttpServletRequest request, HttpSession session) {
		
		AnswerBoardDTO answerBoardDTO = new AnswerBoardDTO();
		
		answerBoardDTO.setUserId(((AuthInfo)session.getAttribute("authInfo")).getId());
		answerBoardDTO.setBoardName(answerBoardCommand.getBoardName());
		answerBoardDTO.setBoardPass(Encrypt.getEncryption(answerBoardCommand.getBoardPass()));
		answerBoardDTO.setBoardSubject(answerBoardCommand.getBoardSubject());
		answerBoardDTO.setBoardContent(answerBoardCommand.getBoardContent());
		
		//이것을 여기에다 넣는 이유는 위에다가 넣으면 계속해서 함수가 붙어버려 싱클톤이라 ?
		String originalTotal = "";
		String storeTotal = "";
		String fileSizeTotal = ""; //블랭크 준 이유는 널에다가 + 할 수 없으니까
		
		
		
		for(MultipartFile mf : answerBoardCommand.getReport()) { // 흠 배열로 한대요!
		 original = mf.getOriginalFilename();
		
		//확장자만 가져오겠다.오리지널 파일 네임인데 => .이 있는 부분부터 가져오는거야 .. ===>>>> 확장자 가져오는거야 이거는!!!!!!!!!!!! 
		 originalFileExtension = original.substring(original.lastIndexOf("."));  
		
		//문자까지도 랜덤으로 가져오는거임 영문자 / 특수문자 / 숫자 다 가져오는아이입니다.!
		//들어가면 안되는게 있어 그걸 없애겠다 => -게 있다면 ""로 없애라.
		//그러면 랜덤 이름에 확장자 명이 붙는거잖아.!!!
		 store = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension; 
				
	
		 fileSize = Long.toString(mf.getSize());
		 
		 originalTotal += original + "-"; //이름 - 구분자  이름  - 구분자 이런식이래
		 storeTotal += store + "-";
		 fileSizeTotal += fileSize + "-";
		 
		 //구분자는 문자잖아. 뭐라는거야 아니 이걸 왜하고 있는거야 뭘하는건데 
		 
		 
		 
		answerBoardDTO.setOriginalFilename(originalTotal);
		answerBoardDTO.setStoreFilename(storeTotal);
		answerBoardDTO.setFileSize(fileSizeTotal);
		// 여기까지가 DTO에 저장을 해주고
		
		//파일의 경로를 가져와야 할거 아니야 그러면 파일 객체가 필요해
		//이거는 context.xml에 적었듯이 하는건 안되서 무조건 /부분부터 다 작성해야함.
		String path = request.getServletContext().getRealPath("/");
		
		path += "WEB-INF/view/AnswerBoard/upload/";
		File file = new File(path+ store); //요거에 대한 객체생성.
		System.out.println();
		System.out.println(path);
		System.out.println();
		System.out.println(file);
		
		try {
			mf.transferTo(file); //여기까지면 파일이 일단 저장이 되었어요.
			
		} catch (IllegalStateException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		}
		answerBoardDAO.boardInsert(answerBoardDTO); //파일이 저장이 되면 insert해줘라
		
	}

}
