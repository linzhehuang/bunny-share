package io.linzhehuang.bunnyshare.services;

import java.io.InputStream;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import io.linzhehuang.bunnyshare.utils.FileUtil;

@Service
public class FileService {
	
	private final static String PROTOCOL = "http://";
	private String basePath = "/tmp/bunny-share/";
	private String host = "127.0.0.1";
	private String port = "8080";
	
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
	
	public boolean sendFile(String path, String localPath) {
		MultiValueMap<String, Object> request = new LinkedMultiValueMap<>();
		request.add("content", new FileSystemResource(
				FileUtil.toLocalPath(localPath)));
		
		ResponseEntity<String> response = new RestTemplate().postForEntity(
				getURL(path), request, String.class);
		if (response.getStatusCode() != HttpStatus.CREATED) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private String getURL(String path) {
		return PROTOCOL + host + ":" + port + "/file" + path;
	}
	
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(String port) {
		this.port = port;
	}
}
