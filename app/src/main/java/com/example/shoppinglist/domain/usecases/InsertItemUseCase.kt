package com.example.shoppinglist.domain.usecases

import com.example.shoppinglist.domain.ShopItem

class InsertItemUseCase(private val shopListRepository: ShopListRepository) {
    fun insertItem(item: ShopItem) {
        shopListRepository.insertItem(item)
    }
}