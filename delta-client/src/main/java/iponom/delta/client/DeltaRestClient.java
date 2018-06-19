package iponom.delta.client;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ilya.Ponomarev
 * @version 1.0 / 08.06.2018
 */
@Component
@Slf4j
public class DeltaRestClient {

    @AllArgsConstructor
    @Getter
    static class Cont {
        Long interval;
        Long delta;
    }

    @Value("${delta.server.url}")
    private String[] urls;

    @Value("${delta.server.total-count}")
    private int totalCount;
    @Value("${delta.server.selected-count}")
    private int selectedCount;

    private RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
    }


    public Map<String, Long> calculateAllDeltas() {
        return Arrays.stream(urls).map(url -> new AbstractMap.SimpleImmutableEntry<>(url, calculateDela(url)))
                .collect(Collectors.toMap((Function<Map.Entry<String, Long>, String>) Map.Entry::getKey, AbstractMap.SimpleImmutableEntry::getValue));
    }

    private Long calculateDela(String url) {
        return IntStream.range(0, totalCount).mapToObj(i -> call(url))
                .sorted(Comparator.comparing(Cont::getInterval))
                .limit(selectedCount)
                .map(Cont::getDelta)
                .collect(Collectors.averagingLong(Long::longValue)).longValue();
    }


    private Cont call(String url) {
        long start = System.currentTimeMillis();
        String server = restTemplate.getForObject(url, String.class);
        long interval = System.currentTimeMillis() - start;
        long delta = new Long(server) - start - interval / 2;
        return new Cont(interval, delta);
    }


}
