package com.mckernant1.keytracker

import com.mckernant1.commons.extensions.executor.Executors.scheduleAtFixedRate
import com.mckernant1.commons.logging.Slf4j.logger
import com.mckernant1.keytracker.store.KeyStore
import com.mckernant1.keytracker.store.KeyStoreFactory
import com.mckernant1.keytracker.submitter.DataSubmitter
import org.slf4j.Logger
import java.time.Duration
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * Tracks keys and submits them to [DataSubmitter] every submittingPeriod
 *
 * The [KeyStore] is locked with a [ReentrantLock] so it does not need to have thread safety built in.
 *
 * The [DataSubmitter]
 *
 * @param submitter submits the data to wherever we want this stored
 * @param storeFactory creates the stores where we intermittently keep the data
 * @param submittingPeriod how often to submit data to the submitter
 * @param executorService the executor to use to schedule the background thread that submits the data
 */
class KeyTracker(
    private val submitter: DataSubmitter,
    private val storeFactory: KeyStoreFactory,
    submittingPeriod: Duration,
    executorService: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
) {

    companion object {
        private val logger: Logger = logger()
    }

    private val currentStoreLock: ReentrantLock = ReentrantLock()
    @Volatile
    private var currentStore: KeyStore = storeFactory.create()

    init {
        executorService.scheduleAtFixedRate(submittingPeriod) {
            logger.info("Submitting key access")
            val tempStore = currentStoreLock.withLock {
                val temp = currentStore
                currentStore = storeFactory.create()
                temp
            }
            submitter.submitData(tempStore)
        }
    }

    fun incrementKey(key: String): Unit = currentStoreLock.withLock {
        currentStore.incrementKey(key)
    }


    fun incrementKeyBy(key: String, toIncBy: Int): Unit = currentStoreLock.withLock {
        currentStore.incrementKeyBy(key, toIncBy)
    }

}
