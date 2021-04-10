package com.koreait.app.board.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.koreait.mybatis.config.SqlMapConfig;

public class ReplyDAO {
	SqlSessionFactory sqlfactory = SqlMapConfig.getInstance();
	SqlSession sqlsession;
	
	public ReplyDAO() {
		sqlsession = sqlfactory.openSession(true);
	}
	
	public boolean insertReply(ReplyBean reply) {
		return sqlsession.insert("Board.insertReply",reply)==1;
	}

	public List<ReplyBean> getDetail(int boardnum) {
		List<ReplyBean> replys = sqlsession.selectList("Board.getReplys",boardnum);
		return replys;
	}

	public void modifyReply(int replynum, String replycontents) {
		HashMap<String, Object> datas = new HashMap<>();
		datas.put("replynum", replynum);
		datas.put("replycontents", replycontents);
		sqlsession.update("Board.modifyReply",datas);
	}

	public void deleteReply(int replynum) {
		sqlsession.delete("Board.deleteReply",replynum);
	}

	public void deleteAll(int boardnum) {
		sqlsession.delete("Board.deleteAll",boardnum);
		
	}
}



















