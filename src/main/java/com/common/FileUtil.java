package com.common;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.springframework.core.io.PathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 *
 * @author yangst
 * @date 2018年1月25日 上午9:27:16
 * 
 */
public class FileUtil {

	/**
	 * 获取指定路径下文件名称符合正则表达式的文件
	 * @author yangst
	 * @date 2018年1月30日 上午10:37:21
	 * @param path 指定路径
	 * @param regex 正则表达式
	 * @return
	 * @throws FileNotFoundException
	 * @throws URISyntaxException
	 */
	public static List<File> scanFileByOld(String path,final String regex)  {
		final List<File> result = new LinkedList<>();
		try {
			URI url = FileUtil.class.getResource(path).toURI();
			File f = new File(url);
			if (f.exists()) {
				recursionFile(result,f,regex);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
			
		}
		return result;
	}

	/**
	 * 递归扫描指定路径下文件名称符合正则表达式的文件
	 * @author yangst
	 * @date 2018年1月30日 上午10:37:55
	 * @param list 符合扫描条件的文件集合
	 * @param file 被扫描文件
	 * @param regex 正则表达式
	 * @return
	 */
	public static List<File> recursionFile(final List<File> list,File file,final String regex) {
		file.listFiles(new FileFilter(){
			@Override
			public boolean accept(File dir){
				//System.out.println(dir.getAbsolutePath());
				//System.out.println(dir.getName());
				if((dir.isFile() && dir.getName().matches(regex))){
					list.add(dir.getAbsoluteFile());
					return true;
				}else if(dir.isDirectory()){
					recursionFile(list,dir.getAbsoluteFile(),regex);
					return false;
				}
				return false;
				
			}
		});
		return list;
	}
	
	/**
	 * 获取指定路径下文件名称符合正则表达式的文件
	 * @author yangst
	 * @date 2018年1月30日 上午10:42:02
	 * @param path 指定路径
	 * @param regex 正则表达式
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static List<Path> scanFile(String path,final String regex) {
		final List<Path> result = new LinkedList<>();
		//final String regex = "^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\.[x|X][m|M][l|L]$";
		//来得到当前的classpath的绝对路径的URL表示法。   
		
		try {
			URI url = Thread.currentThread().getContextClassLoader().getResource(path).toURI();
			Path start = Paths.get(url);
			Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
					if(!attrs.isDirectory()){
						if(path.getFileName().toString().matches(regex)){
							result.add(path);
						}
					}
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
					return FileVisitResult.CONTINUE;
				}

			});
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

		return result;
	}
	
	public static  List<PathResource> scanFile2(String path,final String regex) {
		final List<PathResource> result = new LinkedList<>();
		//final String regex = "^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\.[x|X][m|M][l|L]$";
		//来得到当前的classpath的绝对路径的URL表示法。   
		
		try {
			URI url = Thread.currentThread().getContextClassLoader().getResource(path).toURI();
			Path start = Paths.get(url);
			Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
					if(!attrs.isDirectory()){
						if(path.getFileName().toString().matches(regex)){
							result.add(new PathResource(path));
						}
					}
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
					return FileVisitResult.CONTINUE;
				}

			});
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

		return result;
	}
	
	public static List<Path> scanXmlFile(String path){
		return scanFile(path,"^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\.[x|X][m|M][l|L]$");
	}
	
	public static List<Path> scanPropertiesFile(String path){
		return scanFile(path,"^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\.[p|P][r|R][o|O][p|P][e|E][r|R][t|T][i|I][e|E][s|S]$");
	}

	public static PathResource[] getMapper(){
		List<Path> paths = scanXmlFile("mapper");
		PathResource[] pr = new PathResource[paths.size()];
		for (int i = 0; i < paths.size(); i++) {
			pr[i] = new PathResource(paths.get(i));
		}
		return pr;
	}
	
	public static PathResource[] getProperties(){
		List<Path> paths = scanPropertiesFile("config");
		PathResource[] pr = new PathResource[paths.size()];
		for (int i = 0; i < paths.size(); i++) {
			pr[i] = new PathResource(paths.get(i));
		}
		return pr;
	}
	
	public static void main(String[] args) throws  IOException, URISyntaxException {
		List<File> test = scanFileByOld("/mapper","^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\.[x|X][m|M][l|L]$");
		List<Path> test2 = scanFile("mapper","^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\.[x|X][m|M][l|L]$");
		System.out.println(FileUtil.class.getResource("/mapper"));
		URI uri = Thread.currentThread().getContextClassLoader().getResource("mapper").toURI();
		System.out.println(uri);
		System.out.println(Paths.get(FileUtil.class.getResource("/mapper").toURI()));
		System.out.println("index-sql.xml".matches("^([a-zA-Z]+-?)+[a-zA-Z0-9]+\\.[x|X][m|M][l|L]$"));
		
	}
}
