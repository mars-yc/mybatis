package com.master.frame.mybatis.dao;

import com.master.frame.mybatis.domain.User;
import com.master.frame.mybatis.mapper.UserMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyBatisUserDaoImplTest {

    private static final String DB_CONFIGURATION_FILE_PATH = "/db.properties";
    private static final Logger logger = Logger.getLogger(MyBatisUserDaoImplTest.class);
    private static SqlSessionFactory sqlSessionFactory;
    private static MyBatisUserDaoImpl userDao;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        setUpEnv();
        userDao = new MyBatisUserDaoImpl(sqlSessionFactory);
        logger.info("wired all the beans");
    }

    public static void setUpEnv() {
        Properties properties = new Properties();
        InputStream is = Object.class.getResourceAsStream(DB_CONFIGURATION_FILE_PATH);
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = properties.getProperty("SYSTEM_DB_URL");
        String driver = properties.getProperty("SYSTEM_DB_DRIVER");
        String username = properties.getProperty("SYSTEM_DB_USERNAME");
        String password = properties.getProperty("SYSTEM_DB_PASSWORD");

        DataSource dataSource = new PooledDataSource(driver, url, username, password);
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        //configuration.getTypeAliasRegistry().registerAlias("com.master.frame.mybatis.mapper.User", User.class);
        configuration.addMapper(UserMapper.class);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test7Load() throws Exception {
        List<User> users = userDao.load();
        for(User user : users) {
            logger.info(user);
        }
    }

    @Test
    public void test5FindUserByUserName() throws Exception {
        User user = userDao.findUserByUserName("lily");
        logger.info(user);
    }

    @Test
    public void test3Add() throws Exception {
        User user1 = new User("lily", "女", "lily@hot.mail");
        User user2 = new User("lucy", "女", "lucy@hotmail.com");
        userDao.add(user1);
        userDao.add(user2);
    }

    @Test
    public void test6Delete() throws Exception {
        User user = new User("lily", "女", "lily@hot.mail");
        userDao.delete(user);
    }

    @Test
    public void test4Update() throws Exception {
        User user = new User("lily", "男", "sdssds@hot.mail");
        userDao.update(user);
    }

    @Test
    public void test2CreateUserTbl() {
        userDao.createUserTbl();
    }

    @Test
    public void test1DropIfExistUserTbl() {
        userDao.dropIfExistUserTbl();
    }

}