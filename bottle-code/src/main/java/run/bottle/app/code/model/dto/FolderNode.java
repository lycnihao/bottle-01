package run.bottle.app.code.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 文件夹 tree node 结构
 *
 * @Author: liyuancheng
 * @Date: 2020/4/14 22:01
 */
@Data
public class FolderNode {
    private String key;
    private String name;
    private String path;
    private Boolean disabled;
    private List<FolderNode> child;
}
