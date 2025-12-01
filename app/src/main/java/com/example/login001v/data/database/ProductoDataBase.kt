package com.example.login001v.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.login001v.data.dao.ProductoDao
import com.example.login001v.data.model.Producto

@Database(entities = [Producto::class], version = 1, exportSchema = false)
abstract class ProductoDataBase : RoomDatabase() {
    abstract fun productoDao(): ProductoDao
}