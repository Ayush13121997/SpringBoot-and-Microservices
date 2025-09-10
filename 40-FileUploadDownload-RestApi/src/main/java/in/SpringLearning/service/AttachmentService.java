package in.SpringLearning.service;

import org.springframework.web.multipart.MultipartFile;

import in.SpringLearning.model.Attachment;

public interface AttachmentService {

	Attachment saveAttachment(MultipartFile file);

	Attachment getAttachment(String fieldId);

}
