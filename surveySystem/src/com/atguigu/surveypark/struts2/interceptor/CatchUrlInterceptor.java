package com.atguigu.surveypark.struts2.interceptor;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.atguigu.surveypark.service.RightService;
import com.atguigu.surveypark.util.ValidateUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 捕获URL拦截器
 */
public class CatchUrlInterceptor implements Interceptor {

	private static final long serialVersionUID = 6563408965618840075L;

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		ActionProxy proxy = invocation.getProxy();
		//名字空间
		String ns = proxy.getNamespace();
		//actionName
		String actionName = proxy.getActionName();
		if(!ValidateUtil.isValid(ns)
				||ns.equals("/")){ 
			ns = "" ;
		}
		String url = ns + "/" + actionName ;
		
		//取得在applicationspring容器.
		//ApplicationContext ac = (ApplicationContext) invocation.getInvocationContext().getApplication().get(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		//
		ServletContext sc = ServletActionContext.getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
		RightService rs = (RightService) ac.getBean("rightService");
		
		rs.appendRightByURL(url);
		return invocation.invoke();
	}
}
