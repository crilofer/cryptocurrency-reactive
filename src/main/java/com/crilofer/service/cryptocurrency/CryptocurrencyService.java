package com.crilofer.service.cryptocurrency;

import com.crilofer.core.model.CryptocurrencyTwitterFeed;
import com.crilofer.gateway.coinpaprika.CoinpaprikaGateway;
import com.crilofer.gateway.coinpaprika.dto.response.CoinpaprikaCoinResponseDto;
import com.crilofer.gateway.coinpaprika.dto.response.CoinpaprikaCoinTweetResponseDto;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class CryptocurrencyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CryptocurrencyService.class);

    private final CoinpaprikaGateway coinpaprikaGateway;
    private final CryptocurrencyMapper cryptocurrencyMapper;

    public CryptocurrencyService(@RestClient CoinpaprikaGateway coinpaprikaGateway,
                                 CryptocurrencyMapper cryptocurrencyMapper) {
        this.coinpaprikaGateway = coinpaprikaGateway;
        this.cryptocurrencyMapper = cryptocurrencyMapper;
    }

    public Uni<CryptocurrencyTwitterFeed> getTwitterFeed(CryptocurrencyTwitterFeed cryptocurrencyTwitterFeed) {
        Uni<List<CoinpaprikaCoinResponseDto>> coinpaprikaCoinsResponseDtoUni = coinpaprikaGateway
                .getCoinList().onFailure().invoke(failure ->
                        LOGGER.error("There were an error requesting to Coinpaprika the coin list. " +
                                "Error {}", failure.getMessage()));

        Uni<List<CoinpaprikaCoinTweetResponseDto>> coinpaprikaCoinTwitterFeedResponseDtoUni =
                coinpaprikaCoinsResponseDtoUni.flatMap(item -> coinpaprikaGateway.getCoinFeed(getCoinId(item,
                                cryptocurrencyTwitterFeed.symbol())))
                        .onFailure().invoke(failure -> LOGGER.error("There were an error Coinpaprika the twitter feed "
                                + "Error {}", failure.getMessage()));

        return Uni.combine().all()
                .unis(coinpaprikaCoinsResponseDtoUni, coinpaprikaCoinTwitterFeedResponseDtoUni)
                .combinedWith((coinpaprikaCoinsResponseDto, coinpaprikaCoinTwitterFeedResponseDto) ->
                        cryptocurrencyMapper.toCryptocurrencyTwitterFeed(coinpaprikaCoinTwitterFeedResponseDto));
    }

    private String getCoinId(List<CoinpaprikaCoinResponseDto> coinsResponseDto, String symbol) {
        return Objects.requireNonNull(coinsResponseDto.stream().parallel()
                .filter(coin -> symbol.equals(coin.symbol()))
                .findAny()
                .orElseThrow(() -> new NotFoundException("Cryptocurrency code not found"))).id();
    }
}
