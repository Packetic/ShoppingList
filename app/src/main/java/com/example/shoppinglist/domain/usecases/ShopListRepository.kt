package com.example.shoppinglist.domain.usecases

import androidx.lifecycle.LiveData
import com.example.shoppinglist.domain.model.ShopItem

interface ShopListRepository {
    fun insertItem(item: ShopItem)

    fun deleteItem(item: ShopItem)

    fun editItem(shopItem: ShopItem)

    fun getShopItemById(id: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}