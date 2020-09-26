package ru.tinkoff.invest.openapi;

import org.jetbrains.annotations.NotNull;
import ru.tinkoff.invest.openapi.models.user.AccountsList;

import java.util.concurrent.CompletableFuture;

/**
 * Интерфейс работы с OpenAPI в части касающейся получения информации о клиенте.
 */
public interface UserContext extends Context {

    /**
     * Асинхронное получение списка брокерских счетов.
     *
     * @return Список счетов.
     */
    @NotNull
    CompletableFuture<AccountsList> getAccounts();

}
