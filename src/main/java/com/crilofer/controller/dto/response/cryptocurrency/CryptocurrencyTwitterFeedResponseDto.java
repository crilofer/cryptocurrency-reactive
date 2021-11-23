package com.crilofer.controller.dto.response.cryptocurrency;

import java.util.List;

public record CryptocurrencyTwitterFeedResponseDto(List<CryptocurrencyTweetResponseDto> feed) {
}
