package in.SpringLearning.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileRestController {
	
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception{
		
		String originalFilename = file.getOriginalFilename();
		
		Path path = Paths.get(uploadDir + File.separator + originalFilename);
		
		Files.write(path, file.getBytes());
		
		return "File Uploaded Successfully :: " + originalFilename;
		
	}

}
