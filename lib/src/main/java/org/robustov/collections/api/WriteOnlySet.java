package org.robustov.collections.api;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public final class WriteOnlySet<E> extends AbstractSet<E> {
  private final Set<? super E> delegate;

  public WriteOnlySet(Set<? super E> delegate) {
    this.delegate = Objects.requireNonNull(delegate);
  }

  public static <T> WriteOnlySet<T> of(Set<? super T> set) {
    return new WriteOnlySet<>(set);
  }

  @Override
  public Iterator<E> iterator() {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlySet");
  }

  @Override
  public int size() {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlySet");
  }

  @Override
  public boolean contains(Object o) {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlySet");
  }

  @Override
  public boolean add(E e) {
    return delegate.add(e);
  }

  @Override
  public boolean remove(Object o) {
    return delegate.remove(o);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    return delegate.addAll(c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return delegate.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return delegate.retainAll(c);
  }

  @Override
  public void clear() {
    delegate.clear();
  }

  @Override
  public Object[] toArray() {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlySet");
  }

  @Override
  public <T> T[] toArray(T[] a) {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlySet");
  }

  @Override
  public boolean equals(Object o) {
    throw new UnsupportedOperationException("equals is not supported for WriteOnlySet");
  }

  @Override
  public int hashCode() {
    throw new UnsupportedOperationException("hashCode is not supported for WriteOnlySet");
  }
}
