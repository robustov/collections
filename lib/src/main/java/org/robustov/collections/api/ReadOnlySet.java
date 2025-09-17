package org.robustov.collections.api;

import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public final class ReadOnlySet<E> extends AbstractSet<E> {
  private final Set<? extends E> delegate;

  public ReadOnlySet(Set<? extends E> delegate) {
    this.delegate = Objects.requireNonNull(delegate, "delegate must not be null");
  }

  public static <T> ReadOnlySet<T> of(Set<? extends T> set) {
    return new ReadOnlySet<>(set);
  }

  @Override
  public Iterator<E> iterator() {
    final Iterator<? extends E> it = delegate.iterator();
    return new Iterator<>() {
      public boolean hasNext() {
        return it.hasNext();
      }

      public E next() {
        return it.next();
      }

      public void remove() {
        throw new UnsupportedOperationException("Read-only set");
      }
    };
  }

  @Override
  public int size() {
    return delegate.size();
  }

  @Override
  public boolean contains(Object o) {
    return delegate.contains(o);
  }

  @Override
  public Object[] toArray() {
    return delegate.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return delegate.toArray(a);
  }

  @Override
  public boolean containsAll(java.util.Collection<?> c) {
    return delegate.containsAll(c);
  }

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  @SuppressWarnings("unchecked")
  public java.util.Spliterator<E> spliterator() {
    return (java.util.Spliterator<E>) Collections.unmodifiableSet((Set<?>) delegate).spliterator();
  }
}
