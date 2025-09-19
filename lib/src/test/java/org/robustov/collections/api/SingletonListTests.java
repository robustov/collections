package org.robustov.collections.api;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class SingletonListTests {

  @Test
  void basics() {
    SingletonList<String> s = SingletonList.of("x");
    assertEquals(1, s.size());
    assertEquals("x", s.get(0));
    assertTrue(s.contains("x"));
    assertEquals(0, s.indexOf("x"));
    assertEquals(0, s.lastIndexOf("x"));
    assertArrayEquals(new Object[] { "x" }, s.toArray());
    assertArrayEquals(new String[] { "x" }, s.toArray(new String[0]));
  }

  @Test
  void iteratorBehavior() {
    SingletonList<Integer> s = SingletonList.of(5);
    Iterator<Integer> it = s.iterator();
    assertTrue(it.hasNext());
    assertEquals(5, it.next());
    assertFalse(it.hasNext());
    assertThrows(UnsupportedOperationException.class, it::remove);
  }

  @Test
  void listIteratorBehavior() {
    SingletonList<String> s = SingletonList.of("a");
    var it = s.listIterator(0);
    assertTrue(it.hasNext());
    assertEquals("a", it.next());
    assertTrue(it.hasPrevious());
    assertEquals("a", it.previous());
    assertThrows(UnsupportedOperationException.class, () -> it.add("b"));
  }

  @Test
  void subListBehavior() {
    SingletonList<String> s = SingletonList.of("z");
    assertSame(s, s.subList(0, 1));
    assertEquals(0, s.subList(0, 0).size());
    assertEquals(0, s.subList(1, 1).size());
    assertThrows(IndexOutOfBoundsException.class, () -> s.subList(0, 2));
  }

  @Test
  void mutatingOperationsThrow() {
    SingletonList<String> s = SingletonList.of("k");
    assertThrows(UnsupportedOperationException.class, () -> s.add("x"));
    assertThrows(UnsupportedOperationException.class, () -> s.remove("k"));
    assertThrows(UnsupportedOperationException.class, () -> s.clear());
  }
}
