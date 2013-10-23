package org.andresoviedo.tests.language;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Batman {
	int squares = 81;

	public static void main(String[] args) {
		SubCalc sc = new SubCalc();
		System.out.println(sc.multiply(3, 4));
		System.out.println(SubCalc.multiply(2, 2));
		SuperCalc a = new SubCalc();
		System.out.println(a.a);
		List<? extends String> list = new ArrayList<String>();
		list.add(null);
		System.out.println(list);

		String[] colors = { "blue", "red", "green", "yellow", "orange" };
		Arrays.sort(colors);
		System.out.println(Arrays.toString(colors));
		int s2 = Arrays.binarySearch(colors, "orange");
		int s3 = Arrays.binarySearch(colors, "violet");
		System.out.println(s2 + " " + s3);

//		java.io.Console c = System.console();
//		char[] pw = c.readPassword("%s", "pw: ");
//		System.out.println("got " + pw);
//		String name = c.readLine("%s", "name: ");
//		System.out.println(" got ");
		
		

		// String #name = "Jane Doe";
		// int $age = 24;
		// Double _height = 123.5;
		// double ~temp = 37.5;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ObjectOutputStream os = new ObjectOutputStream(out);
			os.writeObject(new Beagle());
			os.close();
			System.out.println("--");
			ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(out.toByteArray()));
			is.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void go() {
		incr(++squares);
		System.out.println(squares);
	}

	void incr(int squares) {
		squares += 10;
	}
}

class Animal implements Serializable {
	Animal() {
		System.out.print("a");
	}
}

class Dog extends Animal implements Serializable {
	Dog() {
		System.out.print("d");
		List<Integer> ints = new ArrayList<Integer>();
		ints.add(2);
		List<Number> nums = null; // ints;  //valid if List<Integer> is a subtype of List<Number> according to substitution rule. 
		nums.add(3.14);  
		Integer x=ints.get(1); // now 3.14 is assigned to an Integer variable!
	}
	
//	public void addStrings(List list) {
//		list.add("");
//	}
	
	public void addStrings(List<? super String> list) {
		list.add("");
		List<? extends Number> lll = new Vector<Integer>();
		List<? super Number> supnum = null;
		supnum.add(Float.MAX_VALUE);
		supnum.add(Integer.MAX_VALUE);
		Object o = supnum.get(0);
		
	}

}

class Beagle extends Dog {
}

class SuperCalc {
	public int a = 5;

	protected static int multiply(int a, int b) {
		return a * b;
	}
}

class SubCalc extends SuperCalc {
	public int a = 8;

	public static int multiply(int a, int b) {
		int c = SuperCalc.multiply(a, b);
		return c;
	}
}
