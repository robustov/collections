package org.robustov.collections.api;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmptyCollectionsTests {

  @Test
  void emptyListBasics() {
    var list = EmptyList.of();
    assertTrue(list.isEmpty());
    assertEquals(0, list.size());
    Iterator<Object> it = (Iterator<Object>) list.iterator();
    assertFalse(it.hasNext());
    assertEquals(Collections.emptyList(), list);
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    assertThrows(UnsupportedOperationException.class, () -> list.add(null));
    assertThrows(UnsupportedOperationException.class, () -> list.remove(0));
  }

  @Test
  void emptySetBasics() {
    Set<Object> s = EmptySet.of();
    assertTrue(s.isEmpty());
    assertEquals(0, s.size());
    Iterator<Object> it = (Iterator<Object>) s.iterator();
    assertFalse(it.hasNext());
    assertEquals(Collections.emptySet(), s);
    assertThrows(UnsupportedOperationException.class, () -> s.add(null));
    assertThrows(UnsupportedOperationException.class, () -> s.remove(null));
    assertEquals(Collections.emptySet().spliterator().estimateSize(), s.spliterator().estimateSize());
  }

  @Test
  void emptyMapBasics() {
    Map<Object, Object> m = EmptyMap.of();
    assertTrue(m.isEmpty());
    assertEquals(0, m.size());
    assertNull(m.get("x"));
    assertEquals(Collections.emptyMap(), m);
    assertThrows(UnsupportedOperationException.class, () -> m.put("a", 1));
    assertThrows(UnsupportedOperationException.class, () -> m.remove("a"));
  }
}
