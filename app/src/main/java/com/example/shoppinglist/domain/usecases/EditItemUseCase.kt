package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.domain.model.ShopItem

class EditItemUseCase(private val shopListRepository: ShopListRepository) {
    fun editItem(shopItem: ShopItem) {
        shopListRepository.editItem(shopItem)
    }
}