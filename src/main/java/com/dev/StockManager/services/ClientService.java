package com.dev.StockManager.services;

import com.dev.StockManager.converter.AddressConverter;
import com.dev.StockManager.converter.ClientConverter;
import com.dev.StockManager.converter.PhoneConverter;
import com.dev.StockManager.dtos.AddressDTO;
import com.dev.StockManager.dtos.client.ClientDTO;
import com.dev.StockManager.dtos.PhoneDTO;
import com.dev.StockManager.entities.Address;
import com.dev.StockManager.entities.Client;
import com.dev.StockManager.entities.Phone;
import com.dev.StockManager.entities.enums.TypeClient;
import com.dev.StockManager.exceptions.IdNotFoundException;
import com.dev.StockManager.exceptions.ValidatorException;
import com.dev.StockManager.repositories.AddressRepository;
import com.dev.StockManager.repositories.ClientRepository;
import com.dev.StockManager.repositories.PhoneRepository;
import com.dev.StockManager.repositories.SalesOrderRepository;
import com.dev.StockManager.validator.AddressValidator;
import com.dev.StockManager.validator.ClientValidator;
import com.dev.StockManager.validator.CpfOrCnpjValidator;
import com.dev.StockManager.validator.PhoneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    public List<ClientDTO> findAll() {
        List<Client> list = clientRepository.findAll();
        return list.stream().map(ClientDTO::new).toList();
    }

    public ClientDTO findById(int id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Client id not found"));
        ClientDTO ctDTO = new ClientDTO(client);
        return new ClientDTO(client);
    }

    public void create(ClientDTO entity) {

        // Validações de entidades
        ClientValidator.validator(entity);
        for(AddressDTO add : entity.getAddresses()){
            AddressValidator.validator(add);
        }
        for(PhoneDTO pho : entity.getPhones()){
            PhoneValidator.validator(pho);
        }

        entity.setRegister_Moment(Timestamp.from(Instant.now()));
        Client client2 = ClientConverter.toEntity(entity);

        List<Phone> phones = PhoneConverter.toListDTO(entity.getPhones(),client2);

        List<Address> addresses = AddressConverter.toListDTO(entity.getAddresses(), client2);

        client2.setPhones(phones);
        client2.setAddresses(addresses);
        clientRepository.save(client2);
    }

    // Endpoint que faz update de somente 3 campos
    public void update(Integer id, ClientDTO clientDTO) {
        ClientDTO ctDTO = findById(id);

        ClientValidator.validatorUpdate(ctDTO,clientDTO);

        Client ct1 = ClientConverter.toEntity(ctDTO);

        clientRepository.save(ct1);
    }

    public void updateClientPhone(Integer clientId, Integer indexPhone, PhoneDTO phone) {
        Client ct = clientRepository.findById(clientId).orElseThrow(() -> new IdNotFoundException("Client not found!"));
        Phone p = new Phone();

        for (int i = 0; i < 2; i++) {
            if (indexPhone == i){
                p = phoneRepository.findById(ct.getPhones().get(i).getId()).get();
                p.setNumber(phone.getNumber());
                ct.getPhones().add(i, p);
            }
        }

        phoneRepository.save(p);
        clientRepository.save(ct);
    }

    public void updateClientAddress(Integer clientId, Integer indexAddress, AddressDTO address) {
        Client ct = clientRepository.findById(clientId).orElseThrow(() -> new IdNotFoundException("Client not found!"));
        Address addr = new Address();

        for (int i = 0; i < 3; i++) {
            if (indexAddress == i) {
                addr = addressRepository.findById(ct.getAddresses().get(i).getId()).get();
                addr.setStreetName(address.getStreetName());
                addr.setComplement(address.getComplement());
                addr.setNeighborhoodName(address.getNeighborhoodName());
                addr.setNumber(address.getNumber());
                addr.setCep(address.getCep());
            }
        }

        addressRepository.save(addr);
        clientRepository.save(ct);
    }

    public void delete(Integer id){
        clientRepository.deleteById(id);
    }


}
