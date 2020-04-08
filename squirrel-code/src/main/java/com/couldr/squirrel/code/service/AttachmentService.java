package com.couldr.squirrel.code.service;

import com.couldr.squirrel.code.model.entity.Attachment;
import com.couldr.squirrel.code.model.support.UploadResult;
import com.couldr.squirrel.code.service.base.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AttachmentService extends CrudService<Attachment, Integer> {
  Attachment upload(MultipartFile file);

  Attachment convertToBean(UploadResult uploadResult);
}
