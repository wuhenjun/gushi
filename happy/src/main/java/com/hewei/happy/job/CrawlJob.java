package com.hewei.happy.job;

import com.hewei.happy.config.RedisUtil;
import com.hewei.happy.constant.Constant;
import com.hewei.happy.entity.CrawlHistory;
import com.hewei.happy.entity.FamousPhrase;
import com.hewei.happy.entity.FamousPhraseDetail;
import com.hewei.happy.mapper.CrawlHistoryMapper;
import com.hewei.happy.mapper.FamousPhraseDetailMapper;
import com.hewei.happy.mapper.FamousPhraseMapper;
import com.hewei.happy.utils.CrawlUtil;
import com.hewei.happy.utils.HttpclientUtil;
import com.hewei.happy.vo.FamousPhraseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Component
public class CrawlJob {

    @Autowired
    private CrawlHistoryMapper crawlHistoryMapper;

    @Autowired
    private FamousPhraseMapper famousPhraseMapper;

    @Autowired
    private FamousPhraseDetailMapper famousPhraseDetailMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void crawlJob(){
        //查询历史表到哪一个URL了，不存在则使用初始化url
        String url = "";
        CrawlHistory paramCrawlHistory = new CrawlHistory();
        paramCrawlHistory.setResult("N");
        paramCrawlHistory.setType(Constant.FAMOUS_CONTENT_TYPE);
        CrawlHistory crawlHistory = crawlHistoryMapper.selectOne(paramCrawlHistory);
        if(Objects.isNull(crawlHistory)){
            //初始化url
            url = Constant.FAMOUS_CONTENT_URL;
        }else{
            url = crawlHistory.getUrl();
        }
        //根据URL获取数据
        List<FamousPhrase> famousPhrases = CrawlUtil.crawlFamousContent(HttpclientUtil.get(url));
        //循环插入
        for (FamousPhrase famousPhrase : famousPhrases) {
            //插入概要数据
            famousPhraseMapper.insertGetKey(famousPhrase);
            //famousPhraseMapper.insert(famousPhrase);
            //获取url查询详情数据
            FamousPhraseDetail famousPhraseDetail = CrawlUtil.crawlFamousContentDetail(HttpclientUtil.get(famousPhrase.getUrl()));
            famousPhraseDetail.setFamousPhraseId(famousPhrase.getId());
            famousPhraseDetailMapper.insert(famousPhraseDetail);
        }
        //修改历史表数据
        if(Objects.isNull(crawlHistory)){
            //插入一条完成的，以及一条待执行
            CrawlHistory crawlHistory1 = new CrawlHistory();
            crawlHistory1.setCreatedDate(new Date());
            crawlHistory1.setUpdatedDate(new Date());
            crawlHistory1.setUrl(url);
            crawlHistory1.setType(Constant.FAMOUS_CONTENT_TYPE);
            crawlHistory1.setResult("Y");
            crawlHistoryMapper.insert(crawlHistory1);

            crawlHistory1.setUrl(Constant.FAMOUS_CONTENT_URL + Constant.FAMOUS_CONTENT_URL_ADDRESS);
            crawlHistory1.setResult("N");
            crawlHistoryMapper.insert(crawlHistory1);
        }else{
            //g更新当前，并且新生成一条数据
            crawlHistory.setUpdatedDate(new Date());
            crawlHistory.setResult("Y");
            crawlHistoryMapper.updateByPrimaryKey(crawlHistory);

            CrawlHistory crawlHistory1 = new CrawlHistory();
            crawlHistory1.setCreatedDate(new Date());
            crawlHistory1.setUpdatedDate(new Date());
            url = url.substring(0,url.length() - 6);
            Integer page = Integer.parseInt(url.substring(url.length() - 1,url.length()));
            page += 1;
            crawlHistory1.setUrl(url.substring(0,url.length() - 1) + page + Constant.FAMOUS_CONTENT_URL_ADDRESS_HHH);
            crawlHistory1.setType(Constant.FAMOUS_CONTENT_TYPE);
            crawlHistory1.setResult("N");
            crawlHistoryMapper.insert(crawlHistory1);
        }
        //查询最大值
        int nums = famousPhraseMapper.selectCount(null);
        //随机选取一个ID
        FamousPhraseVO famousPhraseVO = famousPhraseMapper.getDataById(new Random().nextInt(nums) + 1);
        //插入redis
        redisUtil.set("love",famousPhraseVO,(long)60*60*24);
    }
}
