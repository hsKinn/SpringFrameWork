package com.ktds.hskim.memo.dao;

import com.ktds.hskim.memo.vo.MemoVO;

public interface MemoDAO {

	public void insertMemo(MemoVO memo);

	public void modifyMemo(MemoVO memo);

	public void deleteMemo(MemoVO memo);

	public MemoVO getOneMemo(int memoId);
	
}
