package com.mckernant1.keytracker.submitter

import com.mckernant1.keytracker.store.KeyStore
import software.amazon.awssdk.services.s3.S3Client

class S3Submitter(
    private val s3: S3Client = S3Client.create()
) : DataSubmitter {


    override fun submitData(keyStore: KeyStore) {
        TODO("Not yet implemented")
    }
}
