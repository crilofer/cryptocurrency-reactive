package com.crilofer.controller.cryptocurrency;

import com.crilofer.core.model.CryptocurrencyTwitterFeed;
import com.crilofer.service.cryptocurrency.CryptocurrencyService;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.core.Response;

public class CryptocurrencyControllerImpl implements CryptocurrencyController {

    private final CryptocurrencyMapper cryptocurrencyMapper;
    private final CryptocurrencyService cryptocurrencyService;

    public CryptocurrencyControllerImpl(CryptocurrencyMapper cryptocurrencyMapper,
                                        CryptocurrencyService cryptocurrencyService) {
        this.cryptocurrencyMapper = cryptocurrencyMapper;
        this.cryptocurrencyService = cryptocurrencyService;
    }

    public Uni<Response> getTwitterFeed(String cryptocurrencySymbol) {
        CryptocurrencyTwitterFeed twitterFeed = cryptocurrencyMapper.toCryptoCurrencyTwitterFeed(cryptocurrencySymbol);
        return cryptocurrencyService.getTwitterFeed(twitterFeed)
                .map(cryptocurrencyMapper::toCryptocurrencyTwitterFeedResponseDto)
                .map(item -> Response.ok().entity(item).build());
    }
}
