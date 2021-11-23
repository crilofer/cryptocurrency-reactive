package com.crilofer.service.cryptocurrency;

import com.crilofer.core.model.CryptocurrencyTweet;
import com.crilofer.core.model.CryptocurrencyTwitterFeed;
import com.crilofer.gateway.coinpaprika.dto.response.CoinpaprikaCoinTweetResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface CryptocurrencyMapper {

    default CryptocurrencyTwitterFeed toCryptocurrencyTwitterFeed(List<CoinpaprikaCoinTweetResponseDto> coinTweets) {
        return new CryptocurrencyTwitterFeed(null, toCryptocurrencyTweetList(coinTweets));
    }

    List<CryptocurrencyTweet> toCryptocurrencyTweetList(List<CoinpaprikaCoinTweetResponseDto> coinTweets);

    @Mapping(target = "tweet", source = "status")
    CryptocurrencyTweet toCryptocurrencyTweet(CoinpaprikaCoinTweetResponseDto cointTweet);
}
