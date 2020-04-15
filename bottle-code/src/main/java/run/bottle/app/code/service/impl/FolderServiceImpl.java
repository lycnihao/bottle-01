package run.bottle.app.code.service.impl;

import static run.bottle.app.code.model.support.BottleConst.DELIMITER;

import run.bottle.app.code.model.dto.FolderNode;
import run.bottle.app.code.model.dto.FolderOrFileDTO;
import run.bottle.app.code.model.entity.Folder;
import run.bottle.app.code.repository.FolderRepository;
import run.bottle.app.code.service.FolderService;
import run.bottle.app.code.service.base.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    super.update(folder);
    return folder;
  }

  @Override
  public Folder removePermanently(String key) {
    Folder folder = getById(Integer.valueOf(key));
    remove(folder);
    fileHandler.delete("upload" + DELIMITER + folder.getPath());
    return folder;
  }

  @Override
  public List<FolderNode> getFolderNode() {
    List<Folder> list = listAll();
    return convertToNode(list,0);
  }

  private List<FolderNode>  convertToNode(List<Folder> list,int pid){
    List<FolderNode> children = new ArrayList<>();
    for (Folder folder : list) {
      if (folder.getPid() == pid){
        FolderNode folderNode = new FolderNode();
        folderNode.setKey(folder.getId().toString());
        folderNode.setName(folder.getName());
        folderNode.setDisabled(false);
        folderNode.setChild(convertToNode(list,folder.getId()));
        children.add(folderNode);
      }
    }
    return children;
  }

}
