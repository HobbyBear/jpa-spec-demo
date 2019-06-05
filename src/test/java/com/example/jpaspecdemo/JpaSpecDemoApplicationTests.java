package com.example.jpaspecdemo;

import com.example.jpaspecdemo.dao.UserInfoDao;
import com.example.jpaspecdemo.entity.dto.UserDto;
import com.example.jpaspecdemo.service.UserService;
import com.example.jpaspecdemo.utils.PageBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaSpecDemoApplicationTests {

    @Autowired
    private UserInfoDao userInfoDao;


    @Autowired
    private UserService userService;

    /**
     * hql 语句多表连接查询分页
     */
    @Test
    public void testGetDto2() {
        PageRequest pageRequest = PageRequest.of(0,1);
        Page<UserDto> userDtoPage = userInfoDao.getDto2(pageRequest);
        System.out.println("总页数: "+ userDtoPage.getTotalPages()+" 查询大小为："+ userDtoPage.getSize()+" list："
                +userDtoPage.getContent());

    }

    /**
     * 本地查询分页
     */
    @Test
    public void testGetDto() {
        PageRequest pageRequest = PageRequest.of(0,1);
        Page<UserDto> userDtoPage = userInfoDao.getDto2(pageRequest);
        System.out.println("总页数: "+ userDtoPage.getTotalPages()+" 查询大小为："+ userDtoPage.getSize()+" list："
                +userDtoPage.getContent());

    }

    @Test
    public void testJdbcTemp(){
        PageBean<UserDto> pageBean = userService.getUserDto();
        System.out.println(pageBean.getContent());
        System.out.println("总页数：" + pageBean.getTotalPage()+" 总大小："+pageBean.getTotalSize() +" 内容大小: "+ pageBean.getElementTotalSize());
    }


}
