package com.library.dao;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AdminDao {
    private final static String NAMESPACE = "com.library.dao.AdminDao.";
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    //管理员登录
    public int getMatchCount(final long admin_id,final String password){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("admin_id", admin_id);
        paramMap.put("password", password);
        return sqlSessionTemplate.selectOne(NAMESPACE + "getMatchCount", paramMap);
    }

    //重置密码
    public int resetPassword(final long admin_id,final String password){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("admin_id", admin_id);
        paramMap.put("password", password);
        return sqlSessionTemplate.update(NAMESPACE + "resetPassword", paramMap);
    }

    //获取密码
    public String getPassword(final long admin_id){
        return sqlSessionTemplate.selectOne(NAMESPACE + "getPassword", admin_id);
    }

    //获取管理员ID
    public String getUsername(final long admin_id){
        return sqlSessionTemplate.selectOne(NAMESPACE + "getUsername", admin_id);
    }




}
