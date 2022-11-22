package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.domain.model.ShopItem
import com.example.shoppinglist.domain.repository.ShopListRepository

class DeleteItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteItem(item: ShopItem) {
        shopListRepository.deleteItem(item)
    }
}