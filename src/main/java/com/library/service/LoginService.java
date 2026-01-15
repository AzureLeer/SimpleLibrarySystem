package com.library.service;

import com.library.bean.ReaderCard;
import com.library.dao.AdminDao;
import com.library.dao.ReaderCardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private ReaderCardDao readerCardDao;
    @Autowired
    private AdminDao adminDao;

    //读者登录
    public boolean hasMatchReader(long readerId,String password){
        return readerCardDao.getIdMatchCount(readerId,password)>0;
    }
    //获取读者信息
    public ReaderCard findReaderCardByReaderId(long readerId){
        return readerCardDao.findReaderCardByReaderId(readerId);
    }

    //管理员登录
    public boolean hasMatchAdmin(long adminId,String password){
        return adminDao.getMatchCount(adminId,password)==1;
    }
    //获取管理员信息
    public String getAdminUsername(long adminId){
        return adminDao.getUsername(adminId);
    }


    //管理员重置密码
    public boolean adminRePassword(long adminId,String newPassword) {
        return adminDao.resetPassword(adminId,newPassword)>0;
    }

    //获取管理员密码
    public String getAdminPassword(long adminId){
        return adminDao.getPassword(adminId);
    }

    //读者重置密码
    public boolean readerRePassword(long readerId,String newPassword) {
        return readerCardDao.resetPassword(readerId,newPassword)>0;
    }
    //获取读者密码
    public String getReaderPassword(long readerId){
        return readerCardDao.getPassword(readerId);
    }



}
