package org.robustov.collections.api;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public final class WriteOnlyMap<K, V> extends AbstractMap<K, V> {
  private final Map<? super K, ? super V> delegate;

  public WriteOnlyMap(Map<? super K, ? super V> delegate) {
    this.delegate = Objects.requireNonNull(delegate);
  }

  public static <K, V> WriteOnlyMap<K, V> of(Map<? super K, ? super V> map) {
    return new WriteOnlyMap<>(map);
  }

  @Override
  @SuppressWarnings("unchecked")
  public V put(K key, V value) {
    return (V) delegate.put(key, value);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void putAll(Map<? extends K, ? extends V> m) {
    ((Map<K, V>) delegate).putAll(m);
  }

  @Override
  @SuppressWarnings("unchecked")
  public V remove(Object key) {
    return (V) delegate.remove(key);
  }

  @Override
  public void clear() {
    delegate.clear();
  }

  @Override
  public int size() {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlyMap");
  }

  @Override
  public boolean isEmpty() {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlyMap");
  }

  @Override
  public boolean containsKey(Object key) {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlyMap");
  }

  @Override
  public boolean containsValue(Object value) {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlyMap");
  }

  @Override
  public V get(Object key) {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlyMap");
  }

  @Override
  public java.util.Set<Entry<K, V>> entrySet() {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlyMap");
  }

  @Override
  public java.util.Set<K> keySet() {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlyMap");
  }

  @Override
  public Collection<V> values() {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlyMap");
  }

  @Override
  public boolean equals(Object o) {
    throw new UnsupportedOperationException("equals is not supported for WriteOnlyMap");
  }

  @Override
  public int hashCode() {
    throw new UnsupportedOperationException("hashCode is not supported for WriteOnlyMap");
  }
}
