package org.robustov.collections.api;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ReadOnlyMapTests {

  @Test
  void readOperations() {
    Map<String, Integer> backing = new HashMap<>();
    backing.put("a", 1);
    backing.put("b", 2);
    ReadOnlyMap<String, Integer> ro = ReadOnlyMap.of(backing);

    assertEquals(2, ro.size());
    assertTrue(ro.containsKey("a"));
    assertTrue(ro.containsValue(2));
    assertEquals(Integer.valueOf(1), ro.get("a"));

    Set<String> keys = ro.keySet();
    assertTrue(keys.contains("b"));

    assertEquals(2, ro.entrySet().size());
    Iterator<Map.Entry<String, Integer>> it = ro.entrySet().iterator();
    assertTrue(it.hasNext());
  }

  @Test
  void mutatingOperationsThrow() {
    Map<String, Integer> backing = new HashMap<>();
    backing.put("x", 10);
    ReadOnlyMap<String, Integer> ro = new ReadOnlyMap<>(backing);

    assertThrows(UnsupportedOperationException.class, () -> ro.put("y", 20));
    assertThrows(UnsupportedOperationException.class, () -> ro.remove("x"));
    assertThrows(UnsupportedOperationException.class, () -> ro.clear());

    Iterator<Map.Entry<String, Integer>> it = ro.entrySet().iterator();
    assertThrows(UnsupportedOperationException.class, it::remove);

    Map.Entry<String, Integer> e = ro.entrySet().iterator().next();
    assertThrows(UnsupportedOperationException.class, () -> e.setValue(99));
  }

  @Test
  void reflectsBackingChanges() {
    Map<String, String> backing = new HashMap<>();
    backing.put("p", "q");
    ReadOnlyMap<String, String> ro = new ReadOnlyMap<>(backing);

    assertEquals(1, ro.size());
    backing.put("r", "s");
    assertEquals(2, ro.size());
    assertEquals("s", ro.get("r"));
  }

  @Test
  void nullDelegateThrowsNpe() {
    assertThrows(NullPointerException.class, () -> new ReadOnlyMap<>(null));
  }
}
