package org.robustov.collections.api;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SingletonSetTest {

  @Test
  void basics() {
    SingletonSet<String> s = SingletonSet.of("x");
    assertEquals(1, s.size());
    assertTrue(s.contains("x"));
    assertArrayEquals(new Object[] { "x" }, s.toArray());
    assertEquals(Set.of("x"), s);
  }

  @Test
  void iteratorBehavior() {
    SingletonSet<Integer> s = SingletonSet.of(7);
    Iterator<Integer> it = s.iterator();
    assertTrue(it.hasNext());
    assertEquals(7, it.next());
    assertFalse(it.hasNext());
    assertThrows(UnsupportedOperationException.class, it::remove);
  }

  @Test
  void spliteratorForEachRemaining() {
    SingletonSet<String> s = SingletonSet.of("a");
    List<String> collected = new ArrayList<>();
    s.spliterator().forEachRemaining(collected::add);
    assertEquals(List.of("a"), collected);
  }

  @Test
  void nullElement() {
    SingletonSet<String> s = SingletonSet.of(null);
    assertTrue(s.contains(null));
    assertEquals(1, s.size());
  }
}
