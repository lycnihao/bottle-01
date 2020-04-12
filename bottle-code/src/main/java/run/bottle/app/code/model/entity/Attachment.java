package run.bottle.app.code.model.entity;

import run.bottle.app.code.model.enums.AttachmentType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

/**
 * 附件实体类
 *
 * @author LiYuanCheng
 * @date 2020-04-07 10:49
 */
@Data
@Entity
@Table(name = "attachments")
@ToString
@EqualsAndHashCode(callSuper = true)
public class Attachment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id")
    private Integer id;

    /**
     * 附件名称
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 访问路径
     */
    @Column(name = "path", length = 1023, nullable = false)
    private String path;

    /**
     * 文件KEY
     */
    @Column(name = "file_key", length = 2047)
    private String fileKey;

    /**
     * 附件类型
     */
    @Column(name = "media_type", length = 127, nullable = false)
    private String mediaType;

    /**
     * 附件后缀，如png, zip, mp4, jpge
     */
    @Column(name = "suffix", length = 50)
    private String suffix;

    /**
     * 附件大小
     */
    @Column(name = "size", nullable = false)
    private Long size;

    /**
     * 附件上传类型
     */
    @Column(name = "type")
    @ColumnDefault("0")
    private AttachmentType type;

    @Override
    public void prePersist() {
        super.prePersist();

        if (fileKey == null) {
            fileKey = "";
        }

        if (type == null) {
            type = AttachmentType.LOCAL;
        }
    }
}
