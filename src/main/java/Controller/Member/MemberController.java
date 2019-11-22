package Controller.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Command.Member.MemberCommand;
import Service.Member.MemberJoinService;
import Validator.MemberCommandValidator;

@Controller
public class MemberController {
	
	@Autowired
	private MemberJoinService memberJoinService; //의존객체를 위에다? => 의존객체 주입을 해야하잖아 그래서 bean에다 작성했는데 이 의존객체를 사용하는게 누구? 컨트롤러잖아. dto랑 command만 있어야
	//의존객체떄문에 멤버필드를 만들잖아 그럼 setter가 필요하지 ? 그럼 만들고 컨트롤러 밑에 작성을 하잖아.. 
	// 근데 Autowired를 쓰면 밑에 작성하는게 없어지는거고 @Controller 안에 작성을하면 setter도 필요없다 ====> 결국에는 멤버 필드만 있으면 되는거야
	

	@RequestMapping("/regeister/agree") //url주소 주소지만 파일명과 같은거라느데?? agree /// /regeister/가 주소고 agree는 파일명이라 생각
	public String agree() {
		System.out.println("aaaa"); //디버깅
		
		return "Member/agree"; //파일을 열어주는거
		
	}
	
	//그냥 여기다가 만들어 줄게
	///regeister/regist이렇게 줘도 만약 form에 action="/regeister/regist"면 => /regeister/regeister/regist
	@RequestMapping("/regeister/regist") //컨텍스트 패스 이후의 주소를 적어줘야 한다 여기에는!! 컨텍스트 패스는 결국 프로젝트명이고 
	public String From(@RequestParam(value = "agree", defaultValue ="false") Boolean agree, Model model) { //뒤는 날라오는 값.
		//view로 보여주려고 하는게 모델
		if(!agree) {
			return "Member/agree";
			 // or Member/agree =>이거하면 주소는 바뀌지만 보이는건 그대로
			//redirect는 내가 원하는 주소 / Member/agree 페이지
		}
		model.addAttribute("memberCommand", new MemberCommand());  //다른페이지에서 열면 커맨드 내용이 없으니까 그렇다고 이걸 쓴데 왜쓰녀고 몰라!
		return "Member/memberForm";
	}
	
	//체크를 선택 안했을때 아무것도 안날라가는데 그게 오류가 난다고 그래서 하는게   defaultValue ="false" 체크박스는 true 아님 false니까 boolean

	//required는 없으면?
	@RequestMapping(value = "/regeister/MemberJoinAction", method= RequestMethod.POST)
	public String Join(MemberCommand memberCommand, Model model, Errors errors) {
		
		//MemberCommandValidator aa = new MemberCommandValidator();
		//aa.validate(target, errors); 이런식으로 적는거나
		
		new MemberCommandValidator().validate(memberCommand, errors); //이런식으로 하는거나 더 간결하게 할 수 있지. 뒤 errors는 메개변수에 errors니까
		if(errors.hasErrors()) {//여기서 출력을 해주는거야 에러가 있다면 ValidationUtils.rejectIfEmpty(errors, "password", "required"); 이거 
			return "Member/memberForm";
		}
	//이메일이 완벽하게 작성이 되고 나서야 밑에 중복된 아이디입니다가 나오지!!
		Integer i = memberJoinService.execute(memberCommand, model);//저값을 서비스에다가 전달 해줘야지.
			
		if(i == null) {//try catchc를 해야하는데 여기에서 해줘서!!!! 위 errors때문에 try를 해줘야하는데!!
			errors.rejectValue("userId", "duplicate");// 중복이면 userId에다가 주겠다라는 뜻이죠!
			return "Member/memberForm";
		}
		return "Member/memberWelcome";//요페이지에다 값을 전달해주고 싶으면 request대신 Model을 쓰잖아
			//커맨드 객체와 페이지가 같이 있잖아 메소드 안에 커맨드 객체를 날릴수가 있어요
			
			
	}
	@RequestMapping(value = "/regeister/MemberJoinAction", method= RequestMethod.GET)
	public String JoinGet(MemberCommand memberCommand) { //DispatcherServlet이 알아서 얘를 사용하는구나 !! 이런거로 생각하자.
		
		System.out.println(memberCommand.getUserId());
		return "redirect:agree";
	}

}
