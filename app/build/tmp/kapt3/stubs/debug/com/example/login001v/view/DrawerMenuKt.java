package com.example.login001v.view;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000fH\u0007\u001a>\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132$\u0010\u0014\u001a \u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000b0\u0015H\u0007\u001a \u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019H\u0007\u001a\b\u0010\u001a\u001a\u00020\u000bH\u0007\"\u0013\u0010\u0000\u001a\u00020\u0001\u00a2\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0002\u0010\u0003\"\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u001b"}, d2 = {"ElectricBlue", "Landroidx/compose/ui/graphics/Color;", "getElectricBlue", "()J", "J", "menuItems", "", "Lcom/example/login001v/view/DrawerMenuItem;", "getMenuItems", "()Ljava/util/List;", "CustomTopAppBar", "", "title", "", "onMenuClick", "Lkotlin/Function0;", "DrawerContent", "username", "navController", "Landroidx/navigation/NavController;", "onItemClick", "Lkotlin/Function4;", "", "DrawerMenu", "productoViewModelFactory", "Lcom/example/login001v/viewmodel/ProductoViewModelFactory;", "DrawerMenuPreview", "app_debug"})
public final class DrawerMenuKt {
    private static final long ElectricBlue = 0L;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<com.example.login001v.view.DrawerMenuItem> menuItems = null;
    
    public static final long getElectricBlue() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.List<com.example.login001v.view.DrawerMenuItem> getMenuItems() {
        return null;
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DrawerMenu(@org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.example.login001v.viewmodel.ProductoViewModelFactory productoViewModelFactory) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void CustomTopAppBar(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onMenuClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DrawerContent(@org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function4<? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? super java.lang.Integer, kotlin.Unit> onItemClick) {
    }
    
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true)
    @androidx.compose.runtime.Composable()
    public static final void DrawerMenuPreview() {
    }
}