package com.web.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.model.Play;

public interface PlayDao extends Serializable {
	//分页查询攻略(可传景点位置和景点编号)
	public List<Play> selectPlayList(@Param("scenicLocation") String scenicLocation, @Param("scenicNumber") String scenicNumber, @Param("currentPageNo") int currentPageNo, @Param("pageSize") int pageSize);
	//查询行数(可传景点位置和景点编号)
	public Integer selectPlayCount(@Param("scenicLocation") String scenicLocation, @Param("scenicNumber") String scenicNumber);
	//根据id查询攻略
	public Play selectPlayById(int id);
	//插入攻略
	public int insertPlay(Play play);
	//修改攻略
	public int updatePlay(Play play);
	//删除攻略
	public int deletePlay(int id);
}
