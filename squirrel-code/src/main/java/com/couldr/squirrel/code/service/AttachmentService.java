package com.couldr.squirrel.code.service;

import com.couldr.squirrel.code.model.entity.Attachment;
import com.couldr.squirrel.code.service.base.CrudService;
import com.couldr.squirrel.upload.model.UploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService extends CrudService<Attachment, Integer> {

  Attachment getByKey(String key);

  Attachment upload(MultipartFile file,String path);

  UploadResult move(String path,String newPath);

  Attachment removePermanently(String  key);

  Attachment convertToBean(UploadResult uploadResult);
}
