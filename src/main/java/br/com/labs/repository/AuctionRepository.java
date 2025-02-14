package br.com.labs.repository;

import br.com.labs.model.Auction;
import br.com.labs.model.Bid;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface AuctionRepository extends R2dbcRepository<Bid, Long> {
    Flux<Auction> findByDescriptionContainingIgnoreCase(String descricao);
}
