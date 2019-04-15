package io.linzhehuang.bunnyshare.services;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import io.linzhehuang.bunnyshare.utils.FileUtil;

@Service
public class FileService {
	
	private String basePath = null;
	
	public boolean saveFile(String path, InputStream in) {
		// refuse save file if base path is null
		if (basePath == null) return false;
		
		String fullPath = basePath + path;
		if (!FileUtil.isExist(fullPath)) {
			if (!FileUtil.isParentExist(fullPath)) {
				if (!FileUtil.createParent(fullPath)) {
					return false;
				}
			}
			if (!FileUtil.createFile(fullPath)) {
				return false;
			}
		}
		return FileUtil.write(fullPath, in);
	}
	
	
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
}
