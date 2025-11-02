package com.example.login001v.ui.login;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u001a\u0010\u0013\u001a\u00020\u000f2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000f0\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lcom/example/login001v/ui/login/LoginViewModel;", "Landroidx/lifecycle/ViewModel;", "repo", "Lcom/example/login001v/data/repository/AuthRepository;", "(Lcom/example/login001v/data/repository/AuthRepository;)V", "<set-?>", "Lcom/example/login001v/ui/login/LoginUiState;", "uiState", "getUiState", "()Lcom/example/login001v/ui/login/LoginUiState;", "setUiState", "(Lcom/example/login001v/ui/login/LoginUiState;)V", "uiState$delegate", "Landroidx/compose/runtime/MutableState;", "onPasswordChange", "", "value", "", "onUsernameChange", "submit", "onSucces", "Lkotlin/Function1;", "app_debug"})
public final class LoginViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.login001v.data.repository.AuthRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState uiState$delegate = null;
    
    public LoginViewModel(@org.jetbrains.annotations.NotNull()
    com.example.login001v.data.repository.AuthRepository repo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.login001v.ui.login.LoginUiState getUiState() {
        return null;
    }
    
    public final void setUiState(@org.jetbrains.annotations.NotNull()
    com.example.login001v.ui.login.LoginUiState p0) {
    }
    
    public final void onUsernameChange(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void onPasswordChange(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void submit(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onSucces) {
    }
    
    public LoginViewModel() {
        super();
    }
}