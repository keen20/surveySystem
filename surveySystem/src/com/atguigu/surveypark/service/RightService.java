package com.atguigu.surveypark.service;

import java.util.List;
import java.util.Set;

import com.atguigu.surveypark.model.security.Right;

/**
 * rightService
 */
public interface RightService extends BaseService<Right> {

	/**
	 * ����/����Ȩ��
	 */
	public void saveOrUpdateRight(Right model);

	/**
	 * ����url׷��Ȩ��
	 */
	public void appendRightByURL(String url);

	/**
	 * ��������Ȩ��
	 */
	public void batchUpdateRights(List<Right> allRights);

	/**
	 * ��ѯ��ָ����Χ�ڵ�Ȩ��
	 */
	public List<Right> findRightsInRange(Integer[] ids);

	/**
	 * ��ѯ����ָ����Χ�ڵ�Ȩ��
	 */
	public List<Right> findRightsNotInRange(Set<Right> rights);

	/**
	 * ��ѯ���Ȩ��λ
	 */
	public int getMaxRightPos();
	
}
