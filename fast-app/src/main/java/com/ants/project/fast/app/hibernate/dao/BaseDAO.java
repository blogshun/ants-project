package com.ants.project.fast.app.hibernate.dao;

import com.ants.project.core.utils.Page;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.hibernate4.HibernateOperations;


public interface BaseDAO extends HibernateOperations, InitializingBean {


	/**
	 * 高级分页 带查询带属性条件
	 * @autho liushun
	 * @return Map集合
	 * 描述：需要查询出list 在得到total
	 */
	Page queryByPage(String hql, Page pageModel, Object... obj);


}
