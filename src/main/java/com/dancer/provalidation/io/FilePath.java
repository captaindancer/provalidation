package com.dancer.provalidation.io;

import java.io.File;

public class FilePath {

	public static void main(String[] args) {
		String filepath="/Users/liufeng/git/datasync/target/qadatasync.jar";
		int index=filepath.lastIndexOf("/");
		System.out.println(index);
		System.out.println(filepath.substring(0, index));
		System.out.println(new File(filepath).getParent());
		
		filepath="/Users/liufeng/yuewen/serverload/databaseinfo1";
		File file=new File(filepath);
		if(file.exists()){
			System.out.println("文件已经存在");
			System.out.println(file.getPath());
		}else{
			System.out.println("文件不存在");
		}
	}

}
