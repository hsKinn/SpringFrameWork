package com.ktds.hskim.memo.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.hskim.memo.dao.MemoDAO;
import com.ktds.hskim.memo.vo.MemoVO;

public class MemoDAOImpl extends SqlSessionDaoSupport implements MemoDAO{

	@Override
	public void insertMemo(MemoVO memo) {
		getSqlSession().insert("MemoDAO.insertMemo", memo);
	}

	@Override
	public void modifyMemo(MemoVO memo) {
		getSqlSession().update("MemoDAO.modifyMemo", memo);
	}

	@Override
	public void deleteMemo(MemoVO memo) {
		getSqlSession().update("MemoDAO.deleteMemo", memo);
	}

	@Override
	public MemoVO getOneMemo(int memoId) {
		return getSqlSession().selectOne("MemoDAO.getOneMemo", memoId);
	}

}
