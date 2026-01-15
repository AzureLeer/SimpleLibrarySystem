package com.library.service;

import com.library.bean.Lend;
import com.library.dao.LendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LendService {
    @Autowired
    private LendDao lendDao;

    //借书
    public boolean lendBook(long bookId, long readerId){
        return lendDao.lendBookOne(bookId, readerId) > 0 && lendDao.lendBookTwo(bookId) > 0;
    }

    //还书
    public boolean returnBook(long bookId, long readerId){
        return lendDao.returnBookOne(bookId, readerId) > 0 && lendDao.returnBookTwo(bookId) > 0;
    }

    //获取借阅列表
    public ArrayList<Lend> lendList(){
        return lendDao.lendList();
    }

    //获取个人借阅列表
    public ArrayList<Lend> myLendList(long readerId){
        return lendDao.myLendList(readerId);
    }

    //删除借阅记录
    public boolean deleteLend(long serNum){
        return lendDao.deleteLend(serNum) > 0;
    }

}
