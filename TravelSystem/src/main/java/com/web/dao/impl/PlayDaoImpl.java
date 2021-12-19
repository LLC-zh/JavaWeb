package com.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.dao.PlayDao;
import com.web.model.Play;
import com.web.util.DateUtil;
import com.web.util.MyBatisUtil;

public class PlayDaoImpl implements PlayDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Play> selectPlayList(String scenicLocation, String scenicNumber, int currentPageNo, int pageSize) {
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			currentPageNo = (currentPageNo -1) * pageSize;
			Map<String, Object> map = new HashMap<>();
			map.put("scenicLocation", scenicLocation);
			map.put("scenicNumber", scenicNumber);
			map.put("currentPageNo",currentPageNo);
			map.put("pageSize", pageSize);
			List<Play> list = session.selectList("com.web.mapper.PlayMapper.selectPlayList", map);
			session.close();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Integer selectPlayCount(String scenicLocation, String scenicNumber) {
		int rows = 0;
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			Map<String, Object> map = new HashMap<>();
			map.put("scenicLocation", scenicLocation);
			map.put("scenicNumber", scenicNumber);
			rows = session.selectOne("com.web.mapper.PlayMapper.selectPlayCount",map);
			session.close();
			return rows;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Play selectPlayById(int id) {
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			Play play = session.selectOne("com.web.mapper.PlayMapper.selectPlayById", id);
			session.close();
			return play;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int insertPlay(Play play) {
		int rows = 0;
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			play.setGmtCreate(DateUtil.getCurDateTime());
			rows = session.insert("com.web.mapper.PlayMapper.insertPlay", play);
			session.commit();
			session.close();
			return rows;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int updatePlay(Play play) {
		int rows = 0;
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			play.setGmtModified(DateUtil.getCurDateTime());
			rows = session.update("com.web.mapper.PlayMapper.updatePlay", play);
			session.commit();
			session.close();
			return rows;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int deletePlay(int id) {
		int rows = 0;
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			rows = session.delete("com.web.mapper.PlayMapper.deletePlay", id);
			session.commit();
			session.close();
			return rows;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

}
