package io.linzhehuang.bunnyshare.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.linzhehuang.bunnyshare.services.FileService;

@RestController
@RequestMapping("/file")
public class FileController {
	@Autowired
	FileService fileService;
	
	@PutMapping("/{path}")
	public ResponseEntity<String> data(@PathVariable("path") String path,
			@RequestParam("content") MultipartFile content) {
		try {
			if (fileService.saveFile(path, content.getInputStream())) {
				return new ResponseEntity<>("created failed",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("created", HttpStatus.CREATED);
	}
}