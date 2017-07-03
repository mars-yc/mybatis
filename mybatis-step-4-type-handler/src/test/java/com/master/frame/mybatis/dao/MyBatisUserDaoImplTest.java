package com.master.frame.mybatis.dao;

import com.master.frame.mybatis.domain.Gender;
import com.master.frame.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyBatisUserDaoImplTest {

    private static final String MYBATIS_CONFIGURATION_FILE = "/config.xml";
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
        InputStream is = Object.class.getResourceAsStream(MYBATIS_CONFIGURATION_FILE);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
    }

    public static void setUpEnvWithResource() {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
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
        User user1 = new User("lily", Gender.MALE, "lily@hot.mail");
        User user2 = new User("lucy", Gender.FEMALE, "lucy@hotmail.com");
        userDao.add(user1);
        userDao.add(user2);
    }

    @Test
    public void test6Delete() throws Exception {
        User user = new User("lily", Gender.MALE, "lily@hot.mail");
        Integer cnt = userDao.delete(user);
    }

    @Test
    public void test4Update() throws Exception {
        User user = new User("lily", Gender.FEMALE, "lilsssssy@hot.mail");
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