package com.hewei.happy.mapper;

import com.hewei.happy.entity.CrawlHistory;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface CrawlHistoryMapper extends MySqlMapper<CrawlHistory>,Mapper<CrawlHistory> {
}
