package com.dev.StockManager.services;

import com.dev.StockManager.dtos.ClientDTO;
import com.dev.StockManager.entities.Client;
import com.dev.StockManager.entities.enums.TypeClient;
import com.dev.StockManager.exceptions.IdNotFoundException;
import com.dev.StockManager.exceptions.ValidatorException;
import com.dev.StockManager.repositories.ClientRepository;
import com.dev.StockManager.validator.CpfOrCnpjValidator;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientDTO> findAll() {
        List<Client> list = clientRepository.findAll();
        return list.stream().map(ClientDTO::new).toList();
    }

    public ClientDTO findById(int id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Client id not found"));
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

        clientRepository.save(client1);
    }

    public void update(Integer id,ClientDTO clientDTO) {
        ClientDTO ctDTO = findById(id);

        // Atualizando campos não nulos
        if (clientDTO.getName() != null) ctDTO.setName(clientDTO.getName());
        if (clientDTO.getEmail() != null) ctDTO.setEmail(clientDTO.getEmail());
        if (clientDTO.getCpf_Or_Cnpj() != null) ctDTO.setCpf_Or_Cnpj(clientDTO.getCpf_Or_Cnpj());

        Client ct = new Client(ctDTO.getId(), ctDTO.getName(), ctDTO.getCpf_Or_Cnpj(),
                ctDTO.getEmail(), ctDTO.getRegister_Moment(), ctDTO.getType());

        clientRepository.save(ct);
    }
}
