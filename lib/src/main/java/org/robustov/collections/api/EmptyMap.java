package org.robustov.collections.api;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public final class EmptyMap<K, V> extends AbstractMap<K, V> {
  private static final EmptyMap<?, ?> INSTANCE = new EmptyMap<>();

  private EmptyMap() {
  }

  @SuppressWarnings("unchecked")
  public static <K, V> EmptyMap<K, V> instance() {
    return (EmptyMap<K, V>) INSTANCE;
  }

  public static <K, V> Map<K, V> of() {
    return instance();
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    return Collections.emptySet();
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public boolean containsKey(Object key) {
    return false;
  }

  @Override
  public boolean containsValue(Object value) {
    return false;
  }

  @Override
  public V get(Object key) {
    return null;
  }

  @Override
  public Set<K> keySet() {
    return Collections.emptySet();
  }

  @Override
  public Collection<V> values() {
    return Collections.emptySet();
  }

  @Override
  public V put(K key, V value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public V remove(Object key) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void putAll(java.util.Map<? extends K, ? extends V> m) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException();
  }

}
