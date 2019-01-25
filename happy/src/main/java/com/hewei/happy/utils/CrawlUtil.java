package com.hewei.happy.utils;

import com.hewei.happy.constant.Constant;
import com.hewei.happy.entity.FamousPhrase;
import com.hewei.happy.entity.FamousPhraseDetail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CrawlUtil {

    /**
     * 解析名句数据
     */
    public static List<FamousPhrase> crawlFamousContent(String htmlContent){
        if(StringUtils.isEmpty(htmlContent)){
            return new ArrayList<>();
        }
        Document document = Jsoup.parse(htmlContent);
        //获取所有cont的div标签
        Elements contents = document.getElementsByClass(Constant.FAMOUS_CLASS_NAME);
        return contents.stream()
                .filter(content -> content.getElementsByTag("a").size() == 2)
                .map(content ->{
                    Elements truthContents = content.getElementsByTag("a");
                    FamousPhrase famousPhrase = new FamousPhrase();
                    famousPhrase.setCreatedDate(new Date());
                    truthContents.forEach(truthContent -> {
                        String html = truthContent.html();
                        if (html.contains(Constant.FAMOUS_CONTENT_SEPARATOR)) {
                            //来源
                            String[] params = html.split(Constant.FAMOUS_CONTENT_SEPARATOR);
                            famousPhrase.setAuthor(params[0]);
                            famousPhrase.setSource(Constant.FAMOUS_CONTENT_SEPARATOR + params[1]);
                        } else {
                            //名句内容
                            famousPhrase.setFamousPhrase(html);
                            famousPhrase.setUrl(Constant.BASE_FAMOUS_CONTENT_URL+truthContent.attr("href"));
                        }
                    });
                    return famousPhrase;
                })
                .collect(Collectors.toList());
    }

    /**
     * 诗词详细数据
     * @param htmlContent
     * @return
     */
    public static FamousPhraseDetail crawlFamousContentDetail(String htmlContent){
        FamousPhraseDetail famousPhraseDetail = new FamousPhraseDetail();
        if(StringUtils.isEmpty(htmlContent)){
            return famousPhraseDetail;
        }
        Document document = Jsoup.parse(htmlContent);
        Elements elements = document.getElementsByTag("p");
        if(elements.size() < 2){
            return famousPhraseDetail;
        }
        famousPhraseDetail.setResource(elements.get(0).html());
        famousPhraseDetail.setTransiation(elements.get(1).html());
        //只有一个标签
        Elements contents = document.getElementsByClass(Constant.FAMOUS_CONTENT_DETAIL_CLASEE_NAME);
        if(contents.size() == 1){
            famousPhraseDetail.setPoetryContent(contents.get(0).html());
        }
        return famousPhraseDetail;
    }

    public static void main(String[] args){
        //List<FamousPhrase> phrases = crawlFamousContent(HttpclientUtil.get("https://so.gushiwen.org/mingju"));
        FamousPhraseDetail famousPhraseDetail = crawlFamousContentDetail(HttpclientUtil.get("https://so.gushiwen.org/mingju/juv_46b1773abd1b.aspx"));
        int a = 1;
    }
}
