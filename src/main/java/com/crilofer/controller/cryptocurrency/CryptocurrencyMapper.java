package com.crilofer.controller.cryptocurrency;

import com.crilofer.controller.dto.response.cryptocurrency.CryptocurrencyTweetResponseDto;
import com.crilofer.controller.dto.response.cryptocurrency.CryptocurrencyTwitterFeedResponseDto;
import com.crilofer.core.model.CryptocurrencyTweet;
import com.crilofer.core.model.CryptocurrencyTwitterFeed;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface CryptocurrencyMapper {

    @Mapping(target = "symbol", expression = "java(symbol.toUpperCase())")
    CryptocurrencyTwitterFeed toCryptoCurrencyTwitterFeed(String symbol);

    default CryptocurrencyTwitterFeedResponseDto toCryptocurrencyTwitterFeedResponseDto(CryptocurrencyTwitterFeed feed) {
        return new CryptocurrencyTwitterFeedResponseDto(toCryptocurrencyTweetResponseList(feed.tweets()));
    }

    List<CryptocurrencyTweetResponseDto> toCryptocurrencyTweetResponseList(List<CryptocurrencyTweet> cryptocurrencyTweets);
}
