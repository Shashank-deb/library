package com.shashank.library.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfiguration {

    /*
     *  Caching-> Redis(
     *
     * 1.Adding the dependency .
     * 2.Driver for redis comes inbuilt in the dependency .
     *    Two types of drivers
     *   ->Lettuce(By default driver)
     *   ->Jedis
     *
     * 3.Create a connection bean .
     * 4.Create a template to access the data .
     *
     *
     * )
     *  Cache capturing the full response which can contain the header and the payload data which is coming in the Server Response .
     *  Helpful when user hit the response the second time .
     *  Cache is stateless.
     *  Stateless having a nature of not knowing whether the  number of request is first or second  this is the behaviour of the application which is stateless .
     *  If value is not found then do to DB ,Otherwise the caching is perform by the Redis configuration in the Config classes .
     *  Everytime we have to search not know about the state of searching that's why we are using Caching  used only for the latency .
     *  Type of latency (Time taken to perform the operations )
     * ------------------
     *  1.Network Call
     *  2.Search DB
     * ------------------
     *  When the latency decrease the throughout of the application is increase always reciprocal to each other means number of request in the application is increase .
     *  If we have to store only 10 movies in cache the crowd is 70% and remaining crowd is 30%  Then latency is increase when movie is not found then we have to again go to the database and application is messed up .
     *
     *
     *
     *  Basic Architecture of Microservices
     *
     *-----------------------------------------------------------------------------------------------------------------------------------------------
     *  1 million user -> Amazon (Operations->search product(1million),Add to cart(0.4 million), order(0.2 million) , payments(0.5 million))
     *  Increase the response time so there is one more instance is coming
     *  So there is a use  of Load  Balancer using the Round Robin Format  between the two instances of backend servers  .
     *
     *
     * Then there is also one good idea for service division
     *
     * Load Balancer->One service is only for searching the product -> And remaining is for the other service applications .
     *
     *
     * Load Balancer->Amazon Product Search -> one instance of cart -> one instance of order ->one instance of payments
     * Anyone is talking to each other in round robbin manner .
     * In this architecture there is a different service where the cache is there using redis (distributed systems).
     * There is a single Service for the Database also .
     *
     * Latency also increase and throughput decrease  when multiple hops network are increase so the latency is also increase in the distributed systems but
     * we can scale up the applications using individuals services .
     *
     *
     *  The datatypes of redis storage is also in form of key values pair where the key is always string
     *
     * ----------------------------------------------------------------------------------------------------------------------------------------------
     *
     *  Redis Design Support two structures:-
     *  Standalone (REDIS STRUCTURE)
     *  Cluster
     *
     * */

    @Bean
    public LettuceConnectionFactory getLettuceConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("redis-10126.c264.ap-south-1-1.ec2.redns.redis-cloud.com", 10126);
        redisStandaloneConfiguration.setPassword("JnrIrCWi2FMFBofDnqkBKcT0fvxCibok");
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        return lettuceConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> getRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//         redisTemplate.setValueSerializer(new StringRedisSerializer());

//        Use Jackson2JsonRedisSerializer for hash values
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return redisTemplate;
    }

//    public  class XmlSerialize implements RedisSerializer<Object> {
////override the methods of RedisSerializer to implement your own logic and pass this implementation to redistemplate
//
//        @Override
//        public byte[] serialize(Object value) throws SerializationException {
//            return new byte[0];
//        }
//
//        @Override
//        public Object deserialize(byte[] bytes) throws SerializationException {
//            return null;
//        }
//
//        @Override
//        public boolean canSerialize(Class<?> type) {
//            return RedisSerializer.super.canSerialize(type);
//        }
//
//        @Override
//        public Class<?> getTargetType() {
//            return RedisSerializer.super.getTargetType();
//        }
//    }
}
