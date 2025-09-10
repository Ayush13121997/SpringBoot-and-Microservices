package in.SpringLearning.controller;

import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import in.SpringLearning.dto.ResponseData;
import in.SpringLearning.model.Attachment;
import in.SpringLearning.service.AttachmentService;

@RestController
public class AttachmentController {

	@Autowired
	private AttachmentService attachmentService;

	@PostMapping("/upload")
	public ResponseData uploadFile(@RequestParam("file") MultipartFile file) {

		Attachment attachment = null;

		String downloadurl = "";

		attachment=attachmentService.saveAttachment(file);

		downloadurl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/download/")
				.path(attachment.getId())
				.toUriString();

		return new ResponseData(attachment.getFileName(), downloadurl, file.getContentType(), file.getSize());

	}

	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> downlaodFile(@PathVariable("id") String id) throws Exception {

		Attachment attachment = null;

		attachment = attachmentService.getAttachment(id);

		ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
				.filename(attachment.getFileName())
				.build();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(contentDisposition);	

		return ResponseEntity.ok()
				.headers(headers)
				.contentType(MediaType.parseMediaType(attachment.getFileType()))
				.body(new ByteArrayResource(attachment.getImgData()));

	}

}
