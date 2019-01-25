package com.hewei.happy.mapper;

import com.hewei.happy.entity.FamousPhrase;
import com.hewei.happy.vo.FamousPhraseVO;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface FamousPhraseMapper extends MySqlMapper<FamousPhrase>,Mapper<FamousPhrase> {
    int insertGetKey(FamousPhrase famousPhrase);

    FamousPhraseVO getDataById(Integer id);
}
