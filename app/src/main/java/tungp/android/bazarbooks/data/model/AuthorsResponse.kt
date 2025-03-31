package tungp.android.bazarbooks.data.model

import kotlinx.serialization.Serializable
import tungp.android.bazarbooks.domain.model.Author

@Serializable
data class AuthorsResponse(
    val authors: List<Author> = emptyList()
) 