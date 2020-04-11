package run.bottle.app.code.contorller.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import run.bottle.app.code.exception.NotFoundException;
import run.bottle.app.code.model.dto.FolderOrFileDTO;
import run.bottle.app.code.model.entity.Attachment;
import run.bottle.app.code.model.entity.Folder;
import run.bottle.app.code.model.entity.FolderAttachment;
import run.bottle.app.code.model.support.BaseResponse;
import run.bottle.app.code.model.support.BottleConst;
import run.bottle.app.code.service.AttachmentService;
import run.bottle.app.code.service.FolderAttachmentService;
import run.bottle.app.code.service.FolderService;
import run.bottle.app.upload.model.UploadResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

  private final FolderAttachmentService folderAttachmentService;

  public FolderController(FolderService folderService,
                          AttachmentService attachmentService, FolderAttachmentService folderAttachmentService) {
    this.folderService = folderService;
    this.attachmentService = attachmentService;
    this.folderAttachmentService = folderAttachmentService;
  }

  @GetMapping
  private BaseResponse getFolder(@RequestParam(defaultValue = "root") String path){
    System.out.println(path);
    Folder folder = folderService.getByPath(path);
    if(ObjectUtils.isEmpty(folder)){
      throw new NotFoundException("路径不存在或以删除");
    }
    List<Folder> folderSub = folderService.getByParentId(folder.getId());
    List<FolderOrFileDTO> folderDTOSub = folderService.convertToDto(folderSub);
    List<FolderAttachment> folderAttachments = folderAttachmentService.getByFolderId(folder.getId());
    Set<Integer> attAIds = folderAttachments.stream().map(FolderAttachment::getAttachmentId).collect(Collectors.toSet());
    List<Attachment> attachments = attachmentService.listAllByIds(attAIds);
    Map<String,Object> resultMap = new HashMap<>();
    resultMap.put("folderSub",convertToDtoList(folderDTOSub,attachments,folder));
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
        folder.setPath(path + BottleConst.DELIMITER + name);
        folder.setPid(folderService.getByPath(path).getId());
        folderService.create(folder);
      } else
      if (isFolder) {
        folderService.rename(name,key);
      } else {
        String newPath = path + BottleConst.DELIMITER + name;
        Attachment attachment = attachmentService.getByKey(key);
        UploadResult uploadResult = attachmentService.move(attachment.getPath(),newPath);
        if (uploadResult != null){
          attachment.setName(name);
          attachment.setPath(uploadResult.getFilePath());
          attachment.setSuffix(uploadResult.getSuffix());
          attachment.setMediaType(uploadResult.getMediaType().toString());
          attachmentService.update(attachment);
        }
      }

    return BaseResponse.ok("");
  }

  @DeleteMapping
  private BaseResponse delete(@RequestParam(value = "key") String key,
      @RequestParam(value = "isFolder", defaultValue = "true") Boolean isFolder){
    if (isFolder) {
      folderService.removeById(Integer.valueOf(key));
    } else {
      attachmentService.removePermanently(key);
    }
    return BaseResponse.ok("");
  }

  @PostMapping("upload")
  public Attachment uploadAttachment(@RequestPart("file") MultipartFile file, @RequestParam(defaultValue = "root") String path){
    Folder folder = folderService.getByPath(path);
    Attachment attachment = attachmentService.upload(file,path);
    FolderAttachment folderAttachment = new FolderAttachment();
    folderAttachment.setAttachmentId(attachment.getId());
    folderAttachment.setFolderId(folder.getId());
    folderAttachmentService.create(folderAttachment);
    return attachment;
  }

  public List<FolderOrFileDTO> convertToDtoList(List<FolderOrFileDTO> folderOrFileDTOS,List<Attachment> attachments,Folder folder) {
    attachments.forEach(attachment -> {
      FolderOrFileDTO folderOrFileDTO = new FolderOrFileDTO();
      folderOrFileDTO.setId(attachment.getId());
      folderOrFileDTO.setKey(attachment.getFileKey());
      folderOrFileDTO.setName(attachment.getName());
      folderOrFileDTO.setPath(BottleConst.Squirrel_PATH + BottleConst.DELIMITER + attachment.getPath());
      folderOrFileDTO.setMediaType(attachment.getMediaType());
      folderOrFileDTO.setIsFolder(false);
      folderOrFileDTO.setParent(folder.getPid());
      folderOrFileDTOS.add(folderOrFileDTO);
    });
    return folderOrFileDTOS;
  }
}
