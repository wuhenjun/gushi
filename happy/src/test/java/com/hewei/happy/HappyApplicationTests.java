package com.hewei.happy;

import com.hewei.happy.job.CrawlJob;
import com.hewei.happy.service.CrawlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HappyApplicationTests {

	@Autowired
	private CrawlJob crawlJob;

	@Autowired
	private CrawlService crawlService;

	@Test
	public void contextLoads() {
		crawlJob.crawlJob();
	}

	@Test
	public void test() {

	}

}

