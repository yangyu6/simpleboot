package com.onion.framework.tool;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author: yu
 * @description: 封装 redis分布式锁操作
 * @create: 2020-08-27 17:04
 **/
@Slf4j
@Component
public class RedisLockUtil {


    private static final long DEFAULT_EXPIRE_UNUSED = 60000L;

    @Autowired
    private RedisLockRegistry redisLockRegistry;

    /**
     * 加锁，如果已经被其他线程锁住或者当前线程不能获取锁则阻塞
     *
     * @param lockKey
     */
    public void lock(String lockKey) {
        Lock lock = obtainLock(lockKey);
        lock.lock();
    }

    /**
     * 尝试加锁，如果已经有其他锁锁住，获取当前线程不能加锁，则返回false，加锁失败；加锁成功则返回true
     *
     * @param lockKey
     * @return
     */
    public boolean tryLock(String lockKey) {
        Lock lock = obtainLock(lockKey);
        return lock.tryLock();
    }

    /**
     * 尝试加锁，如果已经有其他锁锁住，获取当前线程不能加锁，则返回false，加锁失败；加锁成功则返回true
     *
     * @param lockKey
     * @param seconds
     * @return
     */
    public boolean tryLock(String lockKey, long seconds) {
        Lock lock = obtainLock(lockKey);
        try {
            return lock.tryLock(seconds, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }

    /**
     * 解锁
     *
     * @param lockKey
     */
    public void unlock(String lockKey) {
        try {
            Lock lock = obtainLock(lockKey);
            lock.unlock();
            redisLockRegistry.expireUnusedOlderThan(DEFAULT_EXPIRE_UNUSED);
        } catch (Exception e) {
            log.error("分布式锁 [{}] 释放异常", lockKey, e);
        }
    }

    /**
     * 获得锁
     *
     * @param lockKey
     * @return
     */
    private Lock obtainLock(String lockKey) {
        return redisLockRegistry.obtain(lockKey);
    }


}
