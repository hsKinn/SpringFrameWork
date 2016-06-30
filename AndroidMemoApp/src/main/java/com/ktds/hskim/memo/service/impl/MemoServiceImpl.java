package com.ktds.hskim.memo.service.impl;

import com.ktds.hskim.memo.biz.MemoBiz;
import com.ktds.hskim.memo.service.MemoService;
import com.ktds.hskim.memo.vo.MemoVO;

public class MemoServiceImpl implements MemoService {

	private MemoBiz memoBiz;
	public void setMemoBiz(MemoBiz memoBiz) {
		this.memoBiz = memoBiz;
	}

	@Override
	public void insertMemo(MemoVO memo) {
		// Memo DATA Validation
		memoBiz.insertMemo(memo);
	}

	@Override
	public void modifyMemo(MemoVO memo) {
		memoBiz.modifyMemo(memo);
	}

	@Override
	public void deleteMemo(MemoVO memo) {
		memoBiz.deleteMemo(memo);
	}

}
