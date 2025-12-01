package com.example.login001v.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login001v.data.model.Post
import com.example.login001v.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {

    private val repository = PostRepository()

    // Flujo mutable que contiene la lista de posts y su estado
    internal val _postList = MutableStateFlow<List<Post>> (emptyList())

    // Flujo público (solo lectura) que la UI observará
    val postList: StateFlow<List<Post>> = _postList

    // Se llama automáticamente al inicio del ViewModel
    init {
        fetchPosts()
    }

    private fun fetchPosts(){
        // Lanza una corrutina en el scope del ViewModel
        viewModelScope.launch {
            try{
                // Llama al repositorio para obtener los datos y actualiza el estado
                _postList.value = repository.getPosts()
            } catch(e:Exception){
                // Manejo básico de errores
                println("Error al obtener datos: ${e.localizedMessage}")
            }
        }
    }
}