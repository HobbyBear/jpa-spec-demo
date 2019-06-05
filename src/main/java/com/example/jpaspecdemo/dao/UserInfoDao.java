package com.example.jpaspecdemo.dao;

import com.example.jpaspecdemo.entity.UserInfo;
import com.example.jpaspecdemo.entity.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


public interface UserInfoDao extends JpaRepository<UserInfo,Integer>,JpaSpecificationExecutor<UserInfo> {



    @Query(value = "select new com.example.jpaspecdemo.entity.dto.UserDto(u.id,u.username,a.addressName,r.roleName) " +
            "from UserInfo u, Role r,Address a where u.addressId=a.id and u.roleId = r.id " +
            " order by u.id desc ",
            countQuery = "select count(u.id) " +
                    "from UserInfo u, Role r,Address a where u.addressId=a.id and u.roleId = r.id"
    )
    Page<UserDto> getDto2(Pageable pageable);
}
