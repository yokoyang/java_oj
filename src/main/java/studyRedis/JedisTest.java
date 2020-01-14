package studyRedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

interface CallWithJedis {
    public void call(Jedis jedis);
}

class RedisPool {
    private JedisPool pool;

    public RedisPool() {
        this.pool = new JedisPool();
    }

    public void execute(CallWithJedis caller) {
        try (Jedis jedis = pool.getResource()) {
            caller.call(jedis);
        }
    }

}

class Holder<T> {
    private T value;

    public Holder() {

    }

    public Holder(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

public class JedisTest {
    public static void main(String[] args) {
        RedisPool redisPool = new RedisPool();
        Holder<Long> holder = new Holder<>();
        redisPool.execute(new CallWithJedis() {
            @Override
            public void call(Jedis jedis) {
                jedis.set("a", "b");
                holder.setValue(jedis.zcard("a"));
            }
        });
        System.out.println(holder.getValue());
    }

}
