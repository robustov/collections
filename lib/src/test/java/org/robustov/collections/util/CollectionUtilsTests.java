package org.robustov.collections.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtilsTests {

  @Test
  void findFirstAndFindAll() {
    List<String> data = List.of("a", "bb", "ccc", "dd");
    Optional<String> f = CollectionUtils.findFirst(data, s -> s.length() > 2);
    assertTrue(f.isPresent());
    assertEquals("ccc", f.get());
    assertEquals(2, CollectionUtils.findAll(data, s -> s.length() == 2).size());
  }

  @Test
  void indexOfWorks() {
    List<Integer> l = new ArrayList<>(List.of(5, 6, 7, 6));
    assertEquals(1, CollectionUtils.indexOf(l, 6));
    assertEquals(-1, CollectionUtils.indexOf(l, 42));
  }

  @Test
  void sortInPlaceAndSortedCopyNatural() {
    List<Integer> l = new ArrayList<>(List.of(3, 1, 2));
    CollectionUtils.sortInPlace(l);
    assertEquals(List.of(1, 2, 3), l);
    List<Integer> copy = CollectionUtils.sortedCopy(List.of(9, 4, 5));
    assertEquals(List.of(4, 5, 9), copy);
  }

  @Test
  void sortWithComparator() {
    List<String> l = new ArrayList<>(List.of("b", "aa", "ccc"));
    CollectionUtils.sortInPlace(l, Comparator.comparingInt(String::length));
    assertEquals(List.of("b", "aa", "ccc"), l);
    List<String> copy = CollectionUtils.sortedCopy(l, Comparator.comparingInt(String::length).reversed());
    assertEquals(List.of("ccc", "aa", "b"), copy);
  }

  @Test
  void stableAndDuplicatesRange() {
    List<Integer> l = new ArrayList<>(List.of(2, 1, 2, 1, 3));
    CollectionUtils.sortInPlace(l);
    assertEquals(List.of(1, 1, 2, 2, 3), l);
  }
}
