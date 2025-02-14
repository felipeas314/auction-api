package br.com.labs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("auction")
public class Auction {

    @Id
    private Long id;

    private String name;

    private String description;

    private BigDecimal initialValue;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
