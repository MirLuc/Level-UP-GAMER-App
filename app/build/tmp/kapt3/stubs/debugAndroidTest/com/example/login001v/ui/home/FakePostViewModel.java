package com.example.login001v.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2 = {"Lcom/example/login001v/ui/home/FakePostViewModel;", "Lcom/example/login001v/viewmodel/PostViewModel;", "fakePosts", "", "Lcom/example/login001v/data/model/Post;", "(Ljava/util/List;)V", "fakeMutableUiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/login001v/viewmodel/PostUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "app_debugAndroidTest"})
public final class FakePostViewModel extends com.example.login001v.viewmodel.PostViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.login001v.viewmodel.PostUiState> fakeMutableUiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.login001v.viewmodel.PostUiState> uiState = null;
    
    public FakePostViewModel(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.login001v.data.model.Post> fakePosts) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.StateFlow<com.example.login001v.viewmodel.PostUiState> getUiState() {
        return null;
    }
}