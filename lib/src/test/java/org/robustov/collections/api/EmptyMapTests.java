
package org.robustov.collections.api;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmptyMapTests {
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
