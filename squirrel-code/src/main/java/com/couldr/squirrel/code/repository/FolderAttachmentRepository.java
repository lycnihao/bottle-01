package com.couldr.squirrel.code.repository;

import com.couldr.squirrel.code.model.entity.FolderAttachment;
import com.couldr.squirrel.code.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FolderAttachmentRepository extends BaseRepository<FolderAttachment, Integer>,
        JpaSpecificationExecutor<FolderAttachment> {
    List<FolderAttachment> getByFolderId(Integer folderId);
}
