package br.com.labs.model;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("bid")
public class Bid {

    private Long id;

    private Long clientId;

    private Long auctionId;

    private BigDecimal price;

    private LocalDateTime date;
}
