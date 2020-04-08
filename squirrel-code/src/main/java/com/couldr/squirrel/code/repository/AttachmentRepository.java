package com.couldr.squirrel.code.repository;

import com.couldr.squirrel.code.model.entity.Attachment;
import com.couldr.squirrel.code.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AttachmentRepository extends BaseRepository<Attachment, Integer>,
    JpaSpecificationExecutor<Attachment> {

}
