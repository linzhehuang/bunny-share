package io.linzhehuang.bunnyshare.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
	private final static String FILE_SEPARATOR = File.separator;
	
	public static boolean isExist(String path) {
		return new File(toLocalPath(path)).exists();
	}
	
	public static boolean isParentExist(String path) {
		return new File(toLocalPath(path)).getParentFile().exists();
	}
	
	public static boolean isDirectory(String path) {
		return new File(toLocalPath(path)).isDirectory();
	}
	
	public static boolean createParent(String path) {
		return new File(toLocalPath(path)).getParentFile().mkdirs();
	}
	
	public static boolean createFile(String path) {
		try {
			return new File(toLocalPath(path)).createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean write(String path, InputStream in) {
		try (FileOutputStream out = new FileOutputStream(new File(toLocalPath(path)))) {
			int b = -1;
			while ((b=in.read()) != -1) {
				out.write(b);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static String toLocalPath(String path) {
		return path.replace("/", FILE_SEPARATOR).replace("\\", FILE_SEPARATOR);
	}
}
