package com.couldr.squirrel.code.service;

import com.couldr.squirrel.code.model.dto.FolderOrFileDTO;
import com.couldr.squirrel.code.model.entity.Folder;
import com.couldr.squirrel.code.service.base.CrudService;

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
}
