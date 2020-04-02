package com.couldr.squirrel.code.model.support;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.MediaType;

/**
 * Upload result dto.
 *
 * @author iksen
 * @date 2020/4/2 19:47
 */
@Data
@ToString
public class UploadResult {

    private String filename;

    private String filePath;

    private String key;

    private String suffix;

    private MediaType mediaType;

    private Long size;
}
