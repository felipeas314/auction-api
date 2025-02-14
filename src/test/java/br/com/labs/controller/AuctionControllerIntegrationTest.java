package br.com.labs.controller;

import br.com.labs.config.TestDatabaseConfig;
import br.com.labs.model.Auction;
import br.com.labs.repository.AuctionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Import(TestDatabaseConfig.class)
public class AuctionControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private AuctionRepository auctionRepository;

    @BeforeEach
    void setup(){
        auctionRepository.deleteAll().block();
    }

    @Test
    void createAuctionWithSuccess() {
        Auction auction = Auction.builder()
                .description("Leilão de Carro Clássico")
                .initialValue(BigDecimal.valueOf(50000))
                .startDate(LocalDateTime.now().plusDays(1))
                .endDate(LocalDateTime.now().plusDays(7))
                .build();

        webTestClient.post().uri("/auction")
                .body(Mono.just(auction), Auction.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Auction.class)
                .value(auctionCreated -> {
                    assertThat(auctionCreated.getId()).isNotNull();
                    assertThat(auctionCreated.getDescription()).isEqualTo(auction.getDescription());
                    assertThat(auctionCreated.getInitialValue()).isEqualTo(auction.getInitialValue());
                });
    }
}
