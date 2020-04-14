package run.bottle.app.code.service;

import org.springframework.web.multipart.MultipartFile;
import run.bottle.app.code.model.entity.Attachment;
import run.bottle.app.code.service.base.CrudService;
import run.bottle.app.upload.model.UploadResult;

public interface AttachmentService extends CrudService<Attachment, Integer> {

  Attachment getByKey(String key);

  Attachment upload(MultipartFile file,String path);

  UploadResult rename(String path, String newPath);

  Attachment removePermanently(String  key);

  Attachment convertToBean(UploadResult uploadResult);
}
