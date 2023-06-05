package com.mckernant1.keytracker.store.lru

import com.mckernant1.keytracker.store.KeyStore
import com.mckernant1.keytracker.store.KeyStoreFactory

class LRUKeyStoreFactory(
    private val maxSize: Int
) : KeyStoreFactory {
    override fun create(): KeyStore = LRUKeyStore(maxSize)
}
