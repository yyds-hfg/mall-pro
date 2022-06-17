package com.hacker;

import com.hacker.dao.AttrDao;
import com.hacker.entity.AttrEntity;
import com.hacker.redis.service.impl.RedisStringService;
import com.hacker.service.BrandService;
import com.hacker.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.management.Attribute;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * 1、引入oss-starter
 * 2、配置key，endpoint相关信息即可
 * 3、使用OSSClient 进行相关操作
 */
@Slf4j
@SpringBootTest
public class MallProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    private RedisStringService redisStringService;

    @Test
    public void testFindPath() {
        Long[] catelogPath = categoryService.findCatelogPath(225L);
        log.info("完整路径：{}", Arrays.asList(catelogPath));
    }

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    void contextLoads() {
        ArrayList<AttrEntity> list = new ArrayList<>();
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        AttrDao sessionMapper = sqlSession.getMapper(AttrDao.class);
        for (int i = 0; i < 10; i++) {
            AttrEntity attrEntity = sessionMapper.selectById(null);
            //放入list
            list.add(attrEntity);
        }
        sqlSession.commit();
    }

    /**
     *
     * SpringBoot SpringCloud  redis rocketmq
     * Oracle19c  MybatisPlus
     * mapstruct  jdk11
     * lombok
     * karate
     * xxl-job
     * eureka  openfeign okhttp nginx  tomcat
     * cicd  jekis 持续集成
     * Arthas
     * hibernate validator
     * swagger
     * camunda activity flowable
     * hutool
     * guava  --
     * gson
     * easyexcel poi
     * chiner  -数据库设计根据
     *
     * 分布式事务  a   b
     *
     * MySQL调优
     * 慢SQL怎么优化
     *
     *
     * Jmetr  tps qps  压测  并发的情况下
     *
     * apipost 接口联调 测试 压测
     *
     * 10
     * 卡中台  信用卡参数  信用卡额度  应用中心
     *
     * sonar---checkstyle
     *
     * Java
     * Web
     * springboot
     * springcloud
     * xxl-job
     *
     */

}
