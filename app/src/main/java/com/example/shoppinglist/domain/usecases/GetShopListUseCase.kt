package com.example.shoppinglist.domain.usecases

import androidx.lifecycle.LiveData
import com.example.shoppinglist.domain.ShopItem

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}