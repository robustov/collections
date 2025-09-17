package org.robustov.collections.api;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class ReadOnlyMap<K, V> extends AbstractMap<K, V> {
  private final Map<? extends K, ? extends V> delegate;
  private transient Set<Entry<K, V>> entrySet;

  public ReadOnlyMap(Map<? extends K, ? extends V> delegate) {
    this.delegate = Objects.requireNonNull(delegate, "delegate must not be null");
  }

  public static <K, V> ReadOnlyMap<K, V> of(Map<? extends K, ? extends V> map) {
    return new ReadOnlyMap<>(map);
  }

  @Override
  public int size() {
    return delegate.size();
  }

  @Override
  public boolean isEmpty() {
    return delegate.isEmpty();
  }

  @Override
  public boolean containsKey(Object key) {
    return delegate.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value) {
    return delegate.containsValue(value);
  }

  @Override
  public V get(Object key) {
    return delegate.get(key);
  }

  @Override
  public Set<K> keySet() {
    return Collections.unmodifiableSet(delegate.keySet());
  }

  @Override
  public java.util.Collection<V> values() {
    return Collections.unmodifiableCollection(delegate.values());
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    Set<Entry<K, V>> es = entrySet;
    if (es == null) {
      es = Collections.unmodifiableSet(new AbstractSet<>() {
        @Override
        public Iterator<Entry<K, V>> iterator() {
          final Iterator<? extends Entry<? extends K, ? extends V>> it = delegate.entrySet().iterator();
          return new Iterator<>() {
            public boolean hasNext() {
              return it.hasNext();
            }

            public Entry<K, V> next() {
              Entry<? extends K, ? extends V> e = it.next();
              return new UnmodifiableEntry<>(e);
            }

            public void remove() {
              throw new UnsupportedOperationException();
            }
          };
        }

        @Override
        public int size() {
          return delegate.size();
        }

        @Override
        public boolean contains(Object o) {
          return delegate.entrySet().contains(o);
        }
      });
      entrySet = es;
    }
    return es;
  }

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  private static final class UnmodifiableEntry<K, V> implements Entry<K, V> {
    private final Entry<? extends K, ? extends V> delegateEntry;

    UnmodifiableEntry(Entry<? extends K, ? extends V> delegateEntry) {
      this.delegateEntry = delegateEntry;
    }

    public K getKey() {
      return delegateEntry.getKey();
    }

    public V getValue() {
      return delegateEntry.getValue();
    }

    public V setValue(V value) {
      throw new UnsupportedOperationException("Read-only map");
    }

    public boolean equals(Object o) {
      return delegateEntry.equals(o);
    }

    public int hashCode() {
      return delegateEntry.hashCode();
    }
  }
}
