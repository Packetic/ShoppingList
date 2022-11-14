package com.example.shoppinglist.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.model.ShopItem
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ShopItemActivity : AppCompatActivity() {
    private lateinit var viewModel: ShopItemViewModel

    private lateinit var tilName: TextInputLayout
    private lateinit var tilCount: TextInputLayout
    private lateinit var etName: TextInputEditText
    private lateinit var etCount: TextInputEditText

    private lateinit var saveBtn: Button

    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        parseIntent()
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        initViews()
        initTextChangeListeners()
        launchModes()
        observeViewModel()
    }

    private fun launchEditMode() {
        viewModel.getItem(shopItemId)
        viewModel.shopItem.observe(this) {
            if (it != null) {
                etName.setText(it.name)
                etCount.setText(it.count.toString())
            }
        }
        saveBtn.setOnClickListener {
            viewModel.editItem(etName.text?.toString(), etCount.text?.toString())
        }
    }

    private fun launchAddMode() {
        saveBtn.setOnClickListener {
            viewModel.insertItem(etName.text?.toString(), etCount.text?.toString())
        }
    }

    private fun initViews() {
        tilName = findViewById(R.id.til_name)
        tilCount = findViewById(R.id.til_count)
        etName = findViewById(R.id.et_name)
        etCount = findViewById(R.id.et_count)
        saveBtn = findViewById(R.id.save_button)
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE))
            throw RuntimeException("Parameter screen_mode is abscent")
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD)
            throw RuntimeException("Unknown mode $mode")
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID))
                throw RuntimeException("No parameter SHOP_ITEM_ID")
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }
    }

    private fun initTextChangeListeners() {
        etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun launchModes() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun observeViewModel() {
        viewModel.errorInputCount.observe(this) {
            val message =
                if (it) getString(R.string.error_input)
                else null
            tilCount.error = message
        }
        viewModel.errorInputName.observe(this) {
            val message =
                if (it) getString(R.string.error_input)
                else null
            tilName.error = message
        }
        viewModel.closeScreen.observe(this) { finish() }
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent =
            Intent(context, ShopItemActivity::class.java)
                .putExtra(EXTRA_SCREEN_MODE, MODE_ADD)

        fun newIntentEditItem(context: Context, id: Int): Intent =
            Intent(context, ShopItemActivity::class.java)
                .putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
                .putExtra(EXTRA_SHOP_ITEM_ID, id)
    }
}