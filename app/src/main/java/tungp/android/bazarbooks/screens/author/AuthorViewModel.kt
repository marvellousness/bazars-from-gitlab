package tungp.android.bazarbooks.screens.author

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import tungp.android.bazarbooks.data.remote.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.Author
import tungp.android.bazarbooks.domain.usecase.GetAuthorsUseCase
import tungp.android.bazarbooks.domain.usecase.NoParams
import tungp.android.bazarbooks.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorViewModel @Inject constructor(
    private val getAuthorsUseCase: GetAuthorsUseCase
) : MviViewModel<AuthorViewState, AuthorViewEvent>() {

    private val _authors = MutableStateFlow<List<Author>>(emptyList())
    val authors: StateFlow<List<Author>> = _authors.asStateFlow()
    
    private val _allAuthors = MutableStateFlow<List<Author>>(emptyList())
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories.asStateFlow()
    
    private val _selectedCategory = MutableStateFlow<String>("All")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    init {
        fetchAuthors()
    }

    override fun onTriggerEvent(event: AuthorViewEvent) {
        when (event) {
            is AuthorViewEvent.FetchAuthors -> fetchAuthors()
            is AuthorViewEvent.SelectCategory -> selectCategory(event.category)
            is AuthorViewEvent.NavigateToDetail -> {
                // Handle navigation to detail screen
            }
        }
    }

    private fun fetchAuthors() = safeLaunch {
        _isLoading.value = true
        _error.value = null

        getAuthorsUseCase(NoParams).collect { result ->
            when (result) {
                is BazaResult.Success -> {
                    _allAuthors.value = result.data
                    
                    // Extract unique categories
                    val uniqueCategories = result.data.mapNotNull { it.category }
                        .toSet()
                        .toList()
                        .sorted()
                    
                    // Add "All" category at the beginning
                    _categories.value = listOf("All") + uniqueCategories
                    
                    // Apply current filter
                    filterAuthorsByCategory(_selectedCategory.value)
                    
                    _isLoading.value = false
                    updateState()
                }

                is BazaResult.Error -> {
                    _error.value = result.exception.message ?: "Unknown error occurred"
                    _isLoading.value = false
                    updateState()
                }

                is BazaResult.Loading -> {
                    _isLoading.value = true
                    updateState()
                }
            }
        }
    }
    
    private fun selectCategory(category: String) {
        _selectedCategory.value = category
        filterAuthorsByCategory(category)
        updateState()
    }
    
    private fun filterAuthorsByCategory(category: String) {
        _authors.value = if (category == "All") {
            _allAuthors.value
        } else {
            _allAuthors.value.filter { it.category == category }
        }
    }

    private fun updateState() {
        setData(
            AuthorViewState(
                authors = _authors.value,
                categories = _categories.value,
                selectedCategory = _selectedCategory.value,
                isLoading = _isLoading.value,
                error = _error.value
            )
        )
    }
}

data class AuthorViewState(
    val authors: List<Author> = emptyList(),
    val categories: List<String> = emptyList(),
    val selectedCategory: String = "All",
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class AuthorViewEvent {
    object FetchAuthors : AuthorViewEvent()
    data class SelectCategory(val category: String) : AuthorViewEvent()
    data class NavigateToDetail(val authorId: String) : AuthorViewEvent()
} 