package com.crilofer.core.model;

import java.util.List;

public record CryptocurrencyTwitterFeed(String symbol, List<CryptocurrencyTweet> tweets) {
}