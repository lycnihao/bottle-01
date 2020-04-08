package com.couldr.squirrel.code.service.impl;

import com.couldr.squirrel.code.model.entity.Attachment;
import com.couldr.squirrel.code.model.enums.AttachmentType;
import com.couldr.squirrel.code.repository.AttachmentRepository;
import com.couldr.squirrel.code.service.AttachmentService;
import com.couldr.squirrel.code.service.base.AbstractCrudService;
import com.couldr.squirrel.upload.FileHandler;
import com.couldr.squirrel.upload.model.UploadResult;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

/**
 * 附件
 *
 * @author LiYuanCheng
 * @date 2020-04-08 10:39
 */
@Service
public class AttachmentServiceImpl extends AbstractCrudService<Attachment, Integer> implements AttachmentService {

  private final AttachmentRepository attachmentRepository;

  private final FileHandler fileHandler;

  protected AttachmentServiceImpl(AttachmentRepository attachmentRepository,
      FileHandler fileHandler) {
    super(attachmentRepository);
    this.attachmentRepository = attachmentRepository;
    this.fileHandler = fileHandler;
  }

  @Override
  public Attachment upload(MultipartFile file) {
    Assert.notNull(file, "文件不能为空");

    UploadResult uploadResult = fileHandler.upload(file);
    Attachment attachment = convertToBean(uploadResult);
    System.out.println(attachment);
    return create(attachment);
  }

  @Override
  public Attachment convertToBean(UploadResult uploadResult) {
    Attachment attachment = new Attachment();
    attachment.setFileKey(uploadResult.getKey());
    attachment.setName(uploadResult.getFilename());
    attachment.setPath(uploadResult.getFilePath());
    attachment.setSuffix(uploadResult.getSuffix());
    attachment.setMediaType(uploadResult.getMediaType().toString());
    attachment.setType(AttachmentType.LOCAL);
    attachment.setSize(uploadResult.getSize());
    return attachment;
  }

}
