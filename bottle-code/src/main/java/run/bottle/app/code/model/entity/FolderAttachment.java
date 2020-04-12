package run.bottle.app.code.model.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @Author: iksen
 * @Date: 2020/4/8 20:17
 */
@Data
@Entity
@Table(name = "FolderAttachment")
@ToString(callSuper = true)
public class FolderAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "folderId")
    private Integer folderId;

    @Column(name = "attachmentId")
    private Integer attachmentId;
}
