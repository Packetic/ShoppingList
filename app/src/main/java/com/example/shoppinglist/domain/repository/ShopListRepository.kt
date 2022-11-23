package com.example.shoppinglist.domain.repository

import androidx.lifecycle.LiveData
import com.example.shoppinglist.domain.model.ShopItem

interface ShopListRepository {
    suspend fun insertItem(item: ShopItem)

    suspend fun deleteItem(item: ShopItem)

    suspend fun editItem(shopItem: ShopItem)

    suspend fun getShopItemById(id: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}