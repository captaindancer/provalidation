package com.dancer.provalidation.exam;

import java.util.Scanner;

public class IPStat {

	private static int a_count = 0; 
    private static int b_count = 0; 
    private static int c_count = 0; 
    private static int d_count = 0; 
    private static int e_count = 0;
    private static int error_count = 0; 
    private static int private_count = 0;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int lineNumber=Integer.parseInt(sc.nextLine());
//		int lineNumber=sc.nextInt();
//		sc.nextLine();
		for(int index=0;index<lineNumber;index++){
			String lineText=sc.nextLine();
			statisIP(lineText);
		}
		StringBuilder builder=new StringBuilder();
		builder.append(a_count).append(" ").append(b_count).append(" ").append(c_count).
		append(" ").append(d_count).append(" ").append(e_count).
		append(" ").append(error_count).append(" ").append(private_count);
		System.out.println(builder);
		sc.close();
	}
	
	public static void statisIP(String line){
		//先判断是否是不合法的
		String[] splitArray = line.split("~");
		if(splitArray.length != 2){
			return;
		}
        String ip = splitArray[0];
        String mask = splitArray[1];
        
        String[] ipSplits = ip.split("\\.");
        String[] maskSplits = mask.split("\\.");

        if(ipSplits.length != 4 || maskSplits.length != 4){
        	error_count++;
        	return;
        }
        
        int[] ipIntValues=new int[4];
        int[] maskIntValues=new int[4];
        
        for(int index=0;index<4;index++){
        	try{
        		ipIntValues[index]=Integer.parseInt(ipSplits[index]);
            	maskIntValues[index]=Integer.parseInt(maskSplits[index]);
        	}catch(NumberFormatException e){
        		error_count++;
        		return;
        	}
        	if(ipIntValues[index] > 255 || ipIntValues[index] < 0 || maskIntValues[index] > 255 || maskIntValues[index] < 0){
        		error_count++;
        		return;
        	}
        	
        }
        
        String binaryMask=Integer.toBinaryString(maskIntValues[0])+Integer.toBinaryString(maskIntValues[1])+
    			Integer.toBinaryString(maskIntValues[2])+Integer.toBinaryString(maskIntValues[3]);
    	if(binaryMask.contains("01")){
    		error_count++;
    		return;
    	}
    	
    	if(ipIntValues[0]>=1 && ipIntValues[0]<=126){
    		a_count++;
    	}

    	if (ipIntValues[0] >= 128 && ipIntValues[0] <= 191) {
            b_count++;
        }

        if (ipIntValues[0] >= 192 && ipIntValues[0] <= 223) {
            c_count++;
        }

        if (ipIntValues[0] >= 224 && ipIntValues[0] <= 239) {
            d_count++;
        }
        if (ipIntValues[0] >= 240 && ipIntValues[0] <= 255) {
            e_count++;
        }

        if (ipIntValues[0] == 10) {
            private_count++;
        }
    	
        if (ipIntValues[0] == 172) {
            if (ipIntValues[1] >= 16 && ipIntValues[1] <= 31) {
                private_count++;
            }
        }

        if (ipIntValues[0] == 192) {
            if (ipIntValues[1] == 168) {
                private_count++;
            }
        }
	}

}
