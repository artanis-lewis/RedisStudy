package com.zhouhao.study;

import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * ����Redis�Ĳ�����
 *
 * @author : zhouhao
 * @date : Created in 2019/4/10 6:50
 */
public class TestRedis {
    private static Jedis jedis;

    /**
     * ��ʼ��Jedis����
     * @author : zhouhao
     * @date : 2019/4/12 7:19
     */
    @BeforeClass
    public static void initRedis(){
        jedis = new Jedis("192.168.42.128");
        jedis.auth("zhouhao960824");
        System.out.println(jedis.ping() + "------------Redis������");
    }

    /**
     * Reids�������ݽṹ-�ַ���String-�Ļ���ʹ��
     * @author : zhouhao
     * @date : 2019/4/12 7:19
     */
    @Test
    public void testRedisString() {
        //��ӻ��޸�String
        System.out.println("���name:" + jedis.set("name","zhouhao"));
        //��ȡString
        System.out.println("��ȡname:" + jedis.get("name"));
        //ɾ��String
        System.out.println("ɾ��name��" + jedis.del("name"));
        //String����
        System.out.println("���age:" + jedis.set("age","22"));
        System.out.println("��ȡage:" + jedis.get("age"));
        System.out.println("age����:" + jedis.incr("age"));
        //String����ָ����С
        System.out.println("age����5:" + jedis.incrBy("age",5L));
        //��Ӳ�ָ������ʱ�䣨�룩
        System.out.println("���school�����ù���ʱ��:" + jedis.setex("school",5,"cclgdx"));
        //Thread.sleep(6000);
        System.out.println("��ȡschool:" + jedis.get("school"));
        //����ֵ�Ƿ����ѡ�������
        System.out.println("�����Ƿ�������name:" + jedis.setnx("name","zhouhao"));
        System.out.println("�����Ƿ�������name:" + jedis.setnx("name","zhouhao"));
    }

    /**
     * Reids�������ݽṹ-�б�List-�Ļ���ʹ��
     * @author : zhouhao
     * @date : 2019/4/12 7:44
     */
    @Test
    public void testRedisList(){
        //ɾ��
        System.out.println("ɾ��languages:" + jedis.del("languages"));
        //����߷���
        System.out.println("����߷���languages:" + jedis.lpush("languages","java","python","C++"));
        //���ұߵ���
        System.out.println("���ұߵ���:" + jedis.rpop("languages"));
        //�Ƴ�ָ��Ԫ��count : �Ƴ����� count > 0 ����߿�ʼ��count < 0 ���ұ߿�ʼ��count == 0 �Ƴ�ȫ��
        System.out.println("�Ƴ�python:" + jedis.lrem("languages",0,"python"));
        //��ȡ����
        System.out.println("��ȡlanguages����:" + jedis.llen("languages"));
        //�����±��ȡ
        System.out.println("�����±��ȡԪ��:" + jedis.lindex("languages",1L));
        //���ݷ�Χ��ȡ
        System.out.println("��ȡlanguages����Ԫ��:" + jedis.lrange("languages",0L,-1L));
        System.out.println("��ȡlanguages����Ԫ��:" + jedis.lrange("languages",0L,0L));
        //��list��Ƭ
        System.out.println("���ݷ�Χ�ü�languages:" + jedis.ltrim("languages",0L,0L));
        System.out.println("��ȡlanguages����Ԫ��:" + jedis.lrange("languages",0L,-1L));
    }

    /**
     * Reids�������ݽṹ-�ֵ�Hash-�Ļ���ʹ��
     * @author : zhouhao
     * @date : 2019/4/12 7:44
     */
    @Test
    public void testRedisHash(){
        //ɾ��
        System.out.println("ɾ��person:" + jedis.del("person"));
        //����ֵ�
        Map<String,String> person = new HashMap<>();
        person.put("name","zhouhao");
        person.put("age","22");
        System.out.println("����ֵ�person:" + jedis.hset("person",person));
        //����ֵ�Ԫ��
        System.out.println("����ֵ�Ԫ��:" + jedis.hset("person","school","cclgdx"));
        //��ȡ�ֵ�
        System.out.println("��ȡ�ֵ�:" + jedis.hgetAll("person"));
        //��ȡ�ֵ�Ԫ��
        System.out.println("��ȡ�ֵ�Ԫ��,name:" + jedis.hget("person","name"));
        //��ȡ�ֵ��С
        System.out.println("��ȡ�ֵ��С:" + jedis.hlen("person"));
        //ɾ���ֵ�Ԫ��
        System.out.println("ɾ���ֵ�Ԫ��school:" + jedis.hdel("person","school"));
    }

