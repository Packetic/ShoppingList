package com.example.shoppinglist.domain.usecases

import androidx.lifecycle.LiveData
import com.example.shoppinglist.domain.model.ShopItem

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}