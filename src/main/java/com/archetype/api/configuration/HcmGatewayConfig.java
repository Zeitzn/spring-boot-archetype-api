package com.archetype.api.configuration;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HcmGatewayConfig {
    private String findWorkerByPersonNumber;
}