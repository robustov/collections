package org.robustov.collections.api;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public final class ReadOnlyList<E> extends AbstractList<E> {
  private final List<? extends E> delegate;

  public ReadOnlyList(List<? extends E> delegate) {
    this.delegate = Objects.requireNonNull(delegate, "delegate must not be null");
  }

  public static <T> ReadOnlyList<T> of(List<? extends T> list) {
    return new ReadOnlyList<>(list);
  }

  @Override
  public E get(int index) {
    return delegate.get(index);
  }

  @Override
  public int size() {
    return delegate.size();
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
        throw new UnsupportedOperationException();
      }
    };
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    final ListIterator<? extends E> it = delegate.listIterator(index);
    return new ListIterator<>() {
      public boolean hasNext() {
        return it.hasNext();
      }

      public E next() {
        return it.next();
      }

      public boolean hasPrevious() {
        return it.hasPrevious();
      }

      public E previous() {
        return it.previous();
      }

      public int nextIndex() {
        return it.nextIndex();
      }

      public int previousIndex() {
        return it.previousIndex();
      }

      public void remove() {
        throw new UnsupportedOperationException();
      }

      public void set(E e) {
        throw new UnsupportedOperationException();
      }

      public void add(E e) {
        throw new UnsupportedOperationException();
      }
    };
  }

  @Override
  public boolean contains(Object o) {
    return delegate.contains(o);
  }

  @Override
  public int indexOf(Object o) {
    return delegate.indexOf(o);
  }

  @Override
  public int lastIndexOf(Object o) {
    return delegate.lastIndexOf(o);
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
  public boolean equals(Object o) {
    return delegate.equals(o);
  }

  @Override
  public int hashCode() {
    return delegate.hashCode();
  }
}
