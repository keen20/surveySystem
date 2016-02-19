package com.atguigu.surveypark.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.atguigu.surveypark.dao.BaseDao;
import com.atguigu.surveypark.model.security.Right;
import com.atguigu.surveypark.service.RightService;
import com.atguigu.surveypark.util.DataUtil;
import com.atguigu.surveypark.util.StringUtil;
import com.atguigu.surveypark.util.ValidateUtil;

@Service("rightService")
public class RightServiceImpl extends BaseServiceImpl<Right> implements
		RightService {
	@Resource(name="rightDao")
	public void setDao(BaseDao<Right> dao) {
		super.setDao(dao);
	}
	
	/**
	 * 保存/更新权限
	 */
	public void saveOrUpdateRight(Right r){
		//插入操作
		int pos = 0 ;
		long code = 1L ; 
		if(r.getId() == null){
//			String hql = "from Right r order by r.rightPos desc,r.rightCode desc" ;
//			List<Right> rights = this.findEntityByHQL(hql);
//			if(!ValidateUtil.isValid(rights)){
//				pos = 0;
//				code = 1L ;
//			}
//			else{
//				//得到最上面的right
//				Right top = rights.get(0);
//				int topPos = top.getRightPos();
//				long topCode = top.getRightCode();
//				//判断权限码是否到达最大值
//				if(topCode >= (1L << 60)){
//					pos = topPos + 1 ;
//					code = 1 ;
//				}
//				else{
//					pos = topPos ;
//					code = topCode << 1 ;
//				}
//			}
			//
			String hql = "select max(r.rightPos),max(r.rightCode) from Right r "
					+ "where r.rightPos = (select max(rr.rightPos) from Right rr)" ;
			Object[] arr = (Object[]) this.uniqueResult(hql);
			Integer topPos = (Integer) arr[0];
			Long topCode = (Long) arr[1];
			//没有权限
			if(topPos == null){
				pos = 0 ;
				code = 1L ;
			}
			else{
				//权限码是否到达最大值
				if(topCode >= (1L << 60)){
					pos = topPos + 1 ;
					code = 1L ;
				}
				else{
					pos = topPos ;
					code = topCode << 1 ;
				}
			}
			
			r.setRightPos(pos);
			r.setRightCode(code);
		}
		this.saveOrUpdateEntity(r);
	}
	
	/**
	 * 按照url追加权限
	 */
	public void appendRightByURL(String url){
		String hql = "select count(*) from Right r where r.rightUrl = ?" ;
		Long count = (Long) this.uniqueResult(hql,url);
		if(count == 0){
			Right r = new Right();
			r.setRightUrl(url);
			this.saveOrUpdateRight(r);
		}
	}
	
	/**
	 * 批量更新权限
	 */
	public void batchUpdateRights(List<Right> allRights){
		String hql = "update Right r set r.rightName = ?,r.common = ? where r.id = ?" ;
		if(ValidateUtil.isValid(allRights)){
			for(Right r  : allRights){
				this.batchEntityByHQL(hql,r.getRightName(),r.isCommon(),r.getId());
			}
		}
	}
	
	/**
	 * 查询在指定范围内的权限
	 */
	public List<Right> findRightsInRange(Integer[] ids){
		if(ValidateUtil.isValid(ids)){
			String hql = "from Right r where r.id in ("+StringUtil.arr2Str(ids)+")" ;
			return this.findEntityByHQL(hql);
		}
		return null ;
	}
	
	/**
	 * 查询不在指定范围内的权限
	 */
	public List<Right> findRightsNotInRange(Set<Right> rights){
		if(!ValidateUtil.isValid(rights)){
			return this.findAllEntities();
		}
		else{
			String hql = "from Right r where r.id not in("+DataUtil.extractRightIds(rights)+")" ;
			return this.findEntityByHQL(hql);
		}
	}
	
	/**
	 * 查询最大权限位
	 */
	public int getMaxRightPos(){
		String hql = "select max(r.rightPos) from Right r" ;
		Integer pos = (Integer) this.uniqueResult(hql);
		return pos == null ? 0 : pos ;
	}
}
