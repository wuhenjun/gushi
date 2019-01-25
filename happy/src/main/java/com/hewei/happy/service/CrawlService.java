package com.hewei.happy.service;

import com.hewei.happy.entity.FamousPhrase;
import com.hewei.happy.vo.FamousPhraseVO;

public interface CrawlService {

    /*
    爬取诗词概要数据
     */
    int crawlFamousPhraseData(String url);

    /*
    爬取诗词详细数据
     */
    int crawlFamousPhraseDetailData(String url);

    FamousPhraseVO getFamousPhraseVO();

}
