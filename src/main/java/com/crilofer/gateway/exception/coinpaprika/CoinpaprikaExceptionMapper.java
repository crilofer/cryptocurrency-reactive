package com.crilofer.gateway.exception.coinpaprika;

import com.crilofer.gateway.exception.ExternalServiceExceptionMapper;

public class CoinpaprikaExceptionMapper extends ExternalServiceExceptionMapper {

    @Override
    protected String getServiceName() {
        return "Coinpaprika";
    }
}
