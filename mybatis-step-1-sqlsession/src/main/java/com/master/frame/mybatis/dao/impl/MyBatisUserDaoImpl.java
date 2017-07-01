package com.master.frame.mybatis.dao.impl;

import com.master.frame.mybatis.dao.UserDao;
import com.master.frame.mybatis.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class MyBatisUserDaoImpl implements UserDao {

    private SqlSessionFactory sqlSessionFactory;

    public MyBatisUserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<User> load() {
        return this.execute(new UserDaoCallBack<List<User>>() {
            public List<User> doInAction(SqlSession sqlSession) {
                return sqlSession.selectList("UserMapper.load");
            }
        });
    }

    public User findUserByUserName(final String username) {
        return this.execute(new UserDaoCallBack<User>() {
            public User doInAction(SqlSession sqlSession) {
                return sqlSession.selectOne("UserMapper.findUserByName", username);
            }
        });
    }

    public int add(final User user) {
        return this.execute(new UserDaoCallBack<Integer>() {
            public Integer doInAction(SqlSession sqlSession) {
                return sqlSession.insert("UserMapper.add", user);
            }
        });
    }

    public int delete(final User user) {
        return this.execute(new UserDaoCallBack<Integer>() {
            public Integer doInAction(SqlSession sqlSession) {
                return sqlSession.delete("UserMapper.delete", user);
            }
        });
    }

    public int update(final User user) {
        return this.execute(new UserDaoCallBack<Integer>() {
            public Integer doInAction(SqlSession sqlSession) {
                return sqlSession.delete("UserMapper.update", user);
            }
        });
    }

    public void createUserTbl() {
        this.execute(new UserDaoCallBack<Integer>() {
            public Integer doInAction(SqlSession sqlSession) {
                return sqlSession.update("UserMapper.createUserTbl");
            }
        });
    }

    public void dropIfExistUserTbl() {
        this.execute(new UserDaoCallBack<Integer>() {
            public Integer doInAction(SqlSession sqlSession) {
                return sqlSession.update("UserMapper.dropIfExistUserTbl");
            }
        });
    }

    private <T> T execute(UserDaoCallBack<T> action) {
        SqlSession sqlSession = null;
        T ret;
        try {
            sqlSession = sqlSessionFactory.openSession();
            ret = action.doInAction(sqlSession);
            sqlSession.commit();
        } finally {
            if(null != sqlSession) {
                sqlSession.close();
            }
        }
        return ret;
    }

}

interface UserDaoCallBack<T> {

    T doInAction(SqlSession sqlSession);

}