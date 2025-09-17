package org.robustov.collections.api;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WriteOnlySetTests {

  @Test
  void writeOperationsAffectBackingSet() {
    Set<String> backing = new HashSet<>();
    WriteOnlySet<String> w = WriteOnlySet.of(backing);

    assertTrue(w.add("a"));
    assertTrue(w.addAll(Set.of("b", "c")));
    assertTrue(backing.contains("b"));

    assertTrue(w.remove("a"));
    assertFalse(backing.contains("a"));

    w.clear();
    assertTrue(backing.isEmpty());
  }

  @Test
  void readOperationsThrow() {
    Set<Integer> backing = new HashSet<>(Set.of(1, 2));
    WriteOnlySet<Integer> w = new WriteOnlySet<>(backing);

    assertThrows(UnsupportedOperationException.class, w::size);
    assertThrows(UnsupportedOperationException.class, () -> w.iterator());
    assertThrows(UnsupportedOperationException.class, () -> w.contains(1));
    assertThrows(UnsupportedOperationException.class, () -> w.toArray());
  }

  @Test
  void nullDelegateThrowsNpe() {
    assertThrows(NullPointerException.class, () -> new WriteOnlySet<>(null));
  }
}
