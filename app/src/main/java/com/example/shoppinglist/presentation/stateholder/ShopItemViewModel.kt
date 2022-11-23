package com.example.shoppinglist.presentation.stateholder

import android.app.Application
import androidx.lifecycle.*
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.model.ShopItem
import com.example.shoppinglist.domain.usecases.EditItemUseCase
import com.example.shoppinglist.domain.usecases.GetShopItemByIdUseCase
import com.example.shoppinglist.domain.usecases.InsertItemUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ShopItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val insertItemUseCase = InsertItemUseCase(repository)
    private val editItemUseCase = EditItemUseCase(repository)
    private val getShopItemByIdUseCase = GetShopItemByIdUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean> = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean> = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem> = _shopItem

    private val _closeScreen = MutableLiveData<Unit>()
    val closeScreen: LiveData<Unit> = _closeScreen

    fun insertItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val valid = validateInput(name, count)
        if (valid) {
            viewModelScope.launch {
                val shopItem = ShopItem(name, count, true)
                insertItemUseCase.insertItem(shopItem)
                finishScreen()
            }
        }
    }

    fun editItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val valid = validateInput(name, count)
        if (valid) {
            _shopItem.value?.let {
                viewModelScope.launch {
                    val item = it.copy(name = name, count = count)
                    editItemUseCase.editItem(item)
                    finishScreen()
                }
            }
        }
    }

    fun getItem(id: Int) {
        viewModelScope.launch {
            val item = getShopItemByIdUseCase.getShopItemById(id)
            _shopItem.value = item
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishScreen() {
        _closeScreen.value = Unit
    }

}