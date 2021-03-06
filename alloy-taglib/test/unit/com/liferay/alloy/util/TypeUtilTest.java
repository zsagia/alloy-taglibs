package com.liferay.alloy.util;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
public class TypeUtilTest {
	
	abstract class ParentClass {
		public void parentMethod(String param) {
			//
		}
	}
	
	class ChildClass extends ParentClass {
		public void childMethod(String param) {
			//
		}
	}

	@Test
	public void testHasInhertitedMethod() {
		boolean actual = TypeUtil.hasMethod(
			ChildClass.class.getName(), "parentMethod",
			new String[] { "java.lang.String" });

		Assert.assertTrue(actual);

		actual = TypeUtil.hasMethod(
			ChildClass.class.getName(), "childMethod",
			new String[] { "java.lang.String" });

		Assert.assertTrue(actual);
	}

	@Test
	public void testHasMethod() {
		abstract class ArrayListExt extends ArrayList<String> {
			private static final long serialVersionUID = 1L;

			public void add(int index, Class<?>[] classes) {
				//
			}
		}

		boolean actual = TypeUtil.hasMethod(
			ArrayListExt.class.getName(), "add",
			new String[] { "int", "java.lang.Object" });

		Assert.assertTrue(actual);

		actual = TypeUtil.hasMethod(
			ArrayListExt.class.getName(), "add",
			new String[] { "int", "java.lang.Class<?>[]" });

		Assert.assertTrue(actual);

		actual = TypeUtil.hasMethod(
			ArrayListExt.class.getName(), "xyz",
			new String[] { "int", "boolean" });

		Assert.assertFalse(actual);

		actual = TypeUtil.hasMethod(
			ArrayListExt.class.getName(), "add",
			new String[] { "foo", "bar" });

		Assert.assertFalse(actual);
	}

	@Test
	public void testIsJavaClass() {
		Assert.assertTrue(TypeUtil.isJavaClass(String.class.getName()));

		Assert.assertTrue(TypeUtil.isJavaClass("boolean"));

		Assert.assertTrue(TypeUtil.isJavaClass("java.lang.String[]"));

		Assert.assertTrue(TypeUtil.isJavaClass("java.lang.Class<?>[]"));

		Assert.assertFalse(TypeUtil.isJavaClass("foobar"));
	}

}