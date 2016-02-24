package org.andresoviedo.tests.algorithm;

import java.util.HashSet;

import org.andresoviedo.tests.algorithm.Permutation;
import org.junit.Test;

public class PermutationTest {

	@Test
	public void testGetAllPermutations() {
		HashSet<Integer> data = new HashSet<Integer>();
		data.add(1);
		data.add(2);
		data.add(3);
		data.add(4);

		Permutation p = new Permutation(data);
		p.getAllPermutations(4);
	}

	@Test
	public void testGetAllPermutations2() {
		HashSet<Integer> data = new HashSet<Integer>();
		data.add(1);
		data.add(2);
		data.add(3);
		data.add(4);

		Permutation p = new Permutation(data);
		p.getAllPermutations(3);
	}
}
