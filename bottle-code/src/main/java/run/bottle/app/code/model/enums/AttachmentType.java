package run.bottle.app.code.model.enums;

/**
 * 存储类型
 *
 * @author LiYuanCheng
 * @date 2020-04-07 11:12
 */
public enum AttachmentType {

    /**
     * 服务器
     */
    LOCAL(0),

    /**
     * 又拍云
     */
    UPOSS(1),

    /**
     * 七牛云
     */
    QINIUOSS(2),

    /**
     * sm.ms
     */
    SMMS(3),

    /**
     * 阿里云
     */
    ALIOSS(4),

    /**
     * 百度云
     */
    BAIDUBOS(5),

    /**
     * 腾讯云
     */
    TENCENTCOS(6);

    private final Integer value;

    AttachmentType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
