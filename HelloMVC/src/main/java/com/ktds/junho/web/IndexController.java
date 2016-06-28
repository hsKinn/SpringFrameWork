package com.ktds.junho.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.junho.vo.LoginVO;

import kr.co.hucloud.utilities.excel.option.ReadOption;
import kr.co.hucloud.utilities.excel.read.ExcelRead;

// servlet을 대체하는 것은 뒤에 Controller라고 이름을 짓는다.
// annotation Controller라고 한다.
@Controller
public class IndexController {
	
	private Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String index() {
		
		return "mainPage";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login( HttpSession session ) {
		
		if ( session.getAttribute("_MEMBER_") != null ) {
			// 로그인시 처리
			// return "redirect:http://www.daum.net"; 절대경로 URL
			// return "redirect:/home"; 같은 도메인 절대경로 URL
			// -> http://localhost:8080/home으로 접근
			// return "redirect:home"; 상대경로 URL
			
			session.invalidate();
			
			return "redirect:/home";
		}
		
		
		return "login/login";
	}
	
	
	@RequestMapping(value="/doLogin", method=RequestMethod.POST)
	public ModelAndView doLogin( @Valid LoginVO loginVO, Errors errors,  HttpSession session ) {
		
		ModelAndView view = new ModelAndView();
		
		if ( errors.hasErrors() ) {
			view.setViewName("login/login");
			return view;
		}
		
		view.setViewName("redirect:/home");
		
		MultipartFile uploadFile = loginVO.getUploadFile();
		
		if ( !uploadFile.isEmpty() ) {
			// 파일 이름 랜덤값 변환
			//String randomFileName = UUID.randomUUID().toString();
			//File tempFile = new File( "D:\\" + randomFileName );
			
			// 파일 이름대로 업로드
			File tempFile = new File( "D:\\" + uploadFile.getOriginalFilename() );
			try {
				uploadFile.transferTo(tempFile);
				
				//TODO 엑셀파일 불러와서 출력
				String filePath = tempFile.getAbsolutePath();
				if ( filePath.toUpperCase().endsWith(".XLS") || filePath.toUpperCase().endsWith(".XLSX") ) {
					// 강사님 생성 클래스
					ReadOption option = new ReadOption();
					option.setFilePath(filePath);
					option.setOutputColumns("A", "B", "C");
					option.setStartRow(1);
					
					List<Map<String, String>> excel = ExcelRead.read(option);
					
					String content = "";
					for (Map<String, String> map : excel) {
						content = "";
						content += map.get("A");
						content += "\t";
						content += map.get("B");
						content += "\t";
						content += map.get("C");
						logger.info(content);
					}
					
				}
				
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		session.setAttribute("_MEMBER_", loginVO.getId());
		
		System.out.println("ID : " + loginVO.getId());
		System.out.println("Password : " + loginVO.getPassword());
		System.out.println("Member Number : " + loginVO.getMemberNumber());
		System.out.println("Enable Auto Login : " + loginVO.isEnableAutoLogin());
		
		for (int i = 0; i < loginVO.getHobby().size(); i++) {
			System.out.println("Hobby : " + loginVO.getHobby().get(i));
		}
		
		return view;
	}
	
}

