package com.hewei.happy.mapper;

import com.hewei.happy.entity.FamousPhrase;
import com.hewei.happy.entity.FamousPhraseDetail;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface FamousPhraseDetailMapper extends MySqlMapper<FamousPhraseDetail> ,Mapper<FamousPhraseDetail> {
}
