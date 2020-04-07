package com.couldr.squirrel.code.repository;

import com.couldr.squirrel.code.model.entity.Folder;
import com.couldr.squirrel.code.repository.base.BaseRepository;
import java.util.List;

public interface FolderRepository extends BaseRepository<Folder, Integer>  {
  Folder getByName(String name);
  List<Folder> getByPid(Integer pId);
}
