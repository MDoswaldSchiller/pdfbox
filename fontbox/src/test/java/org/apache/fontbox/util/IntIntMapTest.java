/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.apache.fontbox.util;

import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mdo
 */
public class IntIntMapTest
{
  private IntIntMap defaultMap;

  @Before
  public void setUp()
  {
    defaultMap = new IntIntMap(3);
    defaultMap.put(5, 8);
    defaultMap.put(7, 11);
    defaultMap.put(3, 17);
  }

  @Test
  public void testSize()
  {
    IntIntMap sizeMap = new IntIntMap(2);
    Assert.assertEquals("Initial size must be 0", 0, sizeMap.size());

    sizeMap.put(5, 10);
    sizeMap.put(2, 11);
    Assert.assertEquals("Size after 2 inserts",2, sizeMap.size());

    sizeMap.put(5, 7);
    Assert.assertEquals("Size after 2 inserts, 1 overwrite", 2, sizeMap.size());

    //make sure the internal structure has to be resized
    sizeMap.put(11, 7);
    sizeMap.put(12, 7);
    sizeMap.put(13, 7);
    Assert.assertEquals("Size after 5 inserts, 1 overwrite", 5, sizeMap.size());
  }

  @Test
  public void testPutAndGet()
  {
    IntIntMap putMap = new IntIntMap(8);
    Assert.assertEquals("Get of inexisting key must return Integer.MIN_VALUE", IntIntMap.NO_VALUE, putMap.get(5));

    putMap.put(5, 10);
    Assert.assertEquals("Get of value for key '5'", 10, putMap.get(5));

    putMap.put(6, 12);
    putMap.put(2, 19);
    Assert.assertEquals("Get of value for key '6'", 12, putMap.get(6));

    putMap.put(6, 999);
    Assert.assertEquals("Get of value for key '6'", 999, putMap.get(6));
  }

  @Test
  public void entryIterator_returns_all_elements_in_order()
  {
    IntIntMap.EntryIterator iterator = defaultMap.entryIterator();

    iterator.next();
    Assert.assertEquals(3, iterator.getKey());
    Assert.assertEquals(17, iterator.getValue());
    iterator.next();
    Assert.assertEquals(5, iterator.getKey());
    Assert.assertEquals(8, iterator.getValue());
    iterator.next();
    Assert.assertEquals(7, iterator.getKey());
    Assert.assertEquals(11, iterator.getValue());
  }

  @Test
  public void entryIterator_hasNext_only_returns_false_on_last_entry()
  {
    IntIntMap.EntryIterator iterator = defaultMap.entryIterator();

    Assert.assertTrue(iterator.hasNext());
    iterator.next();
    Assert.assertTrue(iterator.hasNext());
    iterator.next();
    Assert.assertTrue(iterator.hasNext());
    iterator.next();
    Assert.assertFalse(iterator.hasNext());
  }

  @Test(expected = NoSuchElementException.class)
  public void entryIterator_throws_if_next_is_called_on_last_entry()
  {
    IntIntMap.EntryIterator iterator = defaultMap.entryIterator();
    iterator.next();
    iterator.next();
    iterator.next();

    //throw
    iterator.next();
  }
  
  
  @Test
  public void empty_map_does_not_contain_any_key()
  {
    IntIntMap containsMap = new IntIntMap(8);
    Assert.assertFalse("Key '5' must not exist", containsMap.containsKey(5));
  }
  
  @Test
  public void contains_returns_true_if_key_is_in_map()
  {
    Assert.assertTrue("Key '5' must exist", defaultMap.containsKey(5));
  }
  
  @Test
  public void contains_returns_false_if_key_is_not_in_map()
  {
    Assert.assertFalse("Key '4' must not exist", defaultMap.containsKey(4));
  }
}
