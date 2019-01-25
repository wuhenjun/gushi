package com.hewei.happy.service.impl;

import com.hewei.happy.config.RedisUtil;
import com.hewei.happy.entity.FamousPhrase;
import com.hewei.happy.entity.FamousPhraseDetail;
import com.hewei.happy.mapper.FamousPhraseDetailMapper;
import com.hewei.happy.mapper.FamousPhraseMapper;
import com.hewei.happy.service.CrawlService;
import com.hewei.happy.utils.CrawlUtil;
import com.hewei.happy.vo.FamousPhraseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Random;

@Service
public class CrawlServiceImpl implements CrawlService {

    @Autowired
    private FamousPhraseMapper famousPhraseMapper;

    @Autowired
    private FamousPhraseDetailMapper famousPhraseDetailMapper;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public int crawlFamousPhraseData(String url) {
        return famousPhraseMapper.insertList(CrawlUtil.crawlFamousContent(url));
    }

    @Override
    public int crawlFamousPhraseDetailData(String url) {
        return famousPhraseDetailMapper.insert(CrawlUtil.crawlFamousContentDetail(url));
    }

    @Override
    public FamousPhraseVO getFamousPhraseVO() {
        FamousPhraseVO famousPhraseVO = (FamousPhraseVO) redisUtil.get("love");
        if(Objects.isNull(famousPhraseVO)){
            //去数据库中查询，并且设置缓存到redis
            //查询最大值
            int nums = famousPhraseMapper.selectCount(null);
            //随机选取一个ID
            famousPhraseVO = famousPhraseMapper.getDataById(new Random().nextInt(nums) + 1);
            //插入redis
            redisUtil.set("love",famousPhraseVO,(long)60*60*24);
        }
        return famousPhraseVO;
    }
}
