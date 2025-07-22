package tungp.android.bazarbooks.screens.category

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tungp.android.bazarbooks.data.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.Book
import tungp.android.bazarbooks.domain.model.Categories
import tungp.android.bazarbooks.domain.model.Category
import tungp.android.bazarbooks.domain.model.PreviewData
import tungp.android.bazarbooks.domain.usecase.GetCategoriesUseCase
import tungp.android.bazarbooks.domain.usecase.NoParams
import tungp.android.bazarbooks.mvi.BaseViewState
import tungp.android.bazarbooks.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : MviViewModel<CategoryViewState, CategoryViewEvent>() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // Added for category selection
    private val _selectedCategoryId = MutableStateFlow<String>("all")
    val selectedCategoryId: StateFlow<String> = _selectedCategoryId.asStateFlow()

    // Added for books display
    private val _allBooks =
        MutableStateFlow<List<Book>>(PreviewData.popular) // Using preview data for now
    private val _filteredBooks = MutableStateFlow<List<Book>>(PreviewData.popular)
    val filteredBooks: StateFlow<List<Book>> = _filteredBooks.asStateFlow()

    init {
        fetchCategories()
    }

    override fun onTriggerEvent(event: CategoryViewEvent) {
        when (event) {
            is CategoryViewEvent.FetchCategories -> fetchCategories()
            is CategoryViewEvent.SelectCategory -> selectCategory(event.categoryId)
            is CategoryViewEvent.NavigateToDetail -> {
                // Handle navigation to detail screen
            }
        }
    }

    private fun fetchCategories() = safeLaunch {
        _isLoading.value = true
        _error.value = null

        getCategoriesUseCase(NoParams).collect { result ->
            when (result) {
                is BazaResult.Success -> {
                    _categories.value = result.data.categories
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

    private fun selectCategory(categoryId: String) {
        _selectedCategoryId.value = categoryId
        filterBooks()
        updateState()
    }

    private fun filterBooks() {
        val categoryId = _selectedCategoryId.value

        _filteredBooks.value = if (categoryId == "all") {
            _allBooks.value
        } else {
            // In a real implementation, you would filter by the category ID or fetch books for that category
            // For now, we'll simulate filtering by taking a subset of the preview data
            val categoryIndex = _categories.value.indexOfFirst { it.id == categoryId }
            if (categoryIndex >= 0) {
                val startIndex = (categoryIndex * 3) % _allBooks.value.size
                val count = (categoryIndex + 3) % _allBooks.value.size
                _allBooks.value.subList(
                    startIndex,
                    minOf(startIndex + count + 2, _allBooks.value.size)
                )
            } else {
                emptyList()
            }
        }
    }

    private fun updateState() {
        setData(
            CategoryViewState(
                categories = _categories.value,
                isLoading = _isLoading.value,
                error = _error.value,
                selectedCategoryId = _selectedCategoryId.value,
                books = _filteredBooks.value
            )
        )
    }
}

data class CategoryViewState(
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedCategoryId: String = "all",
    val books: List<Book> = emptyList()
)

sealed class CategoryViewEvent {
    object FetchCategories : CategoryViewEvent()
    data class SelectCategory(val categoryId: String) : CategoryViewEvent()
    data class NavigateToDetail(val categoryId: String) : CategoryViewEvent()
} 