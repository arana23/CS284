package general;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

class TreapTest {

	@Test
	void test1() {
		Treap<Integer> t = new Treap<Integer>();
		assertTrue(t.add(4,18)); 
		assertTrue(t.add(2,31));
		assertTrue(t.add(6,75)); 
		assertTrue(t.add(1,83));
		assertTrue(t.add(3,12)); 
		assertTrue(t.add(5,82));
		assertTrue(t.add(7,23));
		assertTrue(t.find(3));
		assertFalse(t.find(30));
		assertTrue(t.delete(6));
		assertFalse(t.delete(73));
	}
	
	void test2() {
		Treap<Integer> t2 = new Treap<Integer>();
		assertTrue(t2.add(8,19)); 
		assertTrue(t2.add(4,52));
		assertTrue(t2.add(92,10)); 
		assertTrue(t2.add(11,36));
		assertTrue(t2.add(35,9)); 
		assertTrue(t2.add(6,88));
		assertTrue(t2.add(9,267));
		assertTrue(t2.find(19));
		assertFalse(t2.find(10));
		assertTrue(t2.delete(35));
		assertFalse(t2.delete(58));
		assertFalse(t2.find(39));
	}
	
	void test3() {
		Treap<Integer> t3 = new Treap<Integer>();
		assertTrue(t3.add(4,13)); 
		assertTrue(t3.add(9,74));
		assertTrue(t3.add(8,1)); 
		assertTrue(t3.add(19,32));
		assertTrue(t3.add(8,76)); 
		assertTrue(t3.add(43,48));
		assertTrue(t3.add(14,43));
		assertTrue(t3.find(43));
		assertFalse(t3.find(13));
		assertTrue(t3.delete(19));
		assertFalse(t3.delete(78));
		assertFalse(t3.find(12));
	}
	

}
