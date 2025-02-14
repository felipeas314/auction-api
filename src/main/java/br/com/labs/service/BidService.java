package br.com.labs.service;

import br.com.labs.model.Bid;
import br.com.labs.repository.AuctionRepository;
import br.com.labs.repository.BidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BidService {

    private AuctionRepository auctionRepository;
    private BidRepository bidRepository;

    public Flux<Bid> getBidsByAuction(Long auctionId) {
        return bidRepository.findByAuctionIdOrderByDateDesc(auctionId);
    }

    public Mono<Bid> registerBid(Bid newBid) {
        return auctionRepository.findById(newBid.getAuctionId())
                .switchIfEmpty(Mono.error(new RuntimeException("Leilão não encontrado")))
                .flatMap(auction -> bidRepository.findByAuctionIdOrderByDateDesc(newBid.getAuctionId())
                        .collectList()
                        .flatMap(bids -> {

                            if (!bids.isEmpty() && newBid.getPrice().compareTo(bids.get(0).getPrice()) <= 0) {
                                return Mono.error(new RuntimeException("O lance deve ser maior que o lance anterior."));
                            }
                            if (newBid.getPrice().compareTo(auction.getInitialValue()) < 0) {
                                return Mono.error(new RuntimeException("O lance deve ser maior que o preço inicial do leilão."));
                            }

                            return bidRepository.save(newBid);
                        })
                );
    }
}
