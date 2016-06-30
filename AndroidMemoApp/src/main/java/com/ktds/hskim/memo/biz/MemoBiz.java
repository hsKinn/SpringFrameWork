package com.ktds.hskim.memo.biz;

import com.ktds.hskim.memo.vo.MemoVO;

public interface MemoBiz {
	
	public void insertMemo(MemoVO memo);

	public void modifyMemo(MemoVO memo);

	public void deleteMemo(MemoVO memo);

}
