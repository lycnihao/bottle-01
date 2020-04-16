package run.bottle.app.code.service;

import run.bottle.app.code.model.dto.FolderNode;
import run.bottle.app.code.model.dto.FolderOrFileDTO;
import run.bottle.app.code.model.entity.Folder;
import run.bottle.app.code.service.base.CrudService;

import java.util.List;

public interface FolderService extends CrudService<Folder, Integer> {

  /**
   *  获取子目录
   * @param parentId 父目录id
   * @return 子目录
   */
  List<Folder> getByParentId(Integer parentId);
  /**
   *  根据路径获取当前目录信息
   * @param path 目录路径
   * @return 目录
   */
  Folder getByPath(String path);

  /**
   * 获取包含 {path} 的所有目录
   * @param path
   * @return
   */
  List<Folder> getByPathLike(String path);

  List<FolderOrFileDTO> convertToDto (List<Folder> folders);

  Folder rename(String name,String key);

  Folder removePermanently(String key);

  List<FolderNode>  getFolderNode();

  List<FolderNode>  getFolderNodeByPid(Integer pid);

  void copyFolder(List<FolderNode> childAll,Folder folder,String oldPath);
}
