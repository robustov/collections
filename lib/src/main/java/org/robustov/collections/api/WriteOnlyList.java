package org.robustov.collections.api;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public final class WriteOnlyList<E> extends AbstractList<E> {
  private final List<? super E> delegate;

  public WriteOnlyList(List<? super E> delegate) {
    this.delegate = Objects.requireNonNull(delegate);
  }

  public static <T> WriteOnlyList<T> of(List<? super T> list) {
    return new WriteOnlyList<>(list);
  }

  @Override
  public E get(int index) {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlyList");
  }

  @Override
  public int size() {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlyList");
  }

  @Override
  public boolean add(E e) {
    return delegate.add(e);
  }

  @Override
  public void add(int index, E element) {
    delegate.add(index, element);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    return delegate.addAll(c);
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    return delegate.addAll(index, c);
  }

  @Override
  public void clear() {
    delegate.clear();
  }

  @Override
  public boolean remove(Object o) {
    return delegate.remove(o);
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
  public boolean contains(Object o) {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlyList");
  }

  @Override
  public int indexOf(Object o) {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlyList");
  }

  @Override
  public int lastIndexOf(Object o) {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlyList");
  }

  @Override
  public Object[] toArray() {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlyList");
  }

  @Override
  public <T> T[] toArray(T[] a) {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlyList");
  }

  @Override
  public java.util.Iterator<E> iterator() {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlyList");
  }

  @Override
  public java.util.ListIterator<E> listIterator(int index) {
    throw new UnsupportedOperationException("Read operations are not permitted on WriteOnlyList");
  }

  @Override
  public boolean equals(Object o) {
    throw new UnsupportedOperationException("equals is not supported for WriteOnlyList");
  }

  @Override
  public int hashCode() {
    throw new UnsupportedOperationException("hashCode is not supported for WriteOnlyList");
  }
}
