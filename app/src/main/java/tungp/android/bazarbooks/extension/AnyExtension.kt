package tungp.android.bazarbooks.extension

val Any.classTag: String get() = this.javaClass.canonicalName.orEmpty()

val Any.methodTag get() = classTag + object : Any() {}.javaClass.enclosingMethod?.name

fun Any.hashCodeAsString(): String {
    return hashCode().toString()
}

/**
 * Safely casts an object to the specified type.
 * 
 * @return The casted object or null if the cast is not possible.
 */
inline fun <reified T : Any> Any?.safeCast(): T? {
    return this as? T
}

/**
 * Casts an object to the specified type.
 * 
 * @return The casted object.
 * @throws ClassCastException if the object cannot be cast to the specified type.
 */
inline fun <reified T : Any> Any.cast(): T {
    return this as T
}

/**
 * Safely casts an object to the specified type with a default value if the cast fails.
 * 
 * @param defaultValue The default value to return if the cast fails.
 * @return The casted object or the default value if the cast is not possible.
 */
inline fun <reified T : Any> Any?.castOrDefault(defaultValue: T): T {
    return this as? T ?: defaultValue
}