package com.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author yangst
 * @date 2018年1月25日 上午9:27:16
 * 
 */
public class FileUtil {

	public static List<InputStream> test(String path) throws FileNotFoundException, URISyntaxException {
		URI url = FileUtil.class.getResource(path).toURI();
		List<InputStream> result = new LinkedList<>();
		File f = new File(url);
		if (f.exists()) {
//			File[] listFiles = f.listFiles(new FilenameFilter(){
//				
//				@Override
//				public boolean accept(File dir, String name){
//					System.out.println(name);
//					if((dir.isFile() && name.matches("^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\.[x|X][m|M][l|L]$"))){
//						return true;
//					}
//					return false;
//				}
//			});
			File[] listFiles = f.listFiles(new FileFilter() {

				@Override
				public boolean accept(File dir) {
					System.out.println(dir.getName());
					
					if ((dir.isFile() && dir.getName().matches("^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\.[x|X][m|M][l|L]$"))) {
						return true;
					}
					return false;
				}
			});
			
		
			for(File file : listFiles){
				result.add(new FileInputStream(file));
			}
		}
		return result;
	}

	public static List<InputStream> test(List<InputStream> list,File file){
		
		return list;
	}
	public static List<InputStream> scanFile(String path,final String regex) throws IOException, URISyntaxException {
		final List<InputStream> result = new LinkedList<>();
		//final String regex = "^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\.[x|X][m|M][l|L]$";
		//来得到当前的classpath的绝对路径的URL表示法。   
		URI url = Thread.currentThread().getContextClassLoader().getResource(path).toURI();
		Path start = Paths.get(url);
		Path walkFileTree = Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
				if(!attrs.isDirectory()){
					if(path.getFileName().toString().matches(regex)){
						result.add(Files.newInputStream(path));
					}
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				return FileVisitResult.CONTINUE;
			}

		});

		return result;
	}

	public static void main(String[] args) throws  IOException, URISyntaxException {
		List<InputStream> test = test("/mapper");
		List<InputStream> test2 = scanFile("mapper","^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\.[x|X][m|M][l|L]$");
		System.out.println(FileUtil.class.getResource("/mapper"));
		URI uri = Thread.currentThread().getContextClassLoader().getResource("mapper").toURI();
		System.out.println(uri);
		System.out.println(Paths.get(FileUtil.class.getResource("/mapper").toURI()));
		System.out.println("index-sql.xml".matches("^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\.[x|X][m|M][l|L]$"));
		
	}
}
