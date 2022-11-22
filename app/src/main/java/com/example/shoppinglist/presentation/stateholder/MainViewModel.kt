package com.example.shoppinglist.presentation.stateholder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.model.ShopItem
import com.example.shoppinglist.domain.usecases.DeleteItemUseCase
import com.example.shoppinglist.domain.usecases.EditItemUseCase
import com.example.shoppinglist.domain.usecases.GetShopListUseCase

class MainViewModel: ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteItemUseCase = DeleteItemUseCase(repository)
    private val editItemUseCase = EditItemUseCase(repository)

    private val _shopList = MutableLiveData<List<ShopItem>>()
    val shopList: LiveData<List<ShopItem>> = getShopListUseCase.getShopList()

    fun deleteItem(shopItem: ShopItem) {
        deleteItemUseCase.deleteItem(shopItem)
    }

    fun editItem(shopItem: ShopItem) {
        val newItem = shopItem.copy(isEnabled = !shopItem.isEnabled)
        editItemUseCase.editItem(newItem)
    }
}