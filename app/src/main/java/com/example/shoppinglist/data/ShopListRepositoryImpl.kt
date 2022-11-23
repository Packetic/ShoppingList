package com.example.shoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.shoppinglist.domain.model.ShopItem
import com.example.shoppinglist.domain.repository.ShopListRepository
import kotlin.random.Random

class ShopListRepositoryImpl(
    application: Application
): ShopListRepository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

    override fun insertItem(item: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(item))
    }

    override fun deleteItem(item: ShopItem) {
        shopListDao.deleteShopItem(item.id)
    }

    override fun editItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun getShopItemById(id: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(id)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(shopListDao.getShopList()) {
        mapper.mapListDbModelToListEntity(it)
    }

}