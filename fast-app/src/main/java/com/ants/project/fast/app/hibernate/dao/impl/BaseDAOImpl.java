package com.ants.project.fast.app.hibernate.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ants.project.core.utils.Page;
import com.ants.project.fast.app.hibernate.dao.BaseDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class BaseDAOImpl extends HibernateTemplate implements BaseDAO {

	/**
	 * 基本数据库操作封装类
	 */

	@Resource(name = "sessionFactory")
	public void setMySessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	public Page queryByPage(String hql, Page page, Object... objs) {
		Integer pageSize = page.getSize();
		Integer pageIndex = page.getCurrent()<1?0:page.getCurrent()-1;
		Session session = this.getSessionFactory().openSession();

		Query list_query = session.createQuery(hql);
		Query count_query = session.createQuery("select count(*) "+hql);
		if(objs!=null){
			for(int i=0;i<objs.length;i++) {
				list_query.setParameter(i, objs[i]);
				count_query.setParameter(i, objs[i]);
			}
		}
		list_query.setFirstResult(pageIndex*pageSize); //设置开始记录数
		list_query.setMaxResults(pageSize);	//设置每页大小
		List list = list_query.list();
		page.setData(list);
		//查询总记录数
		long count = Long.parseLong((count_query.uniqueResult()).toString());
		page.setTotal(count);
		if(count%pageSize==0)
			page.setPages((int)(count/pageSize));
		else
			page.setPages((int)((count/pageSize)+1));
		return page;
	}

}
