package com.atguigu.surveypark.service;

import java.util.List;
import java.util.Set;

import com.atguigu.surveypark.model.security.Right;

/**
 * rightService
 */
public interface RightService extends BaseService<Right> {

	/**
	 * 保存/更新权限
	 */
	public void saveOrUpdateRight(Right model);

	/**
	 * 按照url追加权限
	 */
	public void appendRightByURL(String url);

	/**
	 * 批量更新权限
	 */
	public void batchUpdateRights(List<Right> allRights);

	/**
	 * 查询在指定范围内的权限
	 */
	public List<Right> findRightsInRange(Integer[] ids);

	/**
	 * 查询不在指定范围内的权限
	 */
	public List<Right> findRightsNotInRange(Set<Right> rights);

	/**
	 * 查询最大权限位
	 */
	public int getMaxRightPos();
	
}
