package com.web.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.web.model.Delicacy;

public interface DelicacyDao extends Serializable {
	//查询全部美食
	public List<Delicacy> selectDelicacyList(@Param("delicacyName") String delicacyName, @Param("currentPageNo") int currentPageNo,@Param("pageSize") int pageSize);
	//根据美食主键id查询美食
	public Delicacy selectDelicacyById(int id);
	//查询行数
	public int selectDelicacyByName(@Param("delicacyName") String delicacyName);
	//插入美食信息
	public int insertDelicacy(Delicacy delicacy);
	//修改美食信息
	public int updateDelicacy(Delicacy delicacy);
	//删除美食信息
	public int deleteDelicacy(int id);
}
