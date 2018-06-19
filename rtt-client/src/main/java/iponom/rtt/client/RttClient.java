package iponom.rtt.client;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ilya.Ponomarev
 * @version 1.0 / 08.06.2018
 */
@Component
public class RttClient {

    private static final Logger all = LoggerFactory.getLogger("iponom.rtt.client.all");
    private static final Logger big = LoggerFactory.getLogger("iponom.rtt.client.big");

    @Value("${rtt.server.url}")
    private String url;

    @Value("${rtt.threshold}")
    private long threshold;

    private RestTemplate restTemplate;


    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
    }

    @Scheduled(fixedDelayString = "${rtt.delay.ms}")
    private void call() {
        long start = System.currentTimeMillis();
        restTemplate.getForObject(url, String.class);
        long time = System.currentTimeMillis() - start;
        all.debug("" + time);
        if (time > threshold && !all.isDebugEnabled()) big.debug("" + time);
    }

}
