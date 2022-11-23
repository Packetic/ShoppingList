package com.example.shoppinglist.data

import com.example.shoppinglist.domain.model.ShopItem

class ShopListMapper {
    fun mapEntityToDbModel(shopItem: ShopItem): ShopItemDbModel = ShopItemDbModel(
        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        isEnabled = shopItem.isEnabled
    )

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel): ShopItem = ShopItem(
        id = shopItemDbModel.id,
        name = shopItemDbModel.name,
        count = shopItemDbModel.count,
        isEnabled = shopItemDbModel.isEnabled
    )

    fun mapListDbModelToListEntity(list: List<ShopItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}