package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.domain.ShopItem

class GetShopItemByIdUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItemById(id: Int): ShopItem {
        return shopListRepository.getShopItemById(id)
    }
}