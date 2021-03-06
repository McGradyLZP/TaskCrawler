package task;

import crawler.ChromeDrive;
import interfaces.dao.MusicListDao;
import model.HotMusic;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
/**
 * 定时任务bean
 * @author zhipeng
 * @Date: Created in  2018-01-15 
 * @name TaskJob.java
 * @info TODO
 */
@Component
public class TaskJob {
    @Resource(name = "musicListDao")
    private MusicListDao MusicList;

    /**
     * 0 0 0 1/1 * ?
     */
    @Scheduled(cron = "3 * * * * ?")
    public void aTask() {
//        List<HotMusic> list = new Phantomjs().crawlerHtml("http://music.163.com/#/discover/toplist?id=3778678");
        List<HotMusic> list = new ChromeDrive().crawlerHtml("http://music.163.com/#/discover/toplist?id=3778678");
        if(list!=null&&list.size()>0){
            int del = this.MusicList.deleteHotMusic();
            System.out.println(del);
            int save = this.MusicList.saveHotMusicList(list);
            System.out.println(save);
            System.out.println("----------------end-------------------------");
        }
    }

}
