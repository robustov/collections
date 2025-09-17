package org.robustov.collections.api;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadOnlyListTests {

  @Test
  void readOperations() {
    List<String> backing = new ArrayList<>(Arrays.asList("a", "b", "c"));
    ReadOnlyList<String> ro = ReadOnlyList.of(backing);

    assertEquals(3, ro.size());
    assertEquals("a", ro.get(0));
    assertTrue(ro.contains("b"));
    assertEquals(1, ro.indexOf("b"));

    Iterator<String> it = ro.iterator();
    assertTrue(it.hasNext());
    assertEquals("a", it.next());
  }

  @Test
  void mutatingOperationsThrow() {
    List<Integer> backing = new ArrayList<>(List.of(1, 2, 3));
    ReadOnlyList<Integer> ro = new ReadOnlyList<>(backing);

    assertThrows(UnsupportedOperationException.class, () -> ro.add(4));
    assertThrows(UnsupportedOperationException.class, () -> ro.remove(Integer.valueOf(1)));
    assertThrows(UnsupportedOperationException.class, () -> ro.clear());
    assertThrows(UnsupportedOperationException.class, () -> ro.set(0, 99));

    Iterator<Integer> it = ro.iterator();
    assertThrows(UnsupportedOperationException.class, it::remove);
  }

  @Test
  void reflectsBackingListChanges() {
    List<String> backing = new ArrayList<>(List.of("x"));
    ReadOnlyList<String> ro = new ReadOnlyList<>(backing);

    assertEquals(1, ro.size());
    backing.add("y");
    assertEquals(2, ro.size());
    assertEquals("y", ro.get(1));
  }

  @Test
  void nullDelegateThrowsNpe() {
    assertThrows(NullPointerException.class, () -> new ReadOnlyList<>(null));
  }
}
