package com.couldr.squirrel.code.service.impl;

import com.couldr.squirrel.code.exception.ServiceException;
import com.couldr.squirrel.code.model.dto.FolderOrFileDTO;
import com.couldr.squirrel.code.model.entity.Folder;
import com.couldr.squirrel.code.repository.FolderRepository;
import com.couldr.squirrel.code.service.FolderService;
import com.couldr.squirrel.code.service.base.AbstractCrudService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 文件夹(和文件)逻辑处理层
 *
 * @author LiYuanCheng
 * @date 2020-04-07 13:49
 */
@Service
public class FolderServiceImpl extends AbstractCrudService<Folder, Integer> implements FolderService  {

  private List<String> folderName;

  private final FolderRepository folderRepository;

  protected FolderServiceImpl(FolderRepository folderRepository) {
    super(folderRepository);
    this.folderRepository = folderRepository;
    folderName = new ArrayList<>();
  }

  @Override
  public List<FolderOrFileDTO> getByName(List<String> names) {
    List<FolderOrFileDTO> folderOrFileDTOS = new ArrayList<>();

    getSubFolder(1,names);

    System.out.println(folderName);

    if (names.size() != folderName.size() + 1){
      throw new ServiceException("当前路径不存在或已删除");
    }

    for (int i = 0; i < folderName.size(); i++){
      if ( !folderName.get(i).equals(names.get(i + 1)) ) {
        throw new ServiceException("当前路径不存在或已删除");
      }
    }
    System.out.println("正确的路径");
    return null;
  }

  @Override
  public List<String> getParentFolder(Integer id) {
    folderName = new ArrayList<>();
    getParent(id);
    return folderName;
  }

  @Override
  public List<String> getSubFolder(Integer id, List<String> names) {
    folderName = new ArrayList<>();
    getSub(id,names);
    return folderName;
  }

  private void getSub(Integer id,List<String> names) {
    List<Folder> folders = folderRepository.getByPid(id);
    folders.forEach(folder -> {
      if (folder.getName().equals(names.subList(1,names.size()).get(0))){
        folderName.add(folder.getName());
        getSub(folder.getId(), names.subList(1,names.size()));
      }
    });
  }

  /**
   * 获取父级目录
   * @param id 当前目录id
   * @return
   */
  private void getParent(Integer id) {
    Folder folder = getById(id);
    folderName.add(folder.getName());
    if (folder.getPid() != 0) {
      getParent(getById(folder.getPid()).getId());
    }
  }


}
