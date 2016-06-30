package com.ktds.hskim.memo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ktds.hskim.memo.service.MemoService;
import com.ktds.hskim.memo.vo.MemoVO;

@Controller
public class MemoController {
	
	private MemoService memoService;
	public void setMemoService(MemoService memoService) {
		this.memoService = memoService;
	}

	@RequestMapping("/memoInsert")
	public void insertMemo(MemoVO memo) {
		System.out.println("Insert 들어옴");
		memoService.insertMemo(memo);
	}
	
	@RequestMapping("/memoModify")
	public void modifyMemo(MemoVO memo) {
		memoService.modifyMemo(memo);
	}
	
	@RequestMapping("memoDelete")
	public void deleteMemo(MemoVO memo) {
		memoService.deleteMemo(memo);
	}
	
	
}
