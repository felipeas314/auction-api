package br.com.labs.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bid {

    private Long id;

    private Long clientId;

    private Long auctionId;

    private BigDecimal price;

    private LocalDateTime date;
}
