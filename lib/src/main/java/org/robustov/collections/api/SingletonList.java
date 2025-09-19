package org.robustov.collections.api;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.List;

public final class SingletonList<E> extends AbstractList<E> implements RandomAccess {
  private final E element;

  public SingletonList(E element) {
    this.element = element;
  }

  public static <T> SingletonList<T> of(T element) {
    return new SingletonList<>(element);
  }

  @Override
  public E get(int index) {
    if (index != 0)
      throw new IndexOutOfBoundsException("Index: " + index);
    return element;
  }

  @Override
  public int size() {
    return 1;
  }

  @Override
  public boolean contains(Object o) {
    return element == null ? o == null : element.equals(o);
  }

  @Override
  public int indexOf(Object o) {
    return contains(o) ? 0 : -1;
  }

  @Override
  public int lastIndexOf(Object o) {
    return indexOf(o);
  }

  @Override
  public Object[] toArray() {
    return new Object[] { element };
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T[] toArray(T[] a) {
    if (a.length == 0) {
      T[] arr = (T[]) Array.newInstance(a.getClass().getComponentType(), 1);
      arr[0] = (T) element;
      return arr;
    }
    a[0] = (T) element;
    if (a.length > 1)
      a[1] = null;
    return a;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<>() {
      private boolean used = false;

      @Override
      public boolean hasNext() {
        return !used;
      }

      @Override
      public E next() {
        if (used)
          throw new NoSuchElementException();
        used = true;
        return element;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    if (index < 0 || index > 1)
      throw new IndexOutOfBoundsException("Index: " + index);
    return new ListIterator<>() {
      private int cursor = index; // 0 or 1

      @Override
      public boolean hasNext() {
        return cursor == 0;
      }

      @Override
      public E next() {
        if (cursor != 0)
          throw new NoSuchElementException();
        cursor = 1;
        return element;
      }

      @Override
      public boolean hasPrevious() {
        return cursor == 1;
      }

      @Override
      public E previous() {
        if (cursor != 1)
          throw new NoSuchElementException();
        cursor = 0;
        return element;
      }

      @Override
      public int nextIndex() {
        return cursor;
      }

      @Override
      public int previousIndex() {
        return cursor - 1;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }

      @Override
      public void set(E e) {
        throw new UnsupportedOperationException();
      }

      @Override
      public void add(E e) {
        throw new UnsupportedOperationException();
      }
    };
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    if (fromIndex == 0 && toIndex == 1)
      return this;
    if (fromIndex == toIndex)
      return EmptyList.of();
    throw new IndexOutOfBoundsException("fromIndex=" + fromIndex + " toIndex=" + toIndex);
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
