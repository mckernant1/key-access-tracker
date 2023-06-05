package com.mckernant1.keytracker.store.lru

import com.mckernant1.commons.caching.LRUCache
import com.mckernant1.keytracker.store.KeyStore
import java.util.Collections

class LRUKeyStore(
    maxSize: Int
) : KeyStore {

    private val lruCache =
        Collections.synchronizedMap(LRUCache<String, Long>(maxSize))

    override fun incrementKey(key: String) {
        incrementKeyBy(key, 1)
    }

    override fun incrementKeyBy(key: String, toIncBy: Int) {
        lruCache.compute(key) { _, value -> (value ?: 0) + toIncBy }
    }

    override fun dump(): Map<String, Long> = lruCache
}
