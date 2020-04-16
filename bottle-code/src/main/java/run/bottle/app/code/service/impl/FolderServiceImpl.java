package run.bottle.app.code.service.impl;

import static run.bottle.app.code.model.support.BottleConst.DELIMITER;

import run.bottle.app.code.model.dto.FolderNode;
import run.bottle.app.code.model.dto.FolderOrFileDTO;
import run.bottle.app.code.model.entity.Folder;
import run.bottle.app.code.repository.FolderRepository;
import run.bottle.app.code.service.FolderService;
import run.bottle.app.code.service.base.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import run.bottle.app.upload.FileHandler;
import run.bottle.app.upload.model.FileConst;

/**
 * 文件夹(和文件)逻辑处理层
 *
 * @author LiYuanCheng
 * @date 2020-04-07 13:49
 */
@Service
public class FolderServiceImpl extends AbstractCrudService<Folder, Integer> implements FolderService  {


  private final FolderRepository folderRepository;
  private final FileHandler fileHandler;

  protected FolderServiceImpl(FolderRepository folderRepository,
      FileHandler fileHandler) {
    super(folderRepository);
    this.folderRepository = folderRepository;
    this.fileHandler = fileHandler;
  }

  @Override
  public Folder create(Folder folder){
    fileHandler.createDirectories(folder.getPath());
    return super.create(folder);
  }

  @Override
  public List<Folder> getByParentId(Integer parentId) {
    return folderRepository.getByPid(parentId);
  }

  @Override
  public Folder getByPath(String path) {
    return folderRepository.getByPath(path);
  }

  @Override
  public List<Folder> getByPathLike(String path) {
    return folderRepository.getByPathLike(path);
  }

  @Override
  public List<FolderOrFileDTO> convertToDto(List<Folder> folders) {
    List<FolderOrFileDTO> folderOrFileDTOS = new ArrayList<>();
    folders.forEach(folder -> {
      FolderOrFileDTO folderOrFileDTO = new FolderOrFileDTO();
      folderOrFileDTO.setId(folder.getId());
      folderOrFileDTO.setKey(folder.getId().toString());
      folderOrFileDTO.setName(folder.getName());
      folderOrFileDTO.setIsFolder(true);
      folderOrFileDTO.setParent(folder.getPid());
      folderOrFileDTO.setPath(folder.getPath());
      folderOrFileDTOS.add(folderOrFileDTO);
    });
    return folderOrFileDTOS;
  }

  @Override
  public Folder rename(String name, String key) {
    Folder folder = super.getById(Integer.valueOf(key));
    String path = folder.getPath();
    folder.setPath(path.substring(0, path.lastIndexOf(folder.getName())) + name);
    folder.setName(name);
    fileHandler.createDirectories(folder.getPath());
    super.update(folder);
    return folder;
  }

  @Override
  public Folder removePermanently(String key) {
    Folder folder = getById(Integer.valueOf(key));
    List<FolderNode> folderNodes = nodeConvertToList(getFolderNodeByPid(folder.getId()));
    Set<Integer> ids = folderNodes.stream().map(folderNode -> Integer.parseInt(folderNode.getKey())).collect(Collectors.toSet());
    ids.add(folder.getId());
    removeInBatch(ids);
    fileHandler.delete("upload" + DELIMITER + folder.getPath());
    return folder;
  }

  @Override
  public List<FolderNode> getFolderNode() {
    return getFolderNodeByPid(0);
  }

  @Override
  public List<FolderNode> getFolderNodeByPid(Integer pid) {
    List<Folder> list = listAll();
    return convertToNode(list,pid);
  }

  @Override
  public void copyFolder(List<FolderNode> childAll,Folder folder,String oldPath) {
    childAll.forEach(folderNode -> {
      /*String childPath = folderNode.getPath().split(oldPath)[1];*/
      Folder childFolder = new Folder();
      childFolder.setName(folderNode.getName());
      childFolder.setPid(folder.getId());
      childFolder.setPath(folder.getPath() +FileConst.DELIMITER + folder.getName() + FileConst.DELIMITER + folderNode.getName());
      create(childFolder);
     fileHandler.createDirectories(childFolder.getPath());
      copyFolder(folderNode.getChild(), childFolder, oldPath);
    });
  }

  private List<FolderNode>  convertToNode(List<Folder> list,int pid){
    List<FolderNode> children = new ArrayList<>();
    for (Folder folder : list) {
      if (folder.getPid() == pid){
        FolderNode folderNode = new FolderNode();
        folderNode.setKey(folder.getId().toString());
        folderNode.setName(folder.getName());
        folderNode.setDisabled(false);
        folderNode.setPath(folder.getPath());
        folderNode.setChild(convertToNode(list,folder.getId()));
        children.add(folderNode);
      }
    }
    return children;
  }

  private List<FolderNode>  nodeConvertToList(List<FolderNode> list){
    List<FolderNode> children = new ArrayList<>();
    for (FolderNode folder : list) {
        children.add(folder);
        children.addAll(nodeConvertToList(folder.getChild()));
      }
    return children;
  }

}
