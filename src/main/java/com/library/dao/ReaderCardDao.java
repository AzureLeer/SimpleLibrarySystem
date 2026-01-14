package com.library.dao;

import com.library.bean.ReaderCard;
import com.library.bean.ReaderInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ReaderCardDao {
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;
    private final static String NAMESPACE = "com.library.dao.ReaderCardDao.";

    //登录
    public int getIdMatchCount(final long reader_id,final String password){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("reader_id",reader_id);
        map.put("password",password);
        return sqlSessionTemplate.selectOne(NAMESPACE + "getIdMatchCount",map);
    }

    //查询借阅卡
    public ReaderCard findReaderCardByReaderId(final long reader_id){
        return sqlSessionTemplate.selectOne(NAMESPACE + "findReaderCardByReaderId",reader_id);
    }

    //重置密码
    public int resetPassword(final long reader_id,final String newPassword){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("reader_id", reader_id);
        map.put("newPassword", newPassword);
        return sqlSessionTemplate.update(NAMESPACE + "resetPassword", map);
    }

    //添加借阅卡
    public int addReaderCard(final ReaderInfo readerInfo,final String password){
        String username = readerInfo.getName();
        long reader_id = readerInfo.getReader_id();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        map.put("reader_id", reader_id);
        map.put("password", password);
        return sqlSessionTemplate.insert(NAMESPACE + "addReaderCard", map);
    }

    //查询密码
    public String getPassword(final long reader_id){
        return sqlSessionTemplate.selectOne(NAMESPACE + "getPassword", reader_id);
    }

    //删除借阅卡
    public int deleteReaderCard(final long reader_id){
        return sqlSessionTemplate.delete(NAMESPACE + "deleteReaderCard", reader_id);
    }
}
