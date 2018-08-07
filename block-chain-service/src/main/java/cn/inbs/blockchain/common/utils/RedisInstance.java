package cn.inbs.blockchain.common.utils;

import cn.inbs.blockchain.common.property.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisInstance {
    private static Logger logger = LoggerFactory.getLogger(RedisInstance.class);

    private JedisPool jedisPool = null;

    private RedisInstance() {
    }

    static class RedisInstanceHandler {
        static RedisInstance ins = new RedisInstance();
    }

    public static RedisInstance getIns() {
        return RedisInstanceHandler.ins;
    }

    private static final String REDIS_IP_KEY = "session.redis.ip";
    private static final String REDIS_IP_DEFAULT_VAL = "127.0.0.1";
    private static final String REDIS_PORT_KEY = "session.redis.port";
    private static final String REDIS_PORT_DEFAULT_VAL = "6379";

    /**
     * 创建redis连接
     *
     * @return
     */
    public boolean start() {
        String redisIp = null;//redisIP
        Integer redisPort = null;//redis端口
        try {
            //获取redisIP及端口
            redisIp = PropertyUtils.getStringValue(REDIS_IP_KEY, REDIS_IP_DEFAULT_VAL);
            redisPort = Integer.valueOf(PropertyUtils.getStringValue(REDIS_PORT_KEY, REDIS_PORT_DEFAULT_VAL));

            //创建redis连接池
            this.jedisPool = new JedisPool(redisIp, redisPort);

            if (logger.isInfoEnabled()) {
                logger.info("Jedis connect to redis server [{}:{}]",
                        redisIp,
                        redisPort);
            }
            return true;
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("Jedis can not connect to redis server [{}:{}]",
                        redisIp,
                        redisPort,
                        e);
            }
            return false;
        }
    }

    /**
     * 关闭redis连接
     *
     * @param jedis
     */
    public void close(Jedis jedis) {
        try {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("CDO Jedis closes the exception,cause by:", e);
            }
            if (jedis.isConnected()) {
                jedis.quit();
                jedis.disconnect();
            }
        }
    }

    /**
     * 判断是否存在缓存
     *
     * @param strName
     * @return
     * @author tanglei
     * @date 2017-11-2
     */
    public boolean exists(String strName) {
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            return jedis.exists(strName);
        } finally {
            close(jedis);
        }
    }

    //默认24小时,秒
    private static final int nDefaultExpireTime = 60 * 60 * 24;

    /**
     * 默认24小时失效
     *
     * @param strName
     * @param strValue
     */
    public boolean set(String strName, String strValue) {
        return set(strName, strValue, nDefaultExpireTime);
    }

    public boolean set(String strName, String strValue, int nExpireTime) {
        Jedis jedis = null;
        try {
            if (nExpireTime < 1) {
                throw new RuntimeException("nExpireTime must be great 0!");
            }
            jedis = this.jedisPool.getResource();
            String strSetFlag = jedis.setex(strName, nExpireTime, strValue);
            logger.info("jedis setex strName=" + strName + " strSetFlag=" + strSetFlag);

            if ("OK".equals(strSetFlag)) {
                return true;
            } else {
                logger.error("jedisCluster.setex fail!!! strName=" + strName + " strSetFlag=" + strSetFlag);
                return false;
            }
        } catch (Exception e) {
            logger.error("jedisCluster setex error!!! " + e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return false;
    }


    /**
     * 名称为key的string增1操作 +1
     *
     * @param strName
     * @return 返回+1后的最终值
     * @author tanglei
     * @date 2017-11-2
     */
    public long incr(String strName) {
        long count = 0;
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            count = jedis.incr(strName);
            logger.info("jedisCluster.incr strName=" + strName + ",count=" + count);
        } catch (Exception e) {
            logger.error("jedisCluster.incr error!!! strName=" + strName, e);
        } finally {
            close(jedis);
        }
        return count;
    }

    /**
     * 名称为key的string增加long
     *
     * @param strName
     * @param addlength
     * @return 返回+addlength后的最终值
     * @author tanglei
     * @date 2017-11-2
     */
    public long incrby(String strName, long addlength) {
        long count = 0;
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            count = jedis.incrBy(strName, addlength);
            logger.info("jedisCluster.incrBy strName=" + strName + ",count=" + count);
        } catch (Exception e) {
            logger.error("jedisCluster.incrBy error!!! strName=" + strName, e);
        } finally {
            close(jedis);
        }
        return count;
    }

    /**
     * 名称为key的string减1操作
     *
     * @param strName
     * @return 返回-1后的最终值
     * @author tanglei
     * @date 2017-11-2
     */
    public long decr(String strName) {
        long count = 0;
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            count = jedis.decr(strName);
            logger.info("jedisCluster.decr strName=" + strName + ",count=" + count);
        } catch (Exception e) {
            logger.error("jedisCluster.decr error!!! strName=" + strName, e);
        } finally {
            close(jedis);
        }
        return count;
    }

    /**
     * 名称为key的string减少long
     *
     * @param strName
     * @return 返回-long后的最终值
     * @author tanglei
     * @date 2017-11-2
     */
    public long decrby(String strName, long subtractLeng) {
        long count = 0;
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            count = jedis.decrBy(strName, subtractLeng);
            logger.info("jedisCluster.decrBy strName=" + strName + ",count=" + count);
        } catch (Exception e) {
            logger.error("jedisCluster.decrBy error!!! strName=" + strName, e);
        }
        return count;
    }


    /**
     * 只获取，不延时
     *
     * @param strName
     * @return
     */
    public String get(String strName) {
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            return jedis.get(strName);
        } catch (Exception e) {

        } finally {
            close(jedis);
        }
        return null;
    }

    public boolean expire(String strName, int nExpireTime) {
        boolean bExireResult = false;
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            Long nExpireRes = jedis.expire(strName, nExpireTime);
            if (nExpireRes != 1) {
                logger.warn("jedisCluster.expire fail strName=" + strName);
            } else {
                bExireResult = true;
            }
        } catch (Exception e) {
            logger.error("jedisCluster.expire error!!! " + e.getMessage(), e);
            return bExireResult;
        } finally {
            close(jedis);
        }
        return bExireResult;
    }

    public boolean del(String strName) {
        boolean bDelResult = false;
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            long lDelRes = jedis.del(strName);
            logger.info("jedisCluster del strName=" + strName);
            if (lDelRes != 1) {
                logger.error("jedisCluster.del fail strName=" + strName);
            } else {
                bDelResult = true;
            }
        } catch (Exception e) {
            logger.error("jedisCluster.del error!!! " + e.getMessage(), e);
            return bDelResult;
        } finally {
            close(jedis);
        }
        return bDelResult;
    }
}