package io.linzhehuang.bunnyshare.utils.services;

import java.io.ByteArrayInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.linzhehuang.bunnyshare.services.FileService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServicesTests {
	@Autowired
	FileService fileService;
	
	@Test
	public void saveFileTest() {
		fileService.setBasePath("/tmp");
		fileService.saveFile("/test.txt",
				new ByteArrayInputStream("DEMO\n".getBytes()));
	}
	
	@Test
	public void sendFileTest() {
		System.out.println(fileService.sendFile("/a.txt", "/tmp/test.txt"));
	}
}
