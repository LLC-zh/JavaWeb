package com.web.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.web.dao.UserDao;
import com.web.model.User;
import com.web.util.DateUtil;
import com.web.util.MyBatisUtil;
import com.web.util.UUidUtil;

public class UserDaoImpl implements UserDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Map<String, Object>> selectUserList() {
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			List<Map<String,Object>> list = session.selectList("com.web.mapper.UserMapper.selectUser");
			session.close();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User selectUserByUUid(String uuid) {
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			User user = session.selectOne("com.web.mapper.UserMapper.selectUserByUUid", uuid);
			session.close();
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User selectUserLogin(String userName, String userPassword) {
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName", userName);
			map.put("userPassword", userPassword);
			User user = session.selectOne("com.web.mapper.UserMapper.selectUserLogin", map);
			session.close();
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int insertUser(User user) {
		int rows = 0;
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			//java.sql.Date time = new java.sql.Date(new Date().getTime());
			user.setIsAdmin(0);
			user.setUserUUid(UUidUtil.getUUid());
			user.setGmtCreate(DateUtil.getCurDateTime());
			rows = session.insert("com.web.mapper.UserMapper.insertUser", user);
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
	public int updateUser(User user) {
		int rows = 0;
		try {
			SqlSession sqlSession = MyBatisUtil.getSqlSession();
			//java.sql.Date time = new java.sql.Date(new Date().getTime());
			user.setGmtModified(DateUtil.getCurDateTime());
			rows = sqlSession.update("com.web.mapper.UserMapper.updateUserByUUid", user);
			sqlSession.commit();
			sqlSession.close();
			return rows;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int deleteUserByUUid(String uuid) {
		int rows = 0;
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			rows = session.delete("com.web.mapper.UserMapper.deleteUserByUUid", uuid);
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
	public boolean selectUserByName(String userName) {
		Integer id = null;
		try {
			SqlSession session = MyBatisUtil.getSqlSession();
			id = session.selectOne("com.web.mapper.UserMapper.selectUserByName",userName);
			session.close();
			if(id != null && id > 0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
