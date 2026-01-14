package com.library.dao;


import com.library.bean.ReaderInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReaderInfoDao {
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;
    private final static String NAMESPACE = "com.library.dao.ReaderInfoDao.";

    //获取所有读者信息
    public ArrayList<ReaderInfo> getAllReaderInfo() {
        List<ReaderInfo> readers = sqlSessionTemplate.selectList(NAMESPACE + "getAllReaderInfo");
        return  (ArrayList<ReaderInfo>) readers;
    }

    //通过读者id查找读者信息
    public ReaderInfo findReaderInfoByReaderId(final long reader_id){
        return sqlSessionTemplate.selectOne(NAMESPACE + "findReaderInfoByReaderId", reader_id);
    }


    //添加读者信息
    public final long addReaderInfo(final ReaderInfo readerInfo){
        if(sqlSessionTemplate.insert(NAMESPACE + "addReaderInfo", readerInfo)>0){
            return sqlSessionTemplate.selectOne(NAMESPACE + "getReaderId",readerInfo);
        }else{
            return -1;
        }
    }

    //删除读者信息
    public int deleteReaderInfo(final long reader_id){
        return sqlSessionTemplate.delete(NAMESPACE + "deleteReaderInfo", reader_id);
    }

    //修改读者信息
    public int editReaderInfo(final ReaderInfo readerInfo){
        return sqlSessionTemplate.update(NAMESPACE + "editReaderInfo", readerInfo);
    }

    //修改读者借阅卡
    public int editReaderCard(final ReaderInfo readerInfo){
        return sqlSessionTemplate.update(NAMESPACE + "editReaderCard", readerInfo);
    }
}
