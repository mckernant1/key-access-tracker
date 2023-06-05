package com.mckernant1.keytracker.submitter

import com.google.gson.Gson
import com.mckernant1.keytracker.store.KeyStore
import java.io.File
import java.time.Instant

/**
 *
 */
class JsonFileSubmitter(
    private val outputDir: File,
    private val gson: Gson = Gson()
): DataSubmitter {

    override fun submitData(keyStore: KeyStore) {
        val outFile = File(outputDir, "${Instant.now().toEpochMilli()}.json")
        outFile.writeText(gson.toJson(keyStore))
    }

}
