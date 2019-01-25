package com.hewei.happy.controller;

import com.hewei.happy.service.CrawlService;
import com.hewei.happy.utils.ResultInfo;
import com.hewei.happy.vo.FamousPhraseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crawl")
public class CrawlController {

    @Autowired
    private CrawlService crawlService;

    @RequestMapping(value = "/demo",method = RequestMethod.GET)
    public ResultInfo<FamousPhraseVO> crawl(){
        return ResultInfo.build(1,"success",crawlService.getFamousPhraseVO());
    }

}
