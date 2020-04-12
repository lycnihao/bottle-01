package run.bottle.app.code.repository;

import run.bottle.app.code.model.entity.Attachment;
import run.bottle.app.code.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AttachmentRepository extends BaseRepository<Attachment, Integer>,
    JpaSpecificationExecutor<Attachment> {
    Attachment getByFileKey(String key);
}
