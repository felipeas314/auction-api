package br.com.labs.service;

import br.com.labs.model.Auction;
import br.com.labs.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuctionService {

   private final AuctionRepository auctionRepository;

   public Flux<Auction> listAll() {
       return auctionRepository.findAll();
   }

   public Mono<Auction> save(Auction auction) {
       return auctionRepository.save(auction);
    }
}
