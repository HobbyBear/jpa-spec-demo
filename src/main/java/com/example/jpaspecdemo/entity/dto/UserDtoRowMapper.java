package com.example.jpaspecdemo.entity.dto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDtoRowMapper implements RowMapper<UserDto> {
    @Override
    public UserDto mapRow(ResultSet resultSet, int i) throws SQLException {
        return UserDto
                .builder()
                .addressName(resultSet.getString("addressName"))
                .roleName(resultSet.getString("roleName"))
                .userId(resultSet.getInt("userId"))
                .username(resultSet.getString("username"))
                .build();
    }
}
