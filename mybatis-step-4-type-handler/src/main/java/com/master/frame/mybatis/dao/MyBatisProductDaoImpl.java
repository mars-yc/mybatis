package com.master.frame.mybatis.dao;

import com.master.frame.mybatis.domain.Product;
import com.master.frame.mybatis.mapper.ProductMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import java.util.List;

public class MyBatisProductDaoImpl {

    private static final Logger logger = Logger.getLogger(MyBatisProductDaoImpl.class);
    private SqlSessionFactory sqlSessionFactory;

    public MyBatisProductDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int add(final Product product) {
        return this.execute(new Action<Integer>() {
            @Override
            public Integer doInAction(ProductMapper mapper) {
                return mapper.add(product);
            }
        });
    }

    public List<Product> load() {
        return this.execute(new Action<List<Product>>() {
            @Override
            public List<Product> doInAction(ProductMapper mapper) {
                return mapper.load();
            }
        });
    }

    public void createProductTbl() {
        this.execute(new Action<Object>() {
            @Override
            public Object doInAction(ProductMapper mapper) {
                mapper.createProductTbl();
                return null;
            }
        });
    }

    public void dropIfExistProductTbl() {
        this.execute(new Action<Void>() {
            @Override
            public Void doInAction(ProductMapper mapper) {
                mapper.dropIfExistProductTbl();
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
            ProductMapper mapper = sqlSession.getMapper(ProductMapper.class);
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
        T doInAction(ProductMapper mapper);
    }

}