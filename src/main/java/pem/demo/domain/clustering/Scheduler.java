package pem.demo.domain.clustering;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pem.demo.domain.clustering.service.ClusteringService;

import javax.annotation.PostConstruct;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class Scheduler {

    @Autowired
    ClusteringService clusteringService;

//    @PostConstruct
    private void autoSaveIntegratedJson() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("json 한번 스캔하여 저장하였습니다!");
                clusteringService.autoGetJsonFile();
            }
        };
        timer.schedule(timerTask, 1000, 86400000);
    }
}
