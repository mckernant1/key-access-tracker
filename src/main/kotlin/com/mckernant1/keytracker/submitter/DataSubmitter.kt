package com.mckernant1.keytracker.submitter

import com.mckernant1.keytracker.store.KeyStore

interface DataSubmitter {

    fun submitData(keyStore: KeyStore)

}
