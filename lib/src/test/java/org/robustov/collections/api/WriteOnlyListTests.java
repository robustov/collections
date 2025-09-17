package org.robustov.collections.api;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WriteOnlyListTests {

  @Test
  void writeOperationsAffectBackingList() {
    List<String> backing = new ArrayList<>();
    WriteOnlyList<String> w = WriteOnlyList.of(backing);

    assertTrue(w.add("a"));
    w.add(0, "b");
    assertTrue(w.addAll(List.of("c", "d")));
    assertEquals(4, backing.size());
    assertEquals(List.of("b", "a", "c", "d"), backing);

    assertTrue(w.remove("a"));
    assertFalse(backing.contains("a"));

    w.clear();
    assertTrue(backing.isEmpty());
  }

  @Test
  void readOperationsThrow() {
    List<Integer> backing = new ArrayList<>();
    WriteOnlyList<Integer> w = new WriteOnlyList<>(backing);

    assertThrows(UnsupportedOperationException.class, () -> w.get(0));
    assertThrows(UnsupportedOperationException.class, w::size);
    assertThrows(UnsupportedOperationException.class, () -> w.iterator());
    assertThrows(UnsupportedOperationException.class, () -> w.contains(1));
    assertThrows(UnsupportedOperationException.class, () -> w.toArray());
  }

  @Test
  void removeByObjectReturnsCorrectly() {
    List<String> backing = new ArrayList<>(List.of("x", "y"));
    WriteOnlyList<String> w = new WriteOnlyList<>(backing);

    assertTrue(w.remove("x"));
    assertFalse(w.remove("missing"));
  }

  @Test
  void equalsAndHashCodeThrow() {
    List<String> backing = new ArrayList<>();
    WriteOnlyList<String> w = new WriteOnlyList<>(backing);

    assertThrows(UnsupportedOperationException.class, () -> w.equals(new ArrayList<>()));
    assertThrows(UnsupportedOperationException.class, w::hashCode);
  }
}
