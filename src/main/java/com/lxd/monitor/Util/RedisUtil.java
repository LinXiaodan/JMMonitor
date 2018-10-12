package com.lxd.monitor.Util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    private static String ADDR = "localhost";

    private static int PORT = 6379;

    private static int MAX_ACTIVE = 1024;

    private static int MAX_IDLE = 200;

    private static int MAX_WAIT = 10000;

    private static int TIMEOUT = 10000;

    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;

    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public synchronized static Jedis getJedis(){
        try{
            if(jedisPool != null){
                Jedis resource = jedisPool.getResource();
                return resource;
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /***
     *释放资源
     */

    public static void returnResource(final Jedis jedis){
        if(jedis != null){
            jedisPool.returnResource(jedis);
        }
    }

    public static void pushString(Jedis jedis, String keyName, String str){
        try{
            jedis.rpush(keyName, str);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String popString(Jedis jedis, String keyName){
        try{
            return jedis.lpop(keyName);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean returnString(Jedis jedis, String keyName, String str){
        try{
            jedis.lpush(keyName, str);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args){
        Jedis jedis = RedisUtil.getJedis();
        String key = "test list";
        RedisUtil.pushString(jedis, key, "a");
        RedisUtil.pushString(jedis, key, "b");
        RedisUtil.pushString(jedis, key, "cccc");
        String a = RedisUtil.popString(jedis, key);
        String b = RedisUtil.popString(jedis, key);
        System.out.println(a);
        System.out.println(b);
        System.out.println(returnString(jedis, key, a));
    }
}

