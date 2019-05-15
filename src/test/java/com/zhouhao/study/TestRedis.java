package com.zhouhao.study;

import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * 连接Redis的测试类
 *
 * @author : zhouhao
 * @date : Created in 2019/4/10 6:50
 */
public class TestRedis {
    private static Jedis jedis;

    /**
     * 初始化Jedis连接
     * @author : zhouhao
     * @date : 2019/4/12 7:19
     */
    @BeforeClass
    public static void initRedis(){
        jedis = new Jedis("192.168.42.128");
        jedis.auth("zhouhao960824");
        System.out.println(jedis.ping() + "------------Redis已连接");
    }

    /**
     * Reids基础数据结构-字符串String-的基本使用
     * @author : zhouhao
     * @date : 2019/4/12 7:19
     */
    @Test
    public void testRedisString() {
        //添加或修改String
        System.out.println("添加name:" + jedis.set("name","zhouhao"));
        //获取String
        System.out.println("获取name:" + jedis.get("name"));
        //删除String
        System.out.println("删除name：" + jedis.del("name"));
        //String自增
        System.out.println("添加age:" + jedis.set("age","22"));
        System.out.println("获取age:" + jedis.get("age"));
        System.out.println("age自增:" + jedis.incr("age"));
        //String自增指定大小
        System.out.println("age自增5:" + jedis.incrBy("age",5L));
        //添加并指定过期时间（秒）
        System.out.println("添加school并设置过期时间:" + jedis.setex("school",5,"cclgdx"));
        //Thread.sleep(6000);
        System.out.println("获取school:" + jedis.get("school"));
        //根据值是否存在选择性添加
        System.out.println("根据是否存在添加name:" + jedis.setnx("name","zhouhao"));
        System.out.println("根据是否存在添加name:" + jedis.setnx("name","zhouhao"));
    }

    /**
     * Reids基础数据结构-列表List-的基本使用
     * @author : zhouhao
     * @date : 2019/4/12 7:44
     */
    @Test
    public void testRedisList(){
        //删除
        System.out.println("删除languages:" + jedis.del("languages"));
        //从左边放入
        System.out.println("从左边放入languages:" + jedis.lpush("languages","java","python","C++"));
        //从右边弹出
        System.out.println("从右边弹出:" + jedis.rpop("languages"));
        //移除指定元素count : 移除个数 count > 0 从左边开始，count < 0 从右边开始，count == 0 移除全部
        System.out.println("移除python:" + jedis.lrem("languages",0,"python"));
        //获取长度
        System.out.println("获取languages长度:" + jedis.llen("languages"));
        //根据下标获取
        System.out.println("根据下标获取元素:" + jedis.lindex("languages",1L));
        //根据范围获取
        System.out.println("获取languages所有元素:" + jedis.lrange("languages",0L,-1L));
        System.out.println("获取languages部分元素:" + jedis.lrange("languages",0L,0L));
        //对list切片
        System.out.println("根据范围裁剪languages:" + jedis.ltrim("languages",0L,0L));
        System.out.println("获取languages所有元素:" + jedis.lrange("languages",0L,-1L));
    }

    /**
     * Reids基础数据结构-字典Hash-的基本使用
     * @author : zhouhao
     * @date : 2019/4/12 7:44
     */
    @Test
    public void testRedisHash(){
        //删除
        System.out.println("删除person:" + jedis.del("person"));
        //添加字典
        Map<String,String> person = new HashMap<>();
        person.put("name","zhouhao");
        person.put("age","22");
        System.out.println("添加字典person:" + jedis.hset("person",person));
        //添加字典元素
        System.out.println("添加字典元素:" + jedis.hset("person","school","cclgdx"));
        //获取字典
        System.out.println("获取字典:" + jedis.hgetAll("person"));
        //获取字典元素
        System.out.println("获取字典元素,name:" + jedis.hget("person","name"));
        //获取字典大小
        System.out.println("获取字典大小:" + jedis.hlen("person"));
        //删除字典元素
        System.out.println("删除字典元素school:" + jedis.hdel("person","school"));
    }

    /**
     * Reids基础数据结构-集合Set-的基本使用
     * @author : zhouhao
     * @date : 2019/4/12 7:44
     */
    @Test
    public void testRedisSet(){
        //删除集合
        System.out.println("删除languages" + jedis.del("languages"));
        //添加元素
        System.out.println("添加元素:" + jedis.sadd("languages","java","C++","python"));
        //弹出元素
        System.out.println("弹出元素:" + jedis.spop("languages"));
        //所有成员
        System.out.println("所有元素:" + jedis.smembers("languages"));
        //是否存在
        System.out.println("java是否存在:" + jedis.sismember("languages","java"));
        System.out.println("PHP是否存在:" + jedis.sismember("languages","PHP"));
        //集合大小
        System.out.println("集合大小:" + jedis.scard("languages"));
        //移除指定元素
        System.out.println("移除java:" + jedis.srem("languages","java"));
    }

    /**
     * Reids基础数据结构-有序集合Zset-的基本使用
     * @author : zhouhao
     * @date : 2019/4/12 7:44
     */
    @Test
    public void testRedisZset(){
        //删除有序集合
        System.out.println("删除有序集合:" + jedis.del("languages"));
        //添加有序集合-map
        Map<String,Double> languages = new HashMap<>();
        languages.put("java",1.1D);
        languages.put("C++",1.2D);
        languages.put("python",1.3D);
        System.out.println("添加有序集合:" + jedis.zadd("languages",languages));
        //添加元素
        System.out.println("添加元素PHP:" + jedis.zadd("languages",1.5D,"PHP"));
        //元素排位
        System.out.println("PHP位置:" + jedis.zrank("languages","PHP"));
        //元素分数
        System.out.println("PHP分数:" + jedis.zscore("languages","PHP"));
        //删除元素
        System.out.println("删除元素PHP:" + jedis.zrem("languages","PHP"));
        //获取范围内下标的元素
        System.out.println("获取0-1的元素:" + jedis.zrange("languages",0L,1L));
        //以反序获取范围内下标的元素
        System.out.println("反序获取0-1的元素:" + jedis.zrevrange("languages",0L,1L));
        //获取范围内分数的元素
        System.out.println("分数区间在[1.2-1.3]的元素:" + jedis.zrangeByScore("languages",1.2D,1.3D));
        //有序集合大小
        System.out.println("有序集合大小:" + jedis.zcard("languages"));
    }

    @Test
    public void testExist(){
        //删除元素
        System.out.println("删除有序结合:" + jedis.del("languages"));
        //添加有序集合-map
        Map<String,Double> languages = new HashMap<>();
        languages.put("java",1.1D);
        languages.put("C++",1.2D);
        languages.put("python",1.3D);
        System.out.println("添加有序集合:" + jedis.zadd("languages",languages));
        //获取范围内下标的元素
        System.out.println("获取所有元素:" + jedis.zrange("languages",0L,-1L));

        languages = new HashMap<>();
        languages.put("C++",1.9D);
        languages.put("PHP",1.1D);
        System.out.println("添加有序集合:" + jedis.zadd("languages",languages));
        //获取范围内下标的元素
        System.out.println("获取所有元素:" + jedis.zrange("languages",0L,-1L));
    }


}


























