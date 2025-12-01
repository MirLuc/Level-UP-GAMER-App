package com.example.login001v.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0010"}, d2 = {"Lcom/example/login001v/data/AppContainer;", "", "applicationContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "database", "Lcom/example/login001v/data/database/ProductoDataBase;", "getDatabase", "()Lcom/example/login001v/data/database/ProductoDataBase;", "database$delegate", "Lkotlin/Lazy;", "productoRepository", "Lcom/example/login001v/data/repository/ProductoRepository;", "getProductoRepository", "()Lcom/example/login001v/data/repository/ProductoRepository;", "productoRepository$delegate", "app_debug"})
public final class AppContainer {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context applicationContext = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy database$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy productoRepository$delegate = null;
    
    public AppContainer(@org.jetbrains.annotations.NotNull()
    android.content.Context applicationContext) {
        super();
    }
    
    private final com.example.login001v.data.database.ProductoDataBase getDatabase() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.login001v.data.repository.ProductoRepository getProductoRepository() {
        return null;
    }
}