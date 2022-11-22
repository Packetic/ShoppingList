package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.domain.model.ShopItem
import com.example.shoppinglist.domain.repository.ShopListRepository

class EditItemUseCase(private val shopListRepository: ShopListRepository) {
    fun editItem(shopItem: ShopItem) {
        shopListRepository.editItem(shopItem)
    }
}