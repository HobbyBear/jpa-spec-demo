package com.example.jpaspecdemo.service;

import com.example.jpaspecdemo.entity.dto.UserDto;
import com.example.jpaspecdemo.entity.dto.UserDtoRowMapper;
import com.example.jpaspecdemo.utils.JdbcUtil;
import com.example.jpaspecdemo.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private JdbcUtil jdbcUtil;


    public PageBean<UserDto> getUserDto() {
        String sql =
                "select t.id as userId, t.username as username, a.address_name as addressName, r.role_name as roleName " +
                        "from user_info t,address a,role r " +
                        "where t.address_id = a.id and t.role_id = r.id and t.create_time < ?";
        String countSql =
                "select count(*) " +
                        "from user_info t,address a,role r " +
                        "where t.address_id = a.id and t.role_id = r.id and t.create_time < ?";
        ;
        return jdbcUtil.getCustomerPageDto(sql, new Object[]{LocalDateTime.now()}, new UserDtoRowMapper(),
                countSql,new Object[]{LocalDateTime.now()},
                1,3);
    }
}
