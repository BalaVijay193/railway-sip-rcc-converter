package com.railway.sip.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache Configuration for Yard Graphs, Routes, RCC, and Conflicts
 */
@Slf4j
@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {
    
    /**
     * In-memory cache manager for frequently accessed data
     */
    @Bean(name = "cacheManager")
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(
            "yards",              // Yard graphs
            "routes",             // Enumerated routes
            "rcc",                // Generated RCC
            "conflicts",          // Detected conflicts
            "overlaps",           // Overlap information
            "isolationPoints"     // Isolation point information
        );
    }
    
    /**
     * Custom cache for yard graphs with TTL support
     */
    @Bean(name = "yardGraphCache")
    public YardGraphCacheManager yardGraphCache() {
        return new YardGraphCacheManager();
    }
    
    /**
     * Custom cache for routes
     */
    @Bean(name = "routeCache")
    public RouteCacheManager routeCache() {
        return new RouteCacheManager();
    }
    
    /**
     * Custom cache for RCC
     */
    @Bean(name = "rccCache")
    public RccCacheManager rccCache() {
        return new RccCacheManager();
    }
    
    /**
     * Custom cache for conflicts
     */
    @Bean(name = "conflictCache")
    public ConflictCacheManager conflictCache() {
        return new ConflictCacheManager();
    }
    
    /**
     * Clear expired cache entries every 1 hour
     */
    @Scheduled(fixedRate = 3600000) // 1 hour
    public void clearExpiredCaches() {
        log.info("Clearing expired cache entries");
        yardGraphCache().evictExpired();
        routeCache().evictExpired();
        rccCache().evictExpired();
        conflictCache().evictExpired();
    }
    
    /**
     * Yard Graph Cache with TTL (2 hours)
     */
    @Slf4j
    public static class YardGraphCacheManager {
        private static final long TTL = 7200000; // 2 hours
        private final Map<String, CacheEntry<Object>> cache = new ConcurrentHashMap<>();
        
        public void put(String key, Object value) {
            cache.put(key, new CacheEntry<>(value, System.currentTimeMillis() + TTL));
            log.debug("Cached yard graph: {}", key);
        }
        
        public Object get(String key) {
            CacheEntry<Object> entry = cache.get(key);
            if (entry != null && !entry.isExpired()) {
                return entry.getValue();
            } else if (entry != null) {
                cache.remove(key);
            }
            return null;
        }
        
        public void evict(String key) {
            cache.remove(key);
        }
        
        public void evictExpired() {
            cache.entrySet().removeIf(e -> e.getValue().isExpired());
            log.info("Evicted expired yard graph entries");
        }
        
        public Map<String, CacheEntry<Object>> getAll() {
            return new HashMap<>(cache);
        }
        
        static class CacheEntry<T> {
            private final T value;
            private final long expiryTime;
            
            CacheEntry(T value, long expiryTime) {
                this.value = value;
                this.expiryTime = expiryTime;
            }
            
            T getValue() {
                return value;
            }
            
            boolean isExpired() {
                return System.currentTimeMillis() > expiryTime;
            }
        }
    }
    
    /**
     * Route Cache with TTL (1 hour)
     */
    @Slf4j
    public static class RouteCacheManager {
        private static final long TTL = 3600000; // 1 hour
        private final Map<String, CacheEntry<Object>> cache = new ConcurrentHashMap<>();
        
        public void put(String key, Object value) {
            cache.put(key, new CacheEntry<>(value, System.currentTimeMillis() + TTL));
            log.debug("Cached routes: {}", key);
        }
        
        public Object get(String key) {
            CacheEntry<Object> entry = cache.get(key);
            if (entry != null && !entry.isExpired()) {
                return entry.getValue();
            } else if (entry != null) {
                cache.remove(key);
            }
            return null;
        }
        
        public void evict(String key) {
            cache.remove(key);
        }
        
        public void evictExpired() {
            cache.entrySet().removeIf(e -> e.getValue().isExpired());
            log.info("Evicted expired route entries");
        }
        
        static class CacheEntry<T> {
            private final T value;
            private final long expiryTime;
            
            CacheEntry(T value, long expiryTime) {
                this.value = value;
                this.expiryTime = expiryTime;
            }
            
            T getValue() {
                return value;
            }
            
            boolean isExpired() {
                return System.currentTimeMillis() > expiryTime;
            }
        }
    }
    
    /**
     * RCC Cache with TTL (3 hours)
     */
    @Slf4j
    public static class RccCacheManager {
        private static final long TTL = 10800000; // 3 hours
        private final Map<String, CacheEntry<Object>> cache = new ConcurrentHashMap<>();
        
        public void put(String key, Object value) {
            cache.put(key, new CacheEntry<>(value, System.currentTimeMillis() + TTL));
            log.debug("Cached RCC: {}", key);
        }
        
        public Object get(String key) {
            CacheEntry<Object> entry = cache.get(key);
            if (entry != null && !entry.isExpired()) {
                return entry.getValue();
            } else if (entry != null) {
                cache.remove(key);
            }
            return null;
        }
        
        public void evict(String key) {
            cache.remove(key);
        }
        
        public void evictExpired() {
            cache.entrySet().removeIf(e -> e.getValue().isExpired());
            log.info("Evicted expired RCC entries");
        }
        
        static class CacheEntry<T> {
            private final T value;
            private final long expiryTime;
            
            CacheEntry(T value, long expiryTime) {
                this.value = value;
                this.expiryTime = expiryTime;
            }
            
            T getValue() {
                return value;
            }
            
            boolean isExpired() {
                return System.currentTimeMillis() > expiryTime;
            }
        }
    }
    
    /**
     * Conflict Cache with TTL (1 hour)
     */
    @Slf4j
    public static class ConflictCacheManager {
        private static final long TTL = 3600000; // 1 hour
        private final Map<String, CacheEntry<Object>> cache = new ConcurrentHashMap<>();
        
        public void put(String key, Object value) {
            cache.put(key, new CacheEntry<>(value, System.currentTimeMillis() + TTL));
            log.debug("Cached conflicts: {}", key);
        }
        
        public Object get(String key) {
            CacheEntry<Object> entry = cache.get(key);
            if (entry != null && !entry.isExpired()) {
                return entry.getValue();
            } else if (entry != null) {
                cache.remove(key);
            }
            return null;
        }
        
        public void evict(String key) {
            cache.remove(key);
        }
        
        public void evictExpired() {
            cache.entrySet().removeIf(e -> e.getValue().isExpired());
            log.info("Evicted expired conflict entries");
        }
        
        static class CacheEntry<T> {
            private final T value;
            private final long expiryTime;
            
            CacheEntry(T value, long expiryTime) {
                this.value = value;
                this.expiryTime = expiryTime;
            }
            
            T getValue() {
                return value;
            }
            
            boolean isExpired() {
                return System.currentTimeMillis() > expiryTime;
            }
        }
    }
}
