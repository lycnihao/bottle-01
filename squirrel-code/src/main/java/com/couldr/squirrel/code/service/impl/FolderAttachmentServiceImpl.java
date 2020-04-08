package com.couldr.squirrel.code.service.impl;

import com.couldr.squirrel.code.model.entity.FolderAttachment;
import com.couldr.squirrel.code.repository.FolderAttachmentRepository;
import com.couldr.squirrel.code.service.FolderAttachmentService;
import com.couldr.squirrel.code.service.base.AbstractCrudService;
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
