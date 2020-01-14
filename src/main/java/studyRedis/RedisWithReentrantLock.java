package studyRedis;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.HashMap;
import java.util.Map;

public class RedisWithReentrantLock {
    /*
    Redis 单机版的可重入锁，不能够解决超时问题
     */
    private ThreadLocal<Map<String, Integer>> lockers = new ThreadLocal<>();
    private Jedis jedis;

    public RedisWithReentrantLock(Jedis jedis) {
        this.jedis = jedis;
    }

    private boolean _lock(String key) {
        SetParams setParams = new SetParams();
        //EX second ：设置键的过期时间为 second 秒。
        setParams.ex(5);
        //* NX ：只在键不存在时，才对键进行设置操作。
        setParams.nx();
        return jedis.set(key, "", setParams) != null;
    }

    private void _unlock(String key) {
        jedis.del(key);
    }

    private Map<String, Integer> currentLockers() {
        Map<String, Integer> refs = lockers.get();
        if (refs != null) {
            return refs;
        }
        lockers.set(new HashMap<>());
        return lockers.get();
    }

    public boolean lock(String key) {
        Map<String, Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if (refCnt != null) {
            //之前获取过
            refs.put(key, refCnt + 1);
            return true;
        }
        boolean ok = _lock(key);
        if (!ok) {
            return false;
        }
        refs.put(key, 1);
        return true;
    }

    public boolean unlock(String key) {
        Map<String, Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if (refCnt == null) {
            //之前没获取lock
            return false;
        }
        --refCnt;
        if (refCnt > 0) {
            refs.put(key, refCnt);
        } else {
            refs.remove(key);
            _unlock(key);
        }
        return true;
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        RedisWithReentrantLock redis = new RedisWithReentrantLock(jedis);
        System.out.println(redis.lock("codeholde"));
        System.out.println(redis.lock("codeholde"));
        System.out.println(redis.unlock("codeholde"));
        System.out.println(redis.unlock("codeholde"));
    }
}
