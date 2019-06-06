package com.example.jpaspecdemo.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class JdbcUtil {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 返回单个dto
     * @param sql 查询sql
     * @param queryArgs 查询参数
     * @param rowMapper dto mapper
     * @param <T> dto
     * @return dto
     */
    public <T>T getCustomerDto(String sql, Object[] queryArgs, RowMapper<T> rowMapper) {
        log.info(sql);
        return jdbcTemplate.queryForObject(sql, queryArgs, rowMapper);
    }

    /**
     * 返回dto列表
     * @param sql 查询sql
     * @param queryArgs 查询参数
     * @param rowMapper dto mapper
     * @param <T> dto
     * @return dto list
     * */
    public <T> List<T> getCustomerDtoList(String sql, Object[] queryArgs, RowMapper<T> rowMapper) {
        log.info(sql);
        return jdbcTemplate.query(sql, queryArgs, rowMapper);
    }

    /**
     * 返回分页对象
     * @param sql 查询sql
     * @param queryArgs 查询参数
     * @param rowMapper dto mapper
     * @param countSql 总量sql
     * @param countArgs 总量参数
     * @param page 当前页
     * @param size 每页大小
     * @param <T> dto
     * @return 分页对象
     */
    public <T> PageBean<T> getCustomerPageDto(String sql, Object[] queryArgs, RowMapper<T> rowMapper,
                                          String countSql, Object[] countArgs, int page, int size) {

        if (page <= 0){
            throw new RuntimeException("当前页数必须大于1");
        }
        if (size <= 0){
            throw new RuntimeException("每页大小必须大于1");
        }
        //总共数量
        int totalSize = 0;
        try {
            totalSize = jdbcTemplate.queryForObject(countSql,countArgs,Integer.class);
        } catch (EmptyResultDataAccessException e) {   //queryForObject 查询不到数据会抛出 EmptyResultDataAccessException 异常
            totalSize = 0;
        }
        if (totalSize == 0){
            return PageBean.<T>builder()
                    .content(new ArrayList<>())
                    .page(1)
                    .size(0)
                    .totalPage(0)
                    .totalSize(0)
                    .build();
        }
        //总页数
        int totalPage = totalSize%size == 0 ? totalSize/size : totalSize/size + 1;
        //开始位置
        int offset = (page -1)*size;
        //结束位置
        int limit = offset + size;
        sql = sql +" limit "+ limit +" offset "+offset;
        log.info(sql);
        List<T> content = jdbcTemplate.query(sql,queryArgs,rowMapper);
        return PageBean.<T>builder()
                .content(content)
                .totalSize(totalSize)
                .totalPage(totalPage)
                .page(page)
                .size(size)
                .build();
    }

}
