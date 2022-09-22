package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.domain.ShopItem

class DeleteItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteItem(item: ShopItem) {
        shopListRepository.deleteItem(item)
    }
}