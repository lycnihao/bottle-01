package com.couldr.squirrel.cache;

/**
 * 缓存存储接口。
 *
 * @param <K>
 * @param <V>
 * @author iksen
 * @date 2020-04-03 15:24
 */
public interface CacheStore<K,V> {

  /**
   * 设置缓存
   * @param key      缓存KEY
   * @param value    缓存内容
   * @param timeout  缓存时间
   */
  void put(K key,V value, int timeout);


  /**
   * 设置缓存
   * @param key   缓存KEY
   * @param value 缓存内容
   */
  void put(K key, V value);

  /**
   * 获取缓存
   * @param key 缓存KEY
   * @return 缓存内容
   */
  <T> T get(K key);

  /**
   * 删除缓存
   *
   * @param key 缓存KEY
   */
  void delete(K key);

  /**
   * 判断缓存是否存在
   * @param key 缓存KEY
   * @return 缓存内容
   */
  Boolean contains(String key);

}
