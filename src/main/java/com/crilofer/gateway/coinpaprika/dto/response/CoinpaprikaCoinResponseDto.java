package com.crilofer.gateway.coinpaprika.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CoinpaprikaCoinResponseDto(String id, String symbol) {
}