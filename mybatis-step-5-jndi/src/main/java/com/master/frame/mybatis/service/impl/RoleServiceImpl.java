package com.master.frame.mybatis.service.impl;

import com.master.frame.mybatis.domain.Role;
import com.master.frame.mybatis.mapper.RoleMapper;
import com.master.frame.mybatis.service.RoleService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    private static final Logger logger = Logger.getLogger(RoleServiceImpl.class);
    private static final RoleService instance = new RoleServiceImpl();
    private SqlSessionFactory sqlSessionFactory;

    private RoleServiceImpl() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        this.dropIfExistRoleTbl();
        this.createRoleTbl();
    }

    public static RoleService getInstance() {
        return instance;
    }

    @Override
    public void add(final Role role) {
        this.execute(new Action<Void>() {
            @Override
            public Void doInAction(RoleMapper mapper) {
                mapper.add(role);
                return null;
            }
        });
    }

    @Override
    public List<Role> load() {
        return this.execute(new Action<List<Role>>() {
            @Override
            public List<Role> doInAction(RoleMapper mapper) {
                return mapper.load();
            }
        });
    }

    private void createRoleTbl() {
        this.execute(new Action<Void>() {
            @Override
            public Void doInAction(RoleMapper mapper) {
                mapper.createRoleTbl();
                return null;
            }
        });
    }

    private void dropIfExistRoleTbl() {
        this.execute(new Action<Object>() {
            @Override
            public Object doInAction(RoleMapper mapper) {
                mapper.dropIfExistRoleTbl();
                return null;
            }
        });
    }

    private <T> T execute(Action<T> action) {
        SqlSession sqlSession = null;
        T ret;
        try {
            sqlSession = sqlSessionFactory.openSession();
            if(logger.isDebugEnabled()) {
                logger.debug("sqlSession opened");
            }
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
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

    interface Action<T> {
        T doInAction(RoleMapper mapper);
    }

}