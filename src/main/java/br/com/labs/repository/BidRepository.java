package br.com.labs.repository;

import br.com.labs.model.Bid;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface BidRepository extends R2dbcRepository<Bid,Long> {

    Flux<Bid> findByAuctionIdOrderByDataHoraDesc(Long auctionId);
}
