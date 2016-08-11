package com.jiayuan.jyspring.db.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiayuan.jyspring.db.dao.UserModelRepository;
import com.jiayuan.jyspring.db.jpa.UserModel;

@Service  
@Transactional 
public class Client {
	
	@Autowired(required=true)  
	private UserModelRepository ur;  
	   
	public void testAdd(UserModel um){ ur.save(um); }  
	
	public void testFindAll(){
		ur.findAll(new Specification<UserModel>() {
			@Override
			public Predicate toPredicate(Root<UserModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> namePath = root.get("name");  
			    Path<Integer> agePath = root.get("age");  
			    query.where(cb.like(namePath, "%张%"), cb.le(agePath, 10)); //这里可以设置任意条查询条件  
			    query.orderBy(cb.desc(root.get("uuid")));
				return null;
			}
		}, new PageRequest(1, 2)).forEach(user->{
			System.out.println(user.getUuid()+","+user.getName()+","+user.getAge());
		});
	}
	   
	public static void main(String[] args) {  
	ApplicationContext ctx1 = new ClassPathXmlApplicationContext("applicationContext.xml");  
	Client c = (Client)ctx1.getBean("client");  
	UserModel um = new UserModel();  
	um.setAge(1);  
	um.setName("张三");  
//	um.setUuid(2);  
	   
	c.testAdd(um);  
	c.testFindAll();
	}  
}
