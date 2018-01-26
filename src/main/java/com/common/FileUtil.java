package com.common;

import java.io.File;
import java.io.FileFilter;
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

	public static List<InputStream> scanFile(String path ) throws IOException, URISyntaxException {
		final List<InputStream> result = new LinkedList<>();
		//来得到当前的classpath的绝对路径的URL表示法。   
		URI url = Thread.currentThread().getContextClassLoader().getResource("mapper").toURI();
		//String url = Thread.currentThread().getContextClassLoader().getResource("").toString();
		Path start = Paths.get(url);
		Path walkFileTree = Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
				if(!attrs.isDirectory()){
					System.out.println(path.getFileName());
					System.out.println(path.getParent());
					result.add(Files.newInputStream(path));
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
		//List<InputStream> test2 = test2();
		System.out.println(FileUtil.class.getResource("/mapper"));
		URI uri = Thread.currentThread().getContextClassLoader().getResource("mapper").toURI();
		System.out.println(uri);
		System.out.println(Paths.get(FileUtil.class.getResource("/mapper").toURI()));
		System.out.println("index-sql.xml".matches("sql"));
	}
}
