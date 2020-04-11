package run.bottle.app.code.service;


import run.bottle.app.code.model.entity.FolderAttachment;
import run.bottle.app.code.service.base.CrudService;

import java.util.List;

public interface FolderAttachmentService extends CrudService<FolderAttachment, Integer> {
    List<FolderAttachment> getByFolderId(Integer folderId);
}
