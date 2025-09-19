package org.robustov.collections.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.Collections;

public final class CollectionUtils {
  private CollectionUtils() {
  }

  public static <T> Optional<T> findFirst(Collection<? extends T> c, Predicate<? super T> pred) {
    Objects.requireNonNull(c);
    Objects.requireNonNull(pred);
    for (T t : c)
      if (pred.test(t))
        return Optional.of(t);
    return Optional.empty();
  }

  public static <T> List<T> findAll(Collection<? extends T> c, Predicate<? super T> pred) {
    Objects.requireNonNull(c);
    Objects.requireNonNull(pred);
    List<T> out = new ArrayList<>();
    for (T t : c)
      if (pred.test(t))
        out.add(t);
    return out;
  }

  public static <T> int indexOf(List<? extends T> list, T element) {
    Objects.requireNonNull(list);
    for (int i = 0; i < list.size(); i++)
      if (Objects.equals(list.get(i), element))
        return i;
    return -1;
  }

  public static <T> List<T> sortedCopy(Collection<? extends T> c, Comparator<? super T> cmp) {
    Objects.requireNonNull(c);
    List<T> out = new ArrayList<>(c);
    sortInPlace(out, cmp);
    return out;
  }

  public static <T extends Comparable<? super T>> List<T> sortedCopy(Collection<? extends T> c) {
    Objects.requireNonNull(c);
    List<T> out = new ArrayList<>(c);
    sortInPlace(out);
    return out;
  }

  public static <T> void sortInPlace(List<T> list, Comparator<? super T> cmp) {
    Objects.requireNonNull(list);
    int n = list.size();
    if (n <= 1)
      return;
    Comparator<? super T> comparator;
    if (cmp != null) {
      comparator = cmp;
    } else {
      @SuppressWarnings("unchecked")
      Comparator<? super T> natural = (a, b) -> ((Comparable<T>) a).compareTo(b);
      comparator = natural;
    }
    List<T> buf = new ArrayList<>(Collections.nCopies(n, null));
    mergeSortList(list, buf, 0, n, comparator);
  }

  public static <T extends Comparable<? super T>> void sortInPlace(List<T> list) {
    Objects.requireNonNull(list);
    sortInPlace(list, Comparator.naturalOrder());
  }

  private static <T> void mergeSortList(List<T> a, List<T> buf, int from, int to, Comparator<? super T> cmp) {
    if (to - from <= 1)
      return;
    int mid = (from + to) >>> 1;
    mergeSortList(a, buf, from, mid, cmp);
    mergeSortList(a, buf, mid, to, cmp);
    int i = from, j = mid, k = from;
    while (i < mid || j < to) {
      if (i < mid && (j >= to || cmp.compare(a.get(i), a.get(j)) <= 0)) {
        buf.set(k++, a.get(i++));
      } else {
        buf.set(k++, a.get(j++));
      }
    }
    for (k = from; k < to; k++)
      a.set(k, buf.get(k));
  }
}
