package org.andresoviedo.tests.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AnnotationsTest {

	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException {

		Foo a = new Foo();
		FooAnnotation ba = a.getClass().getAnnotation(FooAnnotation.class);
		Class<? extends Muppet> clazz = ba.aaa();
		Muppet mobj = clazz.newInstance();
		System.out.println(mobj.hola());

		Foo2 a2 = new Foo2();
		ba = a2.getClass().getAnnotation(FooAnnotation.class);
		clazz = ba.aaa();
		mobj = clazz.newInstance();
		System.out.println(mobj.hola());

	}
}

@FooAnnotation(aaa = Bar.class)
class Foo {

}

@FooAnnotation(aaa = Bar2.class)
class Foo2 {

}

interface Muppet {
	String hola();
}

class Bar implements Muppet {
	public String hola() {
		return "holita1";
	}
}

class Bar2 implements Muppet {
	public String hola() {
		return "holita2";
	}
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface FooAnnotation {
	Class<? extends Muppet> aaa() default Muppet.class;
}
