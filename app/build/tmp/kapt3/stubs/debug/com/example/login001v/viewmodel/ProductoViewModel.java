package com.example.login001v.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u000f"}, d2 = {"Lcom/example/login001v/viewmodel/ProductoViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/login001v/data/repository/ProductoRepository;", "(Lcom/example/login001v/data/repository/ProductoRepository;)V", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/example/login001v/viewmodel/ProductoUiState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "deleteAll", "Lkotlinx/coroutines/Job;", "insert", "producto", "Lcom/example/login001v/data/model/Producto;", "app_debug"})
public final class ProductoViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.login001v.data.repository.ProductoRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.login001v.viewmodel.ProductoUiState> uiState = null;
    
    public ProductoViewModel(@org.jetbrains.annotations.NotNull()
    com.example.login001v.data.repository.ProductoRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.login001v.viewmodel.ProductoUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insert(@org.jetbrains.annotations.NotNull()
    com.example.login001v.data.model.Producto producto) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteAll() {
        return null;
    }
}