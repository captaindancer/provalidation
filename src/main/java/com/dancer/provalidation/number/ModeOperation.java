package com.dancer.provalidation.number;

public class ModeOperation {

	public static void main(String[] args) {
		System.out.println(6%2);
		
		System.out.println(Math.log10(102));
		int flag=1;
		System.out.println(flag);
		System.out.println(Integer.toBinaryString(2));
		
		System.out.println(flag<<31);
		System.out.println(flag<<33);
		System.out.println("--------");
		System.out.println(Integer.toBinaryString(123));
		System.out.println(numberOf1(123));
	}

	public static int numberOf1(int n){
        int count = 0;
        int flag = 1;
        while(flag!=0){
            if((n&flag)!=0){
                count++;
                System.out.println(flag);
            }
            flag = flag<<1;
        }
        return count;
    }
	
}
