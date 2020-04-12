package run.bottle.app.code.service.impl;

import run.bottle.app.code.model.dto.FolderOrFileDTO;
import run.bottle.app.code.model.entity.Folder;
import run.bottle.app.code.repository.FolderRepository;
import run.bottle.app.code.service.FolderService;
import run.bottle.app.code.service.base.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件夹(和文件)逻辑处理层
 *
 * @author LiYuanCheng
 * @date 2020-04-07 13:49
 */
@Service
public class FolderServiceImpl extends AbstractCrudService<Folder, Integer> implements FolderService  {


  private final FolderRepository folderRepository;

  protected FolderServiceImpl(FolderRepository folderRepository) {
    super(folderRepository);
    this.folderRepository = folderRepository;
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


}
