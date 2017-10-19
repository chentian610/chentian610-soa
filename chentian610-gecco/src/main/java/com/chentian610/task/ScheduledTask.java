package com.chentian610.task;



        import com.amazon.AllSortAmazon;
        import com.chentian610.service.KeywordService;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.scheduling.annotation.Scheduled;
        import org.springframework.stereotype.Component;

        import javax.annotation.Resource;
        import java.util.List;


@Component
public class ScheduledTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
    @Resource
    private KeywordService keywordService;

    @Scheduled(cron="0 0 0/2 * * ?")
    public void executeFileDownLoadTask() {

        List<String> brandList = keywordService.getAllBrands();
        for (String brand:brandList) {
            List<String> keywordList = keywordService.getKeywordsByBrand(brand);
//            AllSortAmazon.getAmazonRankByKeyWords
        }

        // 间隔2分钟,执行工单上传任务
        Thread current = Thread.currentThread();
        System.out.println("定时任务1:"+current.getId());
        logger.info("ScheduledTest.executeFileDownLoadTask 定时任务1:"+current.getId()+ ",name:"+current.getName());
    }



}
