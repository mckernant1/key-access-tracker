package com.mckernant1.keytracker.store

/**
 * Storing the keys for a short period of time before submitting
 */
interface KeyStore {

    fun incrementKey(key: String)

    fun incrementKeyBy(key: String, toIncBy: Int)

    fun dump(): Map<String, Long>

}
