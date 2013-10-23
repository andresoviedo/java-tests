package org.andresoviedo.tests.language;

import java.util.Arrays;

public class ClassSignatureTest1 {

	/**
	 * @param args
	 */
	public static void main(String... args) {
		// TODO Auto-generated method stub
		System.out.println("Hola mundo!");

		Object[] myObjects = { new Integer(12), new String("foo"),
				new Integer(5), new Boolean(true) };
		Arrays.sort(myObjects);
		for (int i = 0; i < myObjects.length; i++) {
			System.out.print(myObjects[i].toString());
			System.out.print(" ");
		}

	}

}
