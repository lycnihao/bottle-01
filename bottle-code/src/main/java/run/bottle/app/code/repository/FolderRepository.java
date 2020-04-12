package run.bottle.app.code.repository;

import run.bottle.app.code.model.entity.Folder;
import run.bottle.app.code.repository.base.BaseRepository;

import java.util.List;

public interface FolderRepository extends BaseRepository<Folder, Integer> {
  Folder getByPath(String path);
  List<Folder> getByPid(Integer pId);
  List<Folder> getByPathLike(String path);
}
