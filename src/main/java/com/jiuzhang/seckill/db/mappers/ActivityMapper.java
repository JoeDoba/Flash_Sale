package com.jiuzhang.seckill.db.mappers;

import com.jiuzhang.seckill.db.po.Activity;

public interface ActivityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);
}