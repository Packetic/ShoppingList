package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.domain.model.ShopItem
import com.example.shoppinglist.domain.repository.ShopListRepository

class InsertItemUseCase(private val shopListRepository: ShopListRepository) {
    fun insertItem(item: ShopItem) {
        shopListRepository.insertItem(item)
    }
}