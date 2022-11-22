package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.domain.model.ShopItem
import com.example.shoppinglist.domain.repository.ShopListRepository

class GetShopItemByIdUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItemById(id: Int): ShopItem {
        return shopListRepository.getShopItemById(id)
    }
}