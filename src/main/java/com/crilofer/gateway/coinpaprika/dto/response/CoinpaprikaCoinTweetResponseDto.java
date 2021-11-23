package com.crilofer.gateway.coinpaprika.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CoinpaprikaCoinTweetResponseDto(String date, @JsonProperty("user_name") String username, String status) {

}
