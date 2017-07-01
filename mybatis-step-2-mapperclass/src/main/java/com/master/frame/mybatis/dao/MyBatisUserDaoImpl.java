package com.master.frame.mybatis.dao;

import com.master.frame.mybatis.domain.User;
import com.master.frame.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import java.util.List;

public class MyBatisUserDaoImpl implements UserMapper {

    private static final Logger logger = Logger.getLogger(MyBatisUserDaoImpl.class);
    private SqlSessionFactory sqlSessionFactory;

    public MyBatisUserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<User> load() {
        return this.execute(new UserDaoCallBack<List<User>>() {
            public List<User> doInAction(UserMapper mapper) {
                return mapper.load();
            }
        });
    }

    public User findUserByUserName(final String username) {
        return this.execute(new UserDaoCallBack<User>() {
            public User doInAction(UserMapper mapper) {
                return mapper.findUserByUserName(username);
            }
        });
    }

    public int add(final User user) {
        return this.execute(new UserDaoCallBack<Integer>() {
            public Integer doInAction(UserMapper mapper) {
                return mapper.add(user);
            }
        });
    }

    public int delete(final User user) {
        return this.execute(new UserDaoCallBack<Integer>() {
            public Integer doInAction(UserMapper mapper) {
                return mapper.delete(user);
            }
        });
    }

    public int update(final User user) {
        return this.execute(new UserDaoCallBack<Integer>() {
            public Integer doInAction(UserMapper mapper) {
                return mapper.delete(user);
            }
        });
    }

    public void createUserTbl() {
        this.execute(new UserDaoCallBack<Void>() {
            public Void doInAction(UserMapper mapper) {
                mapper.createUserTbl();
                return null;
            }
        });
    }

    public void dropIfExistUserTbl() {
        this.execute(new UserDaoCallBack<Void>() {
            public Void doInAction(UserMapper mapper) {
                mapper.dropIfExistUserTbl();
                return null;
            }
        });
    }

    private <T> T execute(UserDaoCallBack<T> action) {
        SqlSession sqlSession = null;
        T ret;
        try {
            sqlSession = sqlSessionFactory.openSession();
            if(logger.isDebugEnabled()) {
                logger.debug("sqlSession opened");
            }
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            ret = action.doInAction(mapper);
            sqlSession.commit();
        } finally {
            if(null != sqlSession) {
                sqlSession.close();
                if(logger.isDebugEnabled()) {
                    logger.debug("sqlSession closed");
                }
            }
        }
        return ret;
    }

}

interface UserDaoCallBack<T> {

    T doInAction(UserMapper mapper);

}