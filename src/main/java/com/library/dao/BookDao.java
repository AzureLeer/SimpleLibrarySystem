package com.library.dao;


import com.library.bean.Book;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDao {
    private final static String NAMESPACE = "com.library.dao.BookDao.";
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;


    //统计模糊查询的图书数量
    public int matchBook(final String bookName) {
        String sqlId = NAMESPACE + "matchBook";
        String search = "%" + bookName + "%";
        return sqlSessionTemplate.selectOne(sqlId, search);
    }
    //模糊查询图书
    public ArrayList<Book> queryBook(final String searchWord){
        String search = "%" + searchWord + "%";
        List<Book> result =sqlSessionTemplate.selectList(NAMESPACE + "queryBook", search);
        return (ArrayList<Book>) result;
    }
    //查询所有图书
    public ArrayList<Book> getAllBooks() {
        List<Book> result = sqlSessionTemplate.selectList(NAMESPACE + "getAllBooks");
        return (ArrayList<Book>) result;
    }

    //添加图书
    public int addBook(final Book book) {
        return sqlSessionTemplate.insert(NAMESPACE + "addBook", book);
    }
    //删除图书
    public int deleteBook(final long bookId) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteBook", bookId);
    }
    //修改图书
    public int editBook(final Book book) {
        return sqlSessionTemplate.update(NAMESPACE + "editBook", book);
    }
    //查询图书
    public Book getBook(final long bookId) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getBook", bookId);
    }


}
