package com.dev.StockManager.config;


import com.dev.StockManager.entities.*;
import com.dev.StockManager.entities.enums.TypeClient;
import com.dev.StockManager.entities.enums.UserRole;
import com.dev.StockManager.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductStockRepository productStockRepository;

    @Override
    public void run(String... args) throws Exception {
        try{
        var passwordAdmin = passwordEncoder.encode(System.getenv("ADMIN_PASSWORD"));
        Client client1 = new Client(null, "ADMIN", "", "admin@gmail.com", null, passwordAdmin, null, UserRole.ADMIN);

        Client client2 = new Client(null, "Pedro Cavalcante", "90661616045", "cava2004@gmail.com",
                Timestamp.from(Instant.now()), passwordEncoder.encode("pedro"), TypeClient.INDIVIDUAL_CLIENT, UserRole.USER);
        Phone p2_1 = new Phone(null, "11878954321", client2);
        Phone p2_2 = new Phone(null, "96346512784", client2);
        Address add2_1 = new Address(null, "Pelota", "perto de uma farmacia",
                "Congós", 55, "65764312", client2);
        Address add2_2 = new Address(null, "JOGOS VORAZES", "dobrando um predio",
                "MACAPABA", 678, "66666666", client2);

        Client client3 = new Client(null, "Jairo Fonseca", "09917145000126", "fonseca@gmail.com",
                Timestamp.from(Instant.now()), passwordEncoder.encode("jairo"), TypeClient.CORPORATE_CLIENT, UserRole.USER);
        Phone p3 = new Phone(null, "96981219337", client3);
        Address add3 = new Address(null, "Boehmia", "de canto com um sinal",
                "Perpetuo Socorro", 21, "90237568", client3);

        Client client4 = new Client(null, "João Roberto", "03064318233", "jnete2004@gmail.com",
                Timestamp.from(Instant.now()), passwordEncoder.encode("joao21"), TypeClient.INDIVIDUAL_CLIENT, UserRole.USER);
        Phone p4 = new Phone(null, "96981219337", client4);
        Address add4 = new Address(null, "Avenida Dionisio Augusto Costa Filho", "perto de um cormercio",
                "Novo Buritizal", 98, "98989898", client4);

        clientRepository.saveAll(List.of(client1, client2, client3, client4));
        phoneRepository.saveAll(List.of(p4, p2_1, p2_2, p3));
        addressRepository.saveAll(List.of(add4, add2_1, add2_2, add3));


        Category category1 = new Category("ALIMENTOS");
        Product product1_1 = new Product(null, "Banana",
                "A banana é uma fruta tropical, rica em potássio, fibras e vitaminas, especialmente a vitamina C.",
                BigDecimal.valueOf(3.12),
                category1);
        ProductStock productStock1_1 = new ProductStock(50, product1_1);

        Product product1_2 = new Product(null, "Feijão",
                "Excelente fonte de nutrientes, o feijão é versátil e pode ser preparado de várias maneiras, como em sopas, saladas ou como acompanhamento de pratos principais.",
                BigDecimal.valueOf(6.24),
                category1);
        ProductStock productStock1_2 = new ProductStock(34, product1_2);


        Category category2 = new Category("Movel");
        Product product2_1 = new Product(null, "mesa de jantar",
                "A mesa de jantar é um móvel essencial em muitas casas, servindo como o ponto central para refeições em família e encontros sociais.",
                BigDecimal.valueOf(1534.32),
                category2);
        ProductStock productStock2_1 = new ProductStock(5, product2_1);

        Product product2_2 = new Product(null, "Sofá",
                "Proporciona conforto, o sofá é um ponto de encontro para a família e amigos, sendo perfeito para relaxar, assistir a filmes ou receber visitas.",
                BigDecimal.valueOf(2345.23),
                category2);
        ProductStock productStock2_2 = new ProductStock(2, product2_2);


        Category category3 = new Category("Computador");
        Product product3_1 = new Product(null, "Computador Game",
                "Intel Core i7, 16GB de RAM e SSD de 512GB",
                BigDecimal.valueOf(3212.61),
                category3);
        ProductStock productStock3_1 = new ProductStock(3, product3_1);

        Product product3_2 = new Product(null, "Monitor LED 24",
                "Os monitores são fundamentais para uma boa experiência de uso, seja para trabalho, estudo ou entretenimento. ",
                BigDecimal.valueOf(512.61),
                category3);
        ProductStock productStock3_2 = new ProductStock(8, product3_2);


        categoryRepository.saveAll(List.of(category1, category2, category3));
        productRepository.saveAll(List.of(product1_1, product1_2, product2_1, product2_2, product3_1, product3_2));
        productStockRepository.saveAll(List.of(productStock1_1, productStock1_2, productStock2_1, productStock2_2, productStock3_1, productStock3_2));
        } catch (Exception e){
            return;
        }

    }
}
