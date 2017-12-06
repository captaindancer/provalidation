package com.dancer.provalidation.math;

import redis.clients.util.Hashing;

public class ExponentMain {

	public static void main(String[] args) {
		System.out.println(Math.exp(1));
		System.out.println(Math.exp(0));
		
		long value=Hashing.MURMUR_HASH.hash("24");
		System.out.println((int)value);
		System.out.println(getTableName(24));
	}
	
	public static String getTableName(long bookid){
		int index=(int)Hashing.MURMUR_HASH.hash(String.valueOf(bookid));
		index=Math.abs(index);
		return String.format("%03d",index%16);
	}

}
