package com.ktds.hskim.memo.biz.impl;

import com.ktds.hskim.memo.biz.MemoBiz;
import com.ktds.hskim.memo.dao.MemoDAO;
import com.ktds.hskim.memo.vo.MemoVO;

public class MemoBizImpl implements MemoBiz {

	private MemoDAO memoDAO;
	public void setMemoDAO(MemoDAO memoDAO) {
		this.memoDAO = memoDAO;
	}

	@Override
	public void insertMemo(MemoVO memo) {
		memoDAO.insertMemo(memo);
	}

	@Override
	public void modifyMemo(MemoVO memo) {
		
		MemoVO originMemo = memoDAO.getOneMemo(memo.getMemoId());
		MemoVO changedMemo = new MemoVO();
		changedMemo.setCreatedDate(memo.getCreatedDate());
		
		if ( !originMemo.getSubject().equals(memo.getSubject()) ) {
			changedMemo.setSubject(memo.getSubject());
		}
		if ( !originMemo.getContent().equals(memo.getContent()) ) {
			changedMemo.setContent(memo.getContent());
		}
		
		memoDAO.modifyMemo(changedMemo);
	}

	@Override
	public void deleteMemo(MemoVO memo) {
		memoDAO.deleteMemo(memo);
	}
	
}
