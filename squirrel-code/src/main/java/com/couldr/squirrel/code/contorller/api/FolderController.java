package com.couldr.squirrel.code.contorller.api;

import com.couldr.squirrel.code.model.dto.FolderOrFileDTO;
import com.couldr.squirrel.code.service.FolderService;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  private List<FolderOrFileDTO> getFolder(){
    String path = "/www/wwwroot/baidu/";
    String[] paths = path.split("/");
    return folderService.getByName(Arrays.asList(paths));
  }

}
