package org.robustov.collections.api;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class EmptyListTests {

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
}
