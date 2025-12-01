package com.example.login001v.data

import android.content.Context
import androidx.room.Room
import com.example.login001v.data.database.ProductoDataBase
import com.example.login001v.data.repository.ProductoRepository

class AppContainer(private val applicationContext: Context) {

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            ProductoDataBase::class.java,
            "producto_database"
        ).build()
    }

    val productoRepository by lazy {
        ProductoRepository(database.productoDao())
    }
}