package com.jiayuan.jyspring.db.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jiayuan.jyspring.db.jpa.UserModel;

@Repository
public interface  UserModelRepository extends  JpaRepository<UserModel, Long> , JpaSpecificationExecutor<UserModel> {         

	    @Query("select s from UserModel s where s.id = ?1")  
	    List<UserModel> findById(Long id);    
}
    