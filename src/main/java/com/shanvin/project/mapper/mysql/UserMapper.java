package com.shanvin.project.mapper.mysql;

import com.shanvin.project.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    List<User> listUser();

}
