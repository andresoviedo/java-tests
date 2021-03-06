package org.andresoviedo.tests.regexp;

import java.text.MessageFormat;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpTest {

	public static void main3(String[] args) {
		String string = "c:\\archivos de programa\\blah\\a.exe";
		System.out.println(string);
		System.out.println(string.replaceAll("\\\\",
				Matcher.quoteReplacement("\\")));
	}

	/**
	 * @param args
	 */
	public static void main2(String[] args) {
		// TODO Auto-generated method stub
		String text = "orderNumber:123 completionStatus";
		String regexp = "(.*):\\d+ (.*)";
		String msgFormat = "{0}:X {1}";

		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(text);

		if (m.find()) {
			Object[] captures = new String[m.groupCount()];
			for (int i = 0; i < m.groupCount(); i++) {
				captures[i] = m.group(i + 1);
			}
			System.out.println(MessageFormat.format(msgFormat, captures));
		}

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String text = "my very long string to test";
		String regexp = "(^|\\W)(\\w*)";

		Matcher m = Pattern.compile(regexp).matcher(text);
		while (m.find()) {
			System.out.println("Found: "+m.group(2));
		}

	}

	public static void main4(String[] args) {
		// reemplazar caracteres sin importar acentos

		String string = "éléphante";

		string = Normalizer.normalize(string, Normalizer.Form.NFD);

		string = string.replaceAll("[^\\p{ASCII}]", "");

		System.out.println(string.replaceAll("[^a-zA-Z0-9 ]", ""));
	}
}
