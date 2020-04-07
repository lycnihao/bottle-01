package com.couldr.squirrel.code.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 文件夹
 *
 * @author LiYuanCheng
 * @date 2020-04-07 10:49
 */
@Data
@Entity
@Table(name = "folder")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Folder extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  /**
   * 文件夹名称
   */
  @Column(name = "name", columnDefinition = "varchar(50) not null")
  private String name;

  /**
   * 父级Id
   */
  @Column(name = "pid")
  private Integer pid;
}