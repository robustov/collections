package org.robustov.collections.api;

import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.NoSuchElementException;

public final class SingletonSet<E> extends AbstractSet<E> {
  private final E element;

  public SingletonSet(E element) {
    this.element = element;
  }

  public static <T> SingletonSet<T> of(T element) {
    return new SingletonSet<>(element);
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
  public int size() {
    return 1;
  }

  @Override
  public boolean contains(Object o) {
    return Objects.equals(o, element);
  }

  @Override
  public Object[] toArray() {
    return new Object[] { element };
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T[] toArray(T[] a) {
    if (a.length == 0) {
      T[] arr = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), 1);
      arr[0] = (T) element;
      return arr;
    }
    a[0] = (T) element;
    if (a.length > 1)
      a[1] = null;
    return a;
  }

  @Override
  public Spliterator<E> spliterator() {
    return (Spliterator<E>) Collections.singleton(element).spliterator();
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
