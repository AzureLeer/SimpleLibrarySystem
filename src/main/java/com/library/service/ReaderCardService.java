package com.library.service;

import com.library.bean.ReaderInfo;
import com.library.dao.ReaderCardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReaderCardService {
    @Autowired
    private ReaderCardDao readerCardDao;

    //添加读者借阅卡
    public boolean addReaderCard(ReaderInfo readerInfo, String password) {
        return readerCardDao.addReaderCard(readerInfo, password) > 0;
    }

    //删除读者借阅卡
    public boolean deleteReaderCard(long reader_id) {
        return readerCardDao.deleteReaderCard(reader_id) > 0;
    }

    //更新密码
    public boolean updatePassword(long reader_id, String newPassword) {
        return readerCardDao.resetPassword(reader_id, newPassword) > 0;
    }


}
