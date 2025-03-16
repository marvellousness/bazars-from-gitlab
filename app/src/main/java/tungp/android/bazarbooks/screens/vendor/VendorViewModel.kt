package tungp.android.bazarbooks.screens.vendor

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tungp.android.bazarbooks.data.model.base.BazaResult
import tungp.android.bazarbooks.domain.model.Vendor
import tungp.android.bazarbooks.domain.usecase.GetVendorsUseCase
import tungp.android.bazarbooks.domain.usecase.NoParams
import tungp.android.bazarbooks.mvi.BaseViewState
import tungp.android.bazarbooks.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class VendorViewModel @Inject constructor(
    private val getVendorsUseCase: GetVendorsUseCase
) : MviViewModel<VendorViewState, VendorViewEvent>() {

    private val _vendors = MutableStateFlow<List<Vendor>>(emptyList())
    val vendors: StateFlow<List<Vendor>> = _vendors.asStateFlow()
    
    private val _allVendors = MutableStateFlow<List<Vendor>>(emptyList())
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories.asStateFlow()
    
    private val _selectedCategory = MutableStateFlow<String>("All")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    init {
        fetchVendors()
    }

    override fun onTriggerEvent(event: VendorViewEvent) {
        when (event) {
            is VendorViewEvent.FetchVendors -> fetchVendors()
            is VendorViewEvent.SelectCategory -> selectCategory(event.category)
            is VendorViewEvent.NavigateToDetail -> {
                // Handle navigation to detail screen
            }
        }
    }

    private fun fetchVendors() = safeLaunch {
        _isLoading.value = true
        _error.value = null

        getVendorsUseCase(NoParams).collect { result ->
            when (result) {
                is BazaResult.Success -> {
                    _allVendors.value = result.data
                    
                    // Extract unique categories
                    val uniqueCategories = result.data.mapNotNull { it.category }
                        .toSet()
                        .toList()
                        .sorted()
                    
                    // Add "All" category at the beginning
                    _categories.value = listOf("All") + uniqueCategories
                    
                    // Apply current filter
                    filterVendorsByCategory(_selectedCategory.value)
                    
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
        filterVendorsByCategory(category)
        updateState()
    }
    
    private fun filterVendorsByCategory(category: String) {
        _vendors.value = if (category == "All") {
            _allVendors.value
        } else {
            _allVendors.value.filter { it.category == category }
        }
    }

    private fun updateState() {
        setData(
            VendorViewState(
                vendors = _vendors.value,
                categories = _categories.value,
                selectedCategory = _selectedCategory.value,
                isLoading = _isLoading.value,
                error = _error.value
            )
        )
    }
}

data class VendorViewState(
    val vendors: List<Vendor> = emptyList(),
    val categories: List<String> = emptyList(),
    val selectedCategory: String = "All",
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class VendorViewEvent {
    object FetchVendors : VendorViewEvent()
    data class SelectCategory(val category: String) : VendorViewEvent()
    data class NavigateToDetail(val vendorId: String) : VendorViewEvent()
} 