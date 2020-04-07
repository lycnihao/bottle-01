package com.couldr.squirrel.code.contorller.api;

import com.couldr.squirrel.code.model.entity.Folder;
import com.couldr.squirrel.code.service.FolderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件夹(和文件)控制器
 *
 * @author LiYuanCheng
 * @date 2020-04-07 11:18
 */
@RestController
@RequestMapping("/api/admin/folder")
public class FolderController {

  private final FolderService folderService;

  public FolderController(FolderService folderService) {
    this.folderService = folderService;
  }

  @GetMapping
  private Map<String,Object> getFolder(@RequestParam(defaultValue = "root") String path){
    System.out.println(path);
    Folder folder = folderService.getByPath(path);
    List<Folder> folderSub = folderService.getByParentId(folder.getId());
    System.out.println(folderSub);
    Map<String,Object> resultMap = new HashMap<>();
    resultMap.put("folderSub",folderService.convertToDto(folderSub));
    resultMap.put("parent",folder.getPid() == 0 ? null : folderService.getById(folder.getPid()).getPath());
    return resultMap;
  }

}
