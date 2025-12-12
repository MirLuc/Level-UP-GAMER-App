package com.example.login001v.data.repository;

/**
 * Pruebas del repositorio de Posts usando MockK.
 *
 * Objetivo:
 * - Simular el repositorio sin llamadas reales de red.
 * - Validar que getPosts() retorna exactamente la lista esperada (stub).
 *
 * Nota:
 * - Se usa runTest del kotlinx-coroutines-test para ejecutar código suspend.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\u00060\u0004j\u0002`\u0005H\u0007\u00a8\u0006\u0006"}, d2 = {"Lcom/example/login001v/data/repository/PostRepositoryMockTest;", "", "()V", "getPosts devuelve la lista simulada", "", "Lkotlinx/coroutines/test/TestResult;", "app_debugUnitTest"})
public final class PostRepositoryMockTest {
    
    public PostRepositoryMockTest() {
        super();
    }
}