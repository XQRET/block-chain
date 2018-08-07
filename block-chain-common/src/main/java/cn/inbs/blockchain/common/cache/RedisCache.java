package cn.inbs.blockchain.common.cache;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description: redis缓存实现
 * @Package: cn.inbs.blockchain.common.cache
 * @ClassName: RedisCache.java
 * @Date: 2018年04月25-17:18
 * @Author: createBy:zhangmingyang
 **/
public class RedisCache<T> implements BaseCache<T> {

    private RedisSerializer<String> keySerializer = new StringRedisSerializer();
    private RedisSerializer<Object> valSerializer;

    private RedisTemplate<String, T> redisTemplate;

    public RedisCache(Class<T> t,
                      RedisTemplate<String, T> redisTemplate,
                      int cacheIndex) {
        this.valSerializer = new JdkSerializationRedisSerializer();
        this.redisTemplate = redisTemplate;
        ((JedisConnectionFactory) this.redisTemplate.getConnectionFactory()).setDatabase(cacheIndex);
    }

    @Override
    public void put(String key, T val) {
        if (key == null) {
            return;
        }
        final byte[] cacheKey = keySerializer.serialize(key.toLowerCase());
        final byte[] cacheValue = valSerializer.serialize(val);

        redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(cacheKey, cacheValue);
                return null;
            }
        });
    }

    @Override
    public void put(String key, T val, final long expire) {
        if (key == null) {
            return;
        }
        final byte[] cacheKey = keySerializer.serialize(key.toLowerCase());
        final byte[] cacheValue = valSerializer.serialize(val);
        redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.setEx(cacheKey, expire, cacheValue);
                return null;
            }
        });
    }

    @Override
    public void update(String key, T val) {
        if (key == null) {
            return;
        }
        final byte[] cacheKey = keySerializer.serialize(key.toLowerCase());
        final byte[] cacheValue = valSerializer.serialize(val);

        redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(cacheKey, cacheValue, null, SetOption.ifPresent());
                return null;
            }
        });
    }


    @Override
    public void update(String key, T val, final long expire) {
        if (key == null) {
            return;
        }
        final byte[] cacheKey = keySerializer.serialize(key.toLowerCase());
        final byte[] cacheValue = valSerializer.serialize(val);
        redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.set(cacheKey, cacheValue, Expiration.from(expire, null), SetOption.ifPresent());
                return null;
            }
        });
    }

    @Override
    public T get(String key) {
        if (key == null) {
            return null;
        }
        final byte[] cacheKey = keySerializer.serialize(key.toLowerCase());

        return redisTemplate.execute(new RedisCallback<T>() {
            @SuppressWarnings("unchecked")
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                if (!connection.exists(cacheKey)) {
                    return null;
                }
                return (T) valSerializer.deserialize(connection.get(cacheKey));
            }
        });
    }

    @Override
    public boolean has(String key) {
        if (key == null) {
            return false;
        }
        final byte[] cacheKey = keySerializer.serialize(key.toLowerCase());
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(cacheKey);
            }
        });
    }

    @Override
    public void del(String key) {
        final byte[] cacheKey = keySerializer.serialize(key.toLowerCase());
        redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.del(cacheKey);
                return null;
            }
        });
    }

    @Override
    public void clear() {
        redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return null;
            }
        });
    }
}
