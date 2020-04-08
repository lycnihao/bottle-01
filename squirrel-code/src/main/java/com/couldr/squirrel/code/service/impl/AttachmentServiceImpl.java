package com.couldr.squirrel.code.service.impl;

import com.couldr.squirrel.code.model.entity.Attachment;
import com.couldr.squirrel.code.model.enums.AttachmentType;
import com.couldr.squirrel.code.model.support.UploadResult;
import com.couldr.squirrel.code.repository.AttachmentRepository;
import com.couldr.squirrel.code.service.AttachmentService;
import com.couldr.squirrel.code.service.base.AbstractCrudService;
import com.couldr.squirrel.upload.FileHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

/**
 * 附件
 *
 * @author LiYuanCheng
 * @date 2020-04-08 10:39
 */
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
    return attachment;
  }

  @Override
  public Attachment convertToBean(UploadResult uploadResult) {
    Attachment attachment = new Attachment();
    BeanUtils.copyProperties(uploadResult,attachment);
    return attachment;
  }
}
