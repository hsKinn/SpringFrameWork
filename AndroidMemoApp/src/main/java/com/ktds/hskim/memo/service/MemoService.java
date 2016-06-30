package com.ktds.hskim.memo.service;

import com.ktds.hskim.memo.vo.MemoVO;

public interface MemoService {

	public void insertMemo(MemoVO memo);

	public void modifyMemo(MemoVO memo);

	public void deleteMemo(MemoVO memo);
}
