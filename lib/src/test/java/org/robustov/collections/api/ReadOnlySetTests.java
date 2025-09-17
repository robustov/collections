package org.robustov.collections.api;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ReadOnlySetAdditionalTest {

  @Test
  void equalsAndHashCodeSymmetry() {
    Set<String> backing = new HashSet<>(Set.of("a", "b"));
    ReadOnlySet<String> ro = new ReadOnlySet<>(backing);
    Set<String> plain = new HashSet<>(backing);

    assertTrue(ro.equals(plain));
    assertTrue(plain.equals(ro));
    assertEquals(ro.hashCode(), plain.hashCode());
  }

  @Test
  void iteratorRemoveAlwaysThrows() {
    Set<Integer> backing = new HashSet<>(Set.of(1, 2, 3));
    ReadOnlySet<Integer> ro = ReadOnlySet.of(backing);

    Iterator<Integer> it1 = ro.iterator();
    assertThrows(UnsupportedOperationException.class, it1::remove);

    Iterator<Integer> it2 = ro.iterator();
    assertTrue(it2.hasNext());
    it2.next();
    assertThrows(UnsupportedOperationException.class, it2::remove);
  }

  @Test
  void spliteratorForEachRemainingProducesElements() {
    Set<String> backing = new HashSet<>(Set.of("x", "y"));
    ReadOnlySet<String> ro = new ReadOnlySet<>(backing);

    List<String> collected = new ArrayList<>();
    ro.spliterator().forEachRemaining(collected::add);

    assertEquals(backing, new HashSet<>(collected));
  }

  @Test
  void toArrayVariantsWork() {
    Set<String> backing = new HashSet<>(Set.of("p", "q"));
    ReadOnlySet<String> ro = new ReadOnlySet<>(backing);

    Object[] arr = ro.toArray();
    assertEquals(2, arr.length);

    String[] sarr = ro.toArray(new String[0]);
    assertEquals(2, sarr.length);
  }

  @Test
  void containsAllWorks() {
    Set<String> backing = new HashSet<>(Set.of("one", "two", "three"));
    ReadOnlySet<String> ro = new ReadOnlySet<>(backing);

    assertTrue(ro.containsAll(Set.of("one", "two")));
    assertFalse(ro.containsAll(Set.of("one", "missing")));
  }
}
