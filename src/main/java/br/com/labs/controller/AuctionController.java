package br.com.labs.controller;

import br.com.labs.model.Auction;
import br.com.labs.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auction")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;

    @GetMapping
    public Flux<Auction> getAll() {
        return auctionService.listAll();
    }

    @PostMapping
    public Mono<Auction> save(@RequestBody Auction auction) {
        return auctionService.save(auction);
    }
}
