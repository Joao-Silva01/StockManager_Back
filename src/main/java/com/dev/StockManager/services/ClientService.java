package com.dev.StockManager.services;

import com.dev.StockManager.dtos.AddressDTO;
import com.dev.StockManager.dtos.ClientDTO;
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
import com.dev.StockManager.validator.CpfOrCnpjValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
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
        // Verificação do CPF
        if (Objects.equals(entity.getType().getCode(), TypeClient.INDIVIDUAL_CLIENT.getCode())) {
            boolean valid = CpfOrCnpjValidator.CpfValidator(entity.getCpf_Or_Cnpj());
            if (!valid) {
                throw new ValidatorException("Incorrect CPF!!");
            }
        } else // Verificação do CNPJ
        {
            boolean valid = CpfOrCnpjValidator.CnpjValidator(entity.getCpf_Or_Cnpj());
            if (!valid) {
                throw new ValidatorException("Incorrect CPF!!");
            }
        }

        Client client1 = new Client(entity.getId(), entity.getName().strip(), entity.getCpf_Or_Cnpj().strip(), entity.getEmail().strip(),
                Timestamp.from(Instant.now()), entity.getType());
        List<Phone> phones = new ArrayList<>(entity.getPhones().stream().map(x -> new Phone(null, x.getNumber(), client1)).toList());

        List<Address> addresses = new ArrayList<>(entity.getAddresses().stream()
                .map(x -> new Address(null, x.getStreetName(), x.getComplement(), x.getNeighborhoodName(), x.getNumber(), x.getCep(), client1)).toList());

        client1.setPhones(phones);
        client1.setAddresses(addresses);
        clientRepository.save(client1);
    }

    public void update(Integer id, ClientDTO clientDTO) {
        ClientDTO ctDTO = findById(id);

        // Atualizando campos não nulos
        if (clientDTO.getName() != null) ctDTO.setName(clientDTO.getName());
        if (clientDTO.getEmail() != null) ctDTO.setEmail(clientDTO.getEmail());
        if (clientDTO.getCpf_Or_Cnpj() != null) ctDTO.setCpf_Or_Cnpj(clientDTO.getCpf_Or_Cnpj());

        Client ct = new Client(ctDTO.getId(), ctDTO.getName(), ctDTO.getCpf_Or_Cnpj(),
                ctDTO.getEmail(), ctDTO.getRegister_Moment(), ctDTO.getType());

        clientRepository.save(ct);
    }

    public void updateClientPhone(Integer clientId, Integer indexPhone, PhoneDTO phone) {
        Client ct = clientRepository.findById(clientId).orElseThrow(() -> new IdNotFoundException("Client not found!"));
        Phone p = new Phone();

        if (indexPhone == 0) {
            p = phoneRepository.findById(ct.getPhones().getFirst().getId()).get();
            p.setNumber(phone.getNumber());
            ct.getPhones().addFirst(p);
        } else if (indexPhone == 1) {
            p = phoneRepository.findById(ct.getPhones().get(1).getId()).get();
            p.setNumber(phone.getNumber());
            ct.getPhones().add(1, p);
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


}
