package com.atguigu.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.surveypark.model.security.Right;
import com.atguigu.surveypark.service.RightService;

/**
 * RightAction
 */
@Controller
@Scope("prototype")
public class RightAction extends BaseAction<Right> {

	private static final long serialVersionUID = 2546431181647311959L;
	
	private List<Right> allRights  ;
	
	private Integer rightId ;
	
	public Integer getRightId() {
		return rightId;
	}


	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}


	public List<Right> getAllRights() {
		return allRights;
	}


	public void setAllRights(List<Right> allRights) {
		this.allRights = allRights;
	}

	@Resource
	private RightService rightService ;

	/**
	 * 查询所有权限
	 */
	public String findAllRights(){
		this.allRights = rightService.findAllEntities();
		return "rightListPage" ;
	} 
	
	/**
	 * 添加权限
	 */
	public String toAddRightPage(){
		return "addRightPage" ;
	}
	
	/**
	 * 保存/更新权限
	 */
	public String saveOrUpdateRight(){
		rightService.saveOrUpdateRight(model);
		return "findAllRightAction" ;
	}
	
	/**
	 * 编辑权限
	 */
	public String editRight(){
		this.model = rightService.getEntity(rightId);
		return "editRightPage" ;
	}
	
	/**
	 * 删除权限 
	 */
	public String deleteRight(){
		Right r = new Right();
		r.setId(rightId);
		rightService.deleteEntity(r);
		return "findAllRightAction" ;
	}
	
	/**
	 * 批量更新权限
	 */
	public String batchUpdateRights(){
		rightService.batchUpdateRights(allRights);
		return "findAllRightAction" ;
	}
}