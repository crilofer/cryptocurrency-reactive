package com.crilofer.gateway.coinpaprika;

import com.crilofer.gateway.coinpaprika.dto.response.CoinpaprikaCoinResponseDto;
import com.crilofer.gateway.coinpaprika.dto.response.CoinpaprikaCoinTweetResponseDto;
import com.crilofer.gateway.exception.coinpaprika.CoinpaprikaExceptionMapper;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RegisterRestClient(configKey = "coinpaprika-api")
@RegisterProvider(CoinpaprikaExceptionMapper.class)
public interface CoinpaprikaGateway {

    @GET
    @Path("/v1/coins")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<CoinpaprikaCoinResponseDto>> getCoinList();

    @GET
    @Path("/v1/coins/{coinId}/twitter")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<CoinpaprikaCoinTweetResponseDto>> getCoinFeed(@PathParam("coinId") String coinId);
}
