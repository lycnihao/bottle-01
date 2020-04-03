package com.couldr.squirrel.code.repository.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;

/**
 * 接口常用方法。
 * @param <DOMAIN> Domain Type
 * @param <ID> Id type
 */
@NoRepositoryBean
public interface BaseRepository<DOMAIN, ID> extends JpaRepository<DOMAIN, ID> {

    /**
     * 根据Ids查询所有
     *
     * @param ids  主键集合
     * @param sort 排序
     * @return 集合
     */
    @NonNull
    List<DOMAIN> findAllByIdIn(@NonNull Collection<ID> ids, @NonNull Sort sort);

    /**
     * 根据Ids查询所有
     *
     * @param ids      主键集合
     * @param pageable 分页
     * @return 集合
     */
    @NonNull
    Page<DOMAIN> findAllByIdIn(@NonNull Collection<ID> ids, @NonNull Pageable pageable);

    /**
     * 批量删除
     *
     * @param ids 主键集合
     * @return 受影响行数
     */
    long deleteByIdIn(@NonNull Collection<ID> ids);

}
