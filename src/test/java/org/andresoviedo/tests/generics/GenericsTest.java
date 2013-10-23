package org.andresoviedo.tests.generics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GenericsTest {

	GenericsTest() {

	}

	public static class A implements Serializable {

	}

	public static class B implements Serializable {

	}

	public static void main2(String[] args) {
		// Error: Type mismatch: cannot convert from List<capture#1-of ? extends
		// Serializable> to List<GenericTest.A>
		// List<A> aList = getInfo().get("A");
		// List<B> BList = getInfo().get("B");

		// Warning: Type safety: Unchecked cast from List<capture#1-of ? extends
		// Serializable> to List<GenericTest.A>
		List<? extends Serializable> aList = getInfo().get("A");
		List<? extends Serializable> BList = getInfo().get("B");

		Map<String, List<? extends Serializable>> p = getInfo();
	}

	public static Map<String, List<? extends Serializable>> getInfo() {
		Map<String, List<? extends Serializable>> infoMap = new HashMap<String, List<? extends Serializable>>();

		List<A> aList = new ArrayList<A>();
		List<B> bList = new ArrayList<B>();

		try {
			aList.add(new A());
			infoMap.put("A", aList);

			bList.add(new B());
			infoMap.put("B", bList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return infoMap;
	}

	public static void main(String[] args) {

		new GenericsTest().new Test2().hashCodes(new Integer(1));

		int[] x = { 1, 2, 3 };
		int y[] = { 4, 5, 6 };
		new GenericsTest().go(x, y);

	}

	void go(int[]... z) {
		for (int[] a : z)
			System.out.print(a[0]);
	}

	private final void hola() {

	}

	@Override
	public int hashCode() {
		return 5 + 1 / 2;
	}

	public void genMeth1(List<? super Integer> list) {
		list.add(1);
	}

	public void genMeth2(List<? super Long> list) {
		list.add(1L);
		// System.out.println(list);
		Object l = list.get(0);
	}

	class Test2 extends GenericsTest {
		Test2() {
			hola();
		}

		@Override
		public int hashCode() throws RuntimeException {
			System.out.println("1");
			return 5 + 1 / 2;
		}

		public void hashCodes(int s) {
			System.out.println("2");
		}

		public void hashCodes(Integer s) {
			System.out.println("3");
		}

	}
}

class Test2<T> {

	enum Element {
		E, A;
		public String toSt() {
			return "";
		}
	}

	T t;

	public static void main(String[] args) {
		Test2<String> ts = new Test2<String>();
		Scanner scanner = new Scanner(",One");
		scanner.useDelimiter(",");
		System.out.println(scanner.nextInt());
		System.out.println(scanner.next());
	}

	Test2() {

	}

	Test2(T obj) {
		t = obj;
	}

	T get() {
		return t;
	}

	static String aa() throws IOException {
		Thread t = null;
		Map<String, Integer> m;
		new BufferedWriter(new PrintWriter(new FileWriter("")));
		try {
			new BufferedReader(new FileReader(new File("")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
