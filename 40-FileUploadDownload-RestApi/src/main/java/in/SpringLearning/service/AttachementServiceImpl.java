package in.SpringLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import in.SpringLearning.model.Attachment;
import in.SpringLearning.repo.AttachementRepo;

@Service
public class AttachementServiceImpl implements AttachmentService{
	
	@Autowired
	private AttachementRepo attachmentRepo;

	@Override
	public Attachment saveAttachment(MultipartFile file) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			
			if(fileName.contains("..")) {
				throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			
			Attachment attachment = new Attachment(fileName, file.getContentType(), file.getBytes());
			
			return attachmentRepo.save(attachment);
			
		}catch (Exception e) {
			
			throw new RuntimeException("Could not store file " + fileName + ". Please try again!", e);
		}
	}

	@Override
	public Attachment getAttachment(String id) {
		
		return attachmentRepo
				.findById(id)
				.orElseThrow(()-> new RuntimeException("File not found"));
	}

}
