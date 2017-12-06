package com.dancer.provalidation.inherits;

public class Child extends Parent {
	
	static{
		System.out.println("child! haha !");
	}

	public static void inheritStatic(){
		System.out.println("this is child");
	}
	
	public static void main(String[] args) {
		System.out.println("enter main");
		Child.inheritStatic();
		Parent parent=new Child();
		parent.inheritStatic();
	}
}
