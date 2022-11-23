package com.example.shoppinglist.presentation.stateholder

import android.app.Application
import androidx.lifecycle.*
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.model.ShopItem
import com.example.shoppinglist.domain.usecases.DeleteItemUseCase
import com.example.shoppinglist.domain.usecases.EditItemUseCase
import com.example.shoppinglist.domain.usecases.GetShopListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteItemUseCase = DeleteItemUseCase(repository)
    private val editItemUseCase = EditItemUseCase(repository)

    private val _shopList = MutableLiveData<List<ShopItem>>()
    val shopList: LiveData<List<ShopItem>> = getShopListUseCase.getShopList()

    fun deleteItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteItemUseCase.deleteItem(shopItem)
        }
    }

    fun editItem(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(isEnabled = !shopItem.isEnabled)
            editItemUseCase.editItem(newItem)
        }
    }
}