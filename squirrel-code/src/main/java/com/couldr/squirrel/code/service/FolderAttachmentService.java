package com.couldr.squirrel.code.service;


import com.couldr.squirrel.code.model.entity.FolderAttachment;
import com.couldr.squirrel.code.service.base.CrudService;

import java.util.List;

public interface FolderAttachmentService extends CrudService<FolderAttachment, Integer> {
    List<FolderAttachment> getByFolderId(Integer folderId);
}
