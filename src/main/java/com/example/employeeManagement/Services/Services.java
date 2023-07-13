package com.example.employeeManagement.Services;
import com.example.employeeManagement.CacheConfig.RedisClient;
import jakarta.persistence.Cacheable;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Cacheable
@Service
public class Services {

    @Autowired
    private final RedisClient redisClient;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    WebClient webClient = WebClient.create();


    public Services(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    public void checkCache() {
        if (redisTemplate.keys("*").isEmpty()){
           sendToCache();
        }
    }

    public void sendToCache() {

        System.out.println("hello");
        String url = "https://countriesnow.space/api/v0.1/countries/iso";

        Disposable disposable = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Servicesdto.class)
                .map(Servicesdto::getData)
                .flatMapMany(Flux::fromIterable)
                .collectList()
                .subscribe(countriesList -> {
                    for (Countries country : countriesList) {
                        String key = country.getName();
                        String value = country.getIso2();

                        redisClient.setKey(key,value);
                    }
                });
    }

//    public Mono<List<Countries>> callExternalApi(){
//        String url = "https://countriesnow.space/api/v0.1/countries/iso";
//
//         return webClient.get()
//                .uri(url)
//                .retrieve()
//                .bodyToMono(Servicesdto.class)
//                .map(Servicesdto::getData);
//    }

}

