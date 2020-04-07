package com.couldr.squirrel.code.repository;

import com.couldr.squirrel.code.model.entity.Folder;
import com.couldr.squirrel.code.repository.base.BaseRepository;
import java.util.List;

public interface FolderRepository extends BaseRepository<Folder, Integer>  {
  Folder getByPath(String path);
  List<Folder> getByPid(Integer pId);
  List<Folder> getByPathLike(String path);
}
