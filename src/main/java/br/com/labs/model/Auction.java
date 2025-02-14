package br.com.labs.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Auction {

    private Long id;

    private String description;

    private BigDecimal precoInicial;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
