package org.robustov.collections.api;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmptySetTests {
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
}
