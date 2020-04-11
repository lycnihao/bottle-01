package run.bottle.app.code.service.impl;

import run.bottle.app.code.model.entity.FolderAttachment;
import run.bottle.app.code.repository.FolderAttachmentRepository;
import run.bottle.app.code.service.FolderAttachmentService;
import run.bottle.app.code.service.base.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @Author: iksen
 * @Date: 2020/4/8 20:24
 */
@Service
public class FolderAttachmentServiceImpl extends AbstractCrudService<FolderAttachment, Integer> implements FolderAttachmentService {

    private final FolderAttachmentRepository folderAttachmentRepository;

    public FolderAttachmentServiceImpl(FolderAttachmentRepository folderAttachmentRepository) {
        super(folderAttachmentRepository);
        this.folderAttachmentRepository = folderAttachmentRepository;
    }

    @Override
    public List<FolderAttachment> getByFolderId(Integer folderId) {
        return folderAttachmentRepository.getByFolderId(folderId);
    }
}
