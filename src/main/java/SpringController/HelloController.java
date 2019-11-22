package SpringController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
	/*
	 * 컨트롤러 할떄 Request.getUrl, Path 등등 읽어왓는데 여기서는 이제 필요가 없다. 
	 프로젝트명 다음주소 적어주면 된다.주소로 들어오면 주소밑 메소드를 무조건 실행 (메소드명이 중요하지는 않음)*/
	@RequestMapping("/hello") //우리가 원하는 주소!!
	public String haaa(Model model, @RequestParam(value="num", required=false) String num) {
		//request 대신 => model / 쿼리스트링이 있다면 @RequestParam(value = "쿼리스트링의 변수명")로 처리를 해줘야 한다. 쿼리스트링 값을 받기 위해서 String num라는 식으로
		
		model.addAttribute("greeting", num);
		
		//얘가 실행된 후 페이지가 열려야 하잖아 그러니까 페이지명을 리턴하면 된다.
		return "NewFile";
	}

}