    /**
     * Reids�������ݽṹ-����Set-�Ļ���ʹ��
     * @author : zhouhao
     * @date : 2019/4/12 7:44
     */
    @Test
    public void testRedisSet(){
        //ɾ������
        System.out.println("ɾ��languages" + jedis.del("languages"));
        //���Ԫ��
        System.out.println("���Ԫ��:" + jedis.sadd("languages","java","C++","python"));
        //����Ԫ��
        System.out.println("����Ԫ��:" + jedis.spop("languages"));
        //���г�Ա
        System.out.println("����Ԫ��:" + jedis.smembers("languages"));
        //�Ƿ����
        System.out.println("java�Ƿ����:" + jedis.sismember("languages","java"));
        System.out.println("PHP�Ƿ����:" + jedis.sismember("languages","PHP"));
        //���ϴ�С
        System.out.println("���ϴ�С:" + jedis.scard("languages"));
        //�Ƴ�ָ��Ԫ��
        System.out.println("�Ƴ�java:" + jedis.srem("languages","java"));
    }

    /**
     * Reids�������ݽṹ-���򼯺�Zset-�Ļ���ʹ��
     * @author : zhouhao
     * @date : 2019/4/12 7:44
     */
    @Test
    public void testRedisZset(){
        //ɾ�����򼯺�
        System.out.println("ɾ�����򼯺�:" + jedis.del("languages"));
        //������򼯺�-map
        Map<String,Double> languages = new HashMap<>();
        languages.put("java",1.1D);
        languages.put("C++",1.2D);
        languages.put("python",1.3D);
        System.out.println("������򼯺�:" + jedis.zadd("languages",languages));
        //���Ԫ��
        System.out.println("���Ԫ��PHP:" + jedis.zadd("languages",1.5D,"PHP"));
        //Ԫ����λ
        System.out.println("PHPλ��:" + jedis.zrank("languages","PHP"));
        //Ԫ�ط���
        System.out.println("PHP����:" + jedis.zscore("languages","PHP"));
        //ɾ��Ԫ��
        System.out.println("ɾ��Ԫ��PHP:" + jedis.zrem("languages","PHP"));
        //��ȡ��Χ���±��Ԫ��
        System.out.println("��ȡ0-1��Ԫ��:" + jedis.zrange("languages",0L,1L));
        //�Է����ȡ��Χ���±��Ԫ��
        System.out.println("�����ȡ0-1��Ԫ��:" + jedis.zrevrange("languages",0L,1L));
        //��ȡ��Χ�ڷ�����Ԫ��
        System.out.println("����������[1.2-1.3]��Ԫ��:" + jedis.zrangeByScore("languages",1.2D,1.3D));
        //���򼯺ϴ�С
        System.out.println("���򼯺ϴ�С:" + jedis.zcard("languages"));
    }

    @Test
    public void testExist(){
        //ɾ��Ԫ��
        System.out.println("ɾ��������:" + jedis.del("languages"));
        //������򼯺�-map
        Map<String,Double> languages = new HashMap<>();
        languages.put("java",1.1D);
        languages.put("C++",1.2D);
        languages.put("python",1.3D);
        System.out.println("������򼯺�:" + jedis.zadd("languages",languages));
        //��ȡ��Χ���±��Ԫ��
        System.out.println("��ȡ����Ԫ��:" + jedis.zrange("languages",0L,-1L));

        languages = new HashMap<>();
        languages.put("C++",1.9D);
        languages.put("PHP",1.1D);
        System.out.println("������򼯺�:" + jedis.zadd("languages",languages));
        //��ȡ��Χ���±��Ԫ��
        System.out.println("��ȡ����Ԫ��:" + jedis.zrange("languages",0L,-1L));
    }


}


























