package org.robustov.collections.api;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SingletonMapTests {

  @Test
  void basics() {
    SingletonMap<String, Integer> m = SingletonMap.of("k", 42);
    assertEquals(1, m.size());
    assertFalse(m.isEmpty());
    assertTrue(m.containsKey("k"));
    assertTrue(m.containsValue(42));
    assertEquals(Integer.valueOf(42), m.get("k"));
    Set<String> keys = m.keySet();
    assertEquals(1, keys.size());
    assertTrue(keys.contains("k"));
    assertEquals(1, m.values().size());
    assertTrue(m.values().contains(42));
    assertEquals(Map.of("k", 42), m);
  }

  @Test
  void entrySetAndIterator() {
    SingletonMap<String, String> m = SingletonMap.of("a", "b");
    Set<Map.Entry<String, String>> es = m.entrySet();
    assertEquals(1, es.size());
    Iterator<Map.Entry<String, String>> it = es.iterator();
    assertTrue(it.hasNext());
    Map.Entry<String, String> e = it.next();
    assertEquals("a", e.getKey());
    assertEquals("b", e.getValue());
    assertThrows(UnsupportedOperationException.class, () -> e.setValue("x"));
    assertThrows(UnsupportedOperationException.class, it::remove);
  }

  @Test
  void equalsAndHashCode() {
    SingletonMap<String, Integer> m = SingletonMap.of("k", 1);
    assertEquals(Map.of("k", 1), m);
    assertEquals(m.hashCode(), Map.of("k", 1).hashCode());
  }

  @Test
  void nullKeyAndValue() {
    SingletonMap<String, String> m = SingletonMap.of(null, null);
    assertTrue(m.containsKey(null));
    assertTrue(m.containsValue(null));
    assertNull(m.get(null));
  }
}
