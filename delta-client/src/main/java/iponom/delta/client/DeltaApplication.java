package iponom.delta.client;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DeltaApplication implements ApplicationRunner {

    private final DeltaRestClient deltaRestClient;

    @Autowired
    public DeltaApplication(DeltaRestClient deltaRestClient) {
        this.deltaRestClient = deltaRestClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(DeltaApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) {
        Map<String, Long> deltas = deltaRestClient.calculateAllDeltas();
        deltas.keySet().forEach(url -> log.info(url + " = " + deltas.get(url)));

    }
}
