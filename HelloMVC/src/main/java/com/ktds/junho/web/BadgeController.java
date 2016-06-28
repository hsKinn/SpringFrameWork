package com.ktds.junho.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktds.junho.vo.LoginVO;

@Controller
public class BadgeController {

	@RequestMapping("/newMember")
	@ResponseBody
	public LoginVO getNewMemberCountForAjax() {
		LoginVO loginVO = new LoginVO();
		loginVO.setId("junho");
		loginVO.setMemberNumber(1);
		loginVO.setEnableAutoLogin(true);
		
		return loginVO;
	}
	
}
