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
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new IdNotFoundException("Client not found"));

        return new ClientDTO(client);
    }

    @Transactional
    public void create(ClientDTO entity) {

        // Validações de entidades
        ClientValidator.validator(entity);
        ClientValidator.hasThisCpfOrCnpjValidator(findAll(), entity);
        AddressValidator.addressesValidator(entity.getAddresses());
        PhoneValidator.phonesValidator(entity.getPhones());

        entity.setRegister_Moment(Timestamp.from(Instant.now()));

        Client client2 = ClientConverter.toEntity(entity);

        clientRepository.save(client2);
        phoneRepository.saveAll(client2.getPhones());
        addressRepository.saveAll(client2.getAddresses());

    }

    @Transactional // Endpoint que faz update de somente 3 campos
    public void update(Integer id, ClientDTO clientDTO) {
        ClientDTO ctDTO = findById(id);

        ClientValidator.validatorUpdate(ctDTO, clientDTO);

        Client ct1 = ClientConverter.toEntity(ctDTO);

        clientRepository.save(ct1);
    }

    @Transactional
    public void updateClientPhone(Integer clientId, Integer indexPhone, PhoneDTO phone) {

        //Validações
        PhoneValidator.fixedSizeValidator(indexPhone);
        PhoneValidator.phoneValidator(phone);
        Client ct = clientRepository.findById(clientId).orElseThrow(() -> new IdNotFoundException("Client not found!"));

        Phone p = new Phone();

        // Uma lista recebendo os dados de telefone do cliente
        // e caso não complete o tamanho máximo preenche com objetos nulos
        List<Phone> phones = ct.getPhones();
        while (phones.size() < 2) {
            phones.add(null);
        }

        //Verificação do telefone
        if (phones.get(indexPhone) != null) { // caso o telefone já existe, acontece a troca do número
            p = phoneRepository.findById(phones.get(indexPhone).getId())
                    .orElseThrow(() -> new IdNotFoundException("Phone not found!"));
            p.setNumber(phone.getNumber());

        } else { // caso o telefone não existe, acontece a adição do número
            p.setNumber(phone.getNumber());
            p.setClientId(ct);
            phoneRepository.save(p);
        }

        phones.set(indexPhone, p);
        ct.setPhones(phones);

        clientRepository.save(ct);
    }

    @Transactional
    public void updateClientAddress(Integer clientId, Integer indexAddress, AddressDTO dto) {
        // Mesma lógica do updateClientPhone

        //Validações
        AddressValidator.fixedSizeValidator(indexAddress);
        AddressValidator.addressValidator(dto);
        Client ct = clientRepository.findById(clientId).orElseThrow(() -> new IdNotFoundException("Client not found!"));

        // Uma lista recebendo os dados dos endereços do cliente
        // e caso não complete o tamanho máximo preenche com objetos nulos
        List<Address> addresses = ct.getAddresses();
        while (addresses.size() < 3) {
            addresses.add(null);
        }

        Address address = new Address();

        //Verificação do endereço
        if(addresses.get(indexAddress) != null){ // caso o endereço exista, acontece a troca de endereço
            Address addr = addressRepository.findById(addresses.get(indexAddress).getId())
                    .orElseThrow(() -> new IdNotFoundException("Phone not found!"));
            address = AddressConverter.toEntityUpdate(dto,addr);
        }else { // caso o endereço não exista, acontece a adição do endereço
            address = AddressConverter.toEntityUpdate(dto, null);
            address.setClient(ct);
        }


        addressRepository.save(address);
        clientRepository.save(ct);
    }

    @Transactional
    public void delete(Integer id) {
        clientRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Client not found"));
        clientRepository.deleteById(id);
    }


}
