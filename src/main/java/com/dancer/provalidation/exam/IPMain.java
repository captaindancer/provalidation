package com.dancer.provalidation.exam;

import java.util.Scanner;

public class IPMain {

	public static void main(String[] args) {
		int[] maskIntValues={255,255,255,255};
		String binaryMask=Integer.toBinaryString(maskIntValues[0])+Integer.toBinaryString(maskIntValues[1])+
    			Integer.toBinaryString(maskIntValues[2])+Integer.toBinaryString(maskIntValues[3]);
		System.out.println(binaryMask);
		
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0, x;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                x = sc.nextInt();
                ans += x;
            }
        } 
        System.out.println(ans);
        sc.close();
        /*
5
10.70.44.68~255.254.255.0
1.0.0.1~255.0.0.0
192.168.0.2~255.255.255.0
19..0.~255.255.255.0
192.168.0.2~255.255.255.0
         * */
	}

}
