package org.robustov.collections.api;

import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

public final class EmptyList<E> extends AbstractList<E> {
  private static final EmptyList<?> INSTANCE = new EmptyList<>();

  private EmptyList() {
  }

  @SuppressWarnings("unchecked")
  public static <T> EmptyList<T> instance() {
    return (EmptyList<T>) INSTANCE;
  }

  public static <T> java.util.List<T> of() {
    return instance();
  }

  @Override
  public E get(int index) {
    throw new IndexOutOfBoundsException("Index: " + index);
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public Iterator<E> iterator() {
    return Collections.emptyIterator();
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    return Collections.emptyListIterator();
  }

  @Override
  public boolean add(E e) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void add(int index, E element) {
    throw new UnsupportedOperationException();
  }

  @Override
  public E remove(int index) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException();
  }

}
