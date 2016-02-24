package org.andresoviedo.tests.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public final class Permutation {

	private final List<Integer> data;

	public Permutation(Set<Integer> data) {
		super();
		if (data == null || data.isEmpty()) {
			throw new IllegalArgumentException("data is null or empty");
		}
		this.data = new ArrayList<Integer>(data);
	}

	public List<Integer[]> getAllPermutations(int m) {
		if (m <= 0) {
			throw new IllegalArgumentException("illegal size. <=0");
		}
		if (m > data.size()) {
			throw new IllegalArgumentException("illegal size. m > datasize");
		}
		List<Integer[]> ret = new ArrayList<Integer[]>();
		addPermutations(ret, new ArrayList<Integer>(data), null, m, 0,
				new Integer[m]);
		return ret;
	}

	private void addPermutations(List<Integer[]> permutations,
			List<Integer> rightList, Integer hold, int m, int pos,
			Integer[] currentSample) {

		// System.out.println(rightList + " : " + pos + " : " + currentSample);

		if (pos >= m) {
			return;
		}

		if (pos == m - 1) {
			for (Integer el : rightList) {
				currentSample[pos] = el;
//				permutations.add(currentSample);
				System.out.println(Arrays.toString(currentSample));
			}
			return;
		}

		for (Integer el : rightList) {
			hold = el;
			currentSample[pos] = el;
			permutations.add(currentSample);

			// remove current holded object for next recursion
			List<Integer> newList = new ArrayList<Integer>(rightList);
			newList.remove((Object) el);
			addPermutations(permutations, newList, hold, m, pos + 1,
					currentSample.clone());
		}

	}
	// // calculate possible permutations
	// int noc = data.size();
	// for (int i = data.size() - 1; i > 0; i--) {
	// noc *= i;
	// }
	//
	// // prepare iterators
	// List<Iterator<Integer>> itl = new ArrayList<Iterator<Integer>>();
	// for (int i = m; i > 0; i--) {
	// itl.add(data.iterator());
	// }
	//
	// //
	// List<Integer[]> ret = new ArrayList<Integer[]>();
	// for (int i = 0; i < noc; i++) {
	// Integer[] sample = new Integer[m];
	// ret.add(sample);
	// for (int index = 0; index < m - 1; index++) {
	// Iterator<Integer> curIt = itl.get(index);
	// Integer curInt = curIt.next();
	// curIt.remove();
	// sample[index] = curInt;
	// }
	// }

	// clean returned list

}
