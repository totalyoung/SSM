package com.common;


import java.io.File;
import java.io.FileFilter;
import java.net.URL;
/**
 *
 * @author yangst
 * @date 2018年1月25日 上午9:27:16
 * 
 */
public class FileUtil {

	public void test() {
		String url = this.getClass().getResource("/mapper").toString();
		File f = new File(url);
		if (f.exists()) {
			File[] listFiles = f.listFiles(new FileFilter() {

				@Override
				public boolean accept(File arg0) {

					return false;
				}
			});
		}
	}

	public static void main(String[] args) {
		System.out.println(FileUtil.class.getResource("/mapper"));
		URL resource = Thread.currentThread().getContextClassLoader().getResource("mapper");
		System.out.println(resource);

	}
}
