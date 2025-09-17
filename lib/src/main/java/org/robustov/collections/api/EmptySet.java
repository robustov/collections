package org.robustov.collections.api;

import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.Spliterator;

public final class EmptySet<E> extends AbstractSet<E> {
  private static final EmptySet<?> INSTANCE = new EmptySet<>();

  private EmptySet() {
  }

  @SuppressWarnings("unchecked")
  public static <T> EmptySet<T> instance() {
    return (EmptySet<T>) INSTANCE;
  }

  public static <T> java.util.Set<T> of() {
    return instance();
  }

  @Override
  public Iterator<E> iterator() {
    return Collections.emptyIterator();
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public boolean contains(Object o) {
    return false;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Spliterator<E> spliterator() {
    return (Spliterator<E>) Collections.emptySet().spliterator();
  }

  @Override
  public boolean add(E e) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(java.util.Collection<? extends E> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(java.util.Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean retainAll(java.util.Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException();
  }

}
