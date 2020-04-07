package com.couldr.squirrel.code.service;

import com.couldr.squirrel.code.model.dto.FolderOrFileDTO;
import com.couldr.squirrel.code.model.entity.Folder;
import com.couldr.squirrel.code.service.base.CrudService;
import java.util.ArrayList;
import java.util.List;

public interface FolderService extends CrudService<Folder, Integer> {

  List<FolderOrFileDTO> getByName(List<String> names);

  List<String> getParentFolder(Integer id);

  List<String> getSubFolder(Integer id,List<String> names);
}
