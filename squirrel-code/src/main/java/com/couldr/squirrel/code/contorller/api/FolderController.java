package com.couldr.squirrel.code.contorller.api;

import com.couldr.squirrel.code.exception.NotFoundException;
import com.couldr.squirrel.code.model.entity.Attachment;
import com.couldr.squirrel.code.model.entity.Folder;
import com.couldr.squirrel.code.model.support.BaseResponse;
import com.couldr.squirrel.code.model.support.SquirrelConst;
import com.couldr.squirrel.code.service.AttachmentService;
import com.couldr.squirrel.code.service.FolderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

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

  private final AttachmentService attachmentService;

  public FolderController(FolderService folderService,
      AttachmentService attachmentService) {
    this.folderService = folderService;
    this.attachmentService = attachmentService;
  }

  @GetMapping
  private BaseResponse getFolder(@RequestParam(defaultValue = "root") String path){
    System.out.println(path);
    Folder folder = folderService.getByPath(path);
    if(ObjectUtils.isEmpty(folder)){
      throw new NotFoundException("路径不存在或以删除");
    }
    List<Folder> folderSub = folderService.getByParentId(folder.getId());
    System.out.println(folderSub);
    Map<String,Object> resultMap = new HashMap<>();
    resultMap.put("folderSub",folderService.convertToDto(folderSub));
    resultMap.put("parent",folder.getPid() == 0 ? null : folderService.getById(folder.getPid()).getPath());
    return BaseResponse.ok(resultMap);
  }

  @PutMapping("rename")
  private BaseResponse rename(
      @RequestParam(value = "name") String name,
      @RequestParam(value = "path") String path,
      @RequestParam(value = "key", defaultValue = "") String key,
      @RequestParam(value = "isFolder", defaultValue = "true") Boolean isFolder){
      if (isFolder && StringUtils.isBlank(key)){
        Folder folder = new Folder();
        folder.setName(name);
        folder.setPath(path + SquirrelConst.DELIMITER + name);
        folder.setPid(folderService.getByPath(path).getId());
        folderService.create(folder);
      } else
      if (isFolder) {
        folderService.rename(name,key);
      }

    return BaseResponse.ok("");
  }

  @DeleteMapping
  private BaseResponse delete(@RequestParam(value = "key") String key,
      @RequestParam(value = "isFolder", defaultValue = "true") Boolean isFolder){
    if (isFolder) {
      folderService.removeById(Integer.valueOf(key));
    }
    return BaseResponse.ok("");
  }

  @PostMapping("upload")
  public Attachment uploadAttachment(@RequestPart("file") MultipartFile file){
    return attachmentService.upload(file);
  }
}
