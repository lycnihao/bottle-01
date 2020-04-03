package com.couldr.squirrel.cache;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 内存缓存工具类
 *
 * @author Iksen
 * @date 2020-04-03 15:30
 */
public class LocalCache implements CacheStore<String,Object>{

  private static final ConcurrentHashMap<String, Object> CACHE_CONTAINER = new ConcurrentHashMap<String, Object>();

  /**
   * 设置缓存
   * @param key      缓存KEY
   * @param value    缓存内容
   * @param timeout  缓存时间
   */
  @Override
  public void put(String key, Object value, int timeout) {
    CACHE_CONTAINER.put(key, new CacheWrapper(timeout, value));
  }

  /**
   * 设置缓存
   * @param key   缓存KEY
   * @param value 缓存内容
   */
  @Override
  public void put(String key, Object value) {
    CACHE_CONTAINER.put(key, new CacheWrapper(value));
  }

  /**
   * 获取缓存
   * @param key 缓存KEY
   * @param <T>
   * @return
   */
  @Override
  public <T> T get(String key) {
    CacheWrapper wrapper = (CacheWrapper) CACHE_CONTAINER.get(key);
    if (wrapper == null) {
      return null;
    }

    if (wrapper.getDate() != null && wrapper.getDate().before(new Date())) {

      System.out.println("缓存KEY:["+key+"]已经过期");

      delete(key);

      return null;
    }

    return (T) wrapper.getValue();
  }

  /**
   * 获取缓存KEY列表
   *
   * @return 缓存key列表
   */
  public Set<String> getKeys() {
    return CACHE_CONTAINER.keySet();
  }

  @Override
  public void delete(String key) {
    CACHE_CONTAINER.remove(key);
  }

  @Override
  public Boolean contains(String key) {
    return CACHE_CONTAINER.containsKey(key);
  }

  private static class CacheWrapper {
    private Date date;
    private Object value;

    public CacheWrapper(int time, Object value) {
      this.date = new Date(System.currentTimeMillis() + time * 1000);
      this.value = value;
    }

    public CacheWrapper(Object value) {
      this.value = value;
    }

    public Date getDate() {
      return date;
    }

    public Object getValue() {
      return value;
    }
  }
}
