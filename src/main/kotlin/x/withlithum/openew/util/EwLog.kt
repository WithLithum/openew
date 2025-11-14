package x.withlithum.openew.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Gets a [Logger] associated with the specified type.
 */
inline fun <reified T: Any> ewLogger() : Logger {
    val name = (T::class).simpleName ?: "anonymous"

    return LoggerFactory.getLogger("OpenEW::$name")
}

/**
 * Provides utilities that assists with interoperability to Java logging.
 */
object EwLog {
    /**
     * Gets a [Logger] associated with the specified type.
     */
    @Deprecated("Use ewLogger() instead.",
        replaceWith = ReplaceWith("ewLogger<T>()"),
        DeprecationLevel.WARNING)
    inline fun <reified T : Any> getLogger() : Logger {
        val name = (T::class).simpleName ?: "anonymous"

        return LoggerFactory.getLogger("OpenEW::$name")
    }
}