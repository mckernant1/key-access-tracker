package com.mckernant1.keytracker.store.lru

import com.mckernant1.keytracker.store.lru.LRUKeyStore
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class LRUKeyStoreTest {

    @Test
    fun testIncrementKey() {
        val keyStore = LRUKeyStore(3)
        keyStore.incrementKey("key1")
        val dump = keyStore.dump()
        assertEquals(1, dump["key1"])
    }

    @Test
    fun testIncrementKeyBy() {
        val keyStore = LRUKeyStore(3)
        keyStore.incrementKeyBy("key1", 5)
        val dump = keyStore.dump()
        assertEquals(5, dump["key1"])
    }

    @Test
    fun testIncrementKey_multipleKeys() {
        val keyStore = LRUKeyStore(3)
        keyStore.incrementKey("key1")
        keyStore.incrementKey("key2")
        keyStore.incrementKey("key1")
        val dump = keyStore.dump()
        assertEquals(2, dump["key1"])
        assertEquals(1, dump["key2"])
    }

    @Test
    fun testDump() {
        val keyStore = LRUKeyStore(3)
        keyStore.incrementKey("key1")
        keyStore.incrementKey("key2")
        val dump = keyStore.dump()
        assertEquals(1, dump["key1"])
        assertEquals(1, dump["key2"])
    }

    @Test
    fun testEviction() {
        val keyStore = LRUKeyStore(3)
        keyStore.incrementKey("key1")
        keyStore.incrementKey("key2")
        keyStore.incrementKey("key3")
        keyStore.incrementKey("key4") // Key4 should trigger eviction of key1

        val dump = keyStore.dump()

        assertEquals(null, dump["key1"]) // Key1 should be evicted
        assertEquals(1, dump["key2"]) // Key2 should still exist
        assertEquals(1, dump["key3"]) // Key3 should still exist
        assertEquals(1, dump["key4"]) // Key4 should exist
    }
}
