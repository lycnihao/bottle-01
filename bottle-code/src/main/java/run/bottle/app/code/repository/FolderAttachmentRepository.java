package run.bottle.app.code.repository;

import run.bottle.app.code.model.entity.FolderAttachment;
import run.bottle.app.code.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FolderAttachmentRepository extends BaseRepository<FolderAttachment, Integer>,
        JpaSpecificationExecutor<FolderAttachment> {
    List<FolderAttachment> getByFolderId(Integer folderId);
    FolderAttachment getByAttachmentId(Integer attachmentId);
}
