package org.robustov.collections.api;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public final class SingletonMap<K, V> extends AbstractMap<K, V> {
  private final K key;
  private final V value;
  private transient Set<Entry<K, V>> entrySet;

  public SingletonMap(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public static <K, V> SingletonMap<K, V> of(K key, V value) {
    return new SingletonMap<>(key, value);
  }

  @Override
  public int size() {
    return 1;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean containsKey(Object k) {
    return Objects.equals(k, key);
  }

  @Override
  public boolean containsValue(Object v) {
    return Objects.equals(v, value);
  }

  @Override
  public V get(Object k) {
    return Objects.equals(k, key) ? value : null;
  }

  @Override
  public Set<K> keySet() {
    return Collections.singleton(key);
  }

  @Override
  public java.util.Collection<V> values() {
    return Collections.singleton(value);
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    Set<Entry<K, V>> es = entrySet;
    if (es == null) {
      es = Collections.unmodifiableSet(new java.util.AbstractSet<>() {
        @Override
        public Iterator<Entry<K, V>> iterator() {
          return new Iterator<>() {
            private boolean used = false;

            @Override
            public boolean hasNext() {
              return !used;
            }

            @Override
            public Entry<K, V> next() {
              if (used)
                throw new java.util.NoSuchElementException();
              used = true;
              return new UnmodifiableEntry();
            }

            @Override
            public void remove() {
              throw new UnsupportedOperationException();
            }
          };
        }

        @Override
        public int size() {
          return 1;
        }

        @Override
        public boolean contains(Object o) {
          return SingletonMap.this.entrySetInternal().contains(o);
        }
      });
      entrySet = es;
    }
    return es;
  }

  private Set<Entry<K, V>> entrySetInternal() {
    return Collections.singleton(new UnmodifiableEntry());
  }

  private final class UnmodifiableEntry implements Entry<K, V> {
    @Override
    public K getKey() {
      return key;
    }

    @Override
    public V getValue() {
      return value;
    }

    @Override
    public V setValue(V v) {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
      return entryEquals(this, o);
    }

    @Override
    public int hashCode() {
      return entryHash(this);
    }
  }

  private static <K, V> boolean entryEquals(Entry<K, V> e1, Object o) {
    if (e1 == o)
      return true;
    if (!(o instanceof Entry))
      return false;
    Entry<?, ?> e2 = (Entry<?, ?>) o;
    return Objects.equals(e1.getKey(), e2.getKey()) && Objects.equals(e1.getValue(), e2.getValue());
  }

  private static <K, V> int entryHash(Entry<K, V> e) {
    return Objects.hashCode(e.getKey()) ^ Objects.hashCode(e.getValue());
  }

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
