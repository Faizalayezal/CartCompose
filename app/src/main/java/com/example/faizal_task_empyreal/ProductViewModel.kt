package com.example.faizal_task_empyreal

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.faizal_task_empyreal.model.Product
import com.example.faizal_task_empyreal.repository.ProductRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repo: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProductListState())
    val state = _state
        .onStart { loadCoins() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            ProductListState()
        )



    private fun loadCoins() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            delay(3000)
            val products = repo.getProducts()
            _state.value = ProductListState(isLoading = false, products = products)
        }
    }

    fun onProductSelected(productId: Int) {
        _state.update { it.copy(selectedProductId = productId) }
    }

    fun increaseCount(productId: Int) {
        updateCount(productId) { it + 1 }
    }

    fun decreaseCount(productId: Int) {
        updateCount(productId) { count -> if (count > 0) count - 1 else 0 }
    }

    fun onCountRemove() {
        val updated = _state.value.products.map { it.copy(count = 0) }
        _state.value = _state.value.copy(products = updated)
    }

    private fun updateCount(productId: Int, update: (Int) -> Int) {
        val updated = _state.value.products.map {
            if (it.id == productId) it.copy(count = update(it.count)) else it
        }
        _state.value = _state.value.copy(products = updated)
    }

}

@Immutable
data class ProductListState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val selectedProductId: Int? = null,

    )