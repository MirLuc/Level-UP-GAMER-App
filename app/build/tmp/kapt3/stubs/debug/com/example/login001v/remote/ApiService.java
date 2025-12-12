package com.example.login001v.remote;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/example/login001v/remote/ApiService;", "", "createPost", "Lcom/example/login001v/data/model/Post;", "post", "(Lcom/example/login001v/data/model/Post;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPosts", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ApiService {
    
    @retrofit2.http.GET(value = "/posts")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPosts(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.login001v.data.model.Post>> $completion);
    
    @retrofit2.http.POST(value = "/posts")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createPost(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.login001v.data.model.Post post, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.login001v.data.model.Post> $completion);
}