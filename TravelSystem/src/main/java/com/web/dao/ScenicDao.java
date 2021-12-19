package com.web.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.web.model.Scenic;

public interface ScenicDao extends Serializable {
	//查询全部景点
	public List<Scenic> scenicList(@Param(value = "scenicLocation") String scenicLocation,@Param(value = "currentPageNo") int currentPageNo,@Param(value = "pageSize") int pageSize);
	//查询行数(可根据景点位置来查)
	public int selectScenicCountByLocation(@Param(value = "scenicLocation") String scenicLocation);
	//根据id查询景点
	public Scenic selectScenicById(int id);
	//查询景点及攻略
	public List<Map<String,Object>> selectScenicAll();
	//查询景点位置及其景点数量
	public List<Map<String, Object>> selectScenicCount();
	//查询景点名称及其景点编号(可根据景点位置来查)
	public List<Map<String, Object>> selectScenicNumber(@Param("scenicLocation") String scenicLocation);
	//插入景点信息
	public int insertScenic(Scenic scenic);
	//修改景点信息
	public int updateScenic(Scenic scenic);
	//删除景点信息
	public int deleteScenic(int id);
}
