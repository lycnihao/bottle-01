package com.couldr.squirrel.code.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 文件夹/文件
 *
 * @author LiYuanCheng
 * @date 2020-04-07 11:21
 */
@Data
@ToString
public class FolderOrFileDTO {
  private String key;
  private String name;
  private String suffix;
  private Long size;
  private Boolean isFolder;
}