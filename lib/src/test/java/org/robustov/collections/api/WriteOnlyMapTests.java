package org.robustov.collections.api;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WriteOnlyMapTests {

  @Test
  void writeOperationsAffectBackingMap() {
    Map<String, Integer> backing = new HashMap<>();
    WriteOnlyMap<String, Integer> w = WriteOnlyMap.of(backing);

    assertNull(w.put("a", 1));
    assertEquals(Integer.valueOf(1), backing.get("a"));

    w.putAll(Map.of("b", 2, "c", 3));
    assertEquals(3, backing.size());

    assertEquals(Integer.valueOf(2), w.remove("b"));
    assertFalse(backing.containsKey("b"));

    w.clear();
    assertTrue(backing.isEmpty());
  }

  @Test
  void readOperationsThrow() {
    Map<String, String> backing = new HashMap<>();
    WriteOnlyMap<String, String> w = new WriteOnlyMap<>(backing);

    assertThrows(UnsupportedOperationException.class, w::size);
    assertThrows(UnsupportedOperationException.class, () -> w.get("x"));
    assertThrows(UnsupportedOperationException.class, () -> w.entrySet());
    assertThrows(UnsupportedOperationException.class, () -> w.keySet());
  }

  @Test
  void nullDelegateThrowsNpe() {
    assertThrows(NullPointerException.class, () -> new WriteOnlyMap<>(null));
  }
}
