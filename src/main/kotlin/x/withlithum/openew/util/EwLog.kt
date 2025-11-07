package x.withlithum.openew.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Provides utilities that assists with interoperability to Java logging.
 */
object EwLog {
    inline fun <reified T : Any> getLogger() : Logger {
        return LoggerFactory.getLogger(T::class.java)
    }
}