package com.library.service;

import com.library.bean.ReaderCard;
import com.library.bean.ReaderInfo;
import com.library.dao.ReaderInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReaderIndoService {
    @Autowired
    private ReaderInfoDao readerInfoDao;

    //获取所有读者信息
    public ArrayList<ReaderInfo> readerInfos(){
        return readerInfoDao.getAllReaderInfo();
    }

    //删除读者信息
    public boolean deleteReaderInfo(long reader_id){
        return readerInfoDao.deleteReaderInfo(reader_id) > 0;
    }

    //获取读者信息
    public ReaderInfo getReaderInfo(long reader_id){
        return readerInfoDao.findReaderInfoByReaderId(reader_id);
    }

    //修改读者信息
    public boolean editReaderInfo(ReaderInfo readerInfo){
        return readerInfoDao.editReaderInfo(readerInfo) > 0;
    }

    //修改读者借还信息
    public boolean editReaderCard(ReaderInfo readerInfo){
        return readerInfoDao.editReaderCard(readerInfo) > 0;
    }

    //添加读者信息
    public long addReaderInfo(ReaderInfo readerInfo){
        return readerInfoDao.addReaderInfo(readerInfo);
    }
}
