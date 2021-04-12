package com.SistemaGestion.ShowroomX.Service;

import com.SistemaGestion.ShowroomX.Model.Client;
import com.SistemaGestion.ShowroomX.Repository.IClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl {

    private final IClient dao;

    @Autowired
    public ClientServiceImpl(IClient dao) {
        this.dao = dao;
    }

    public Client save(Client client) {
        if (client.getPhone().equals("") || client.getDni().equals("") || client.getLastName().equals("") || client.getName().equals("") || client.getAddress().equals("")) {
            return null;
        }
        return dao.save(client);
    }

    public Client update(Client client) {
        if (client.getPhone().equals("") || client.getDni().equals("") || client.getLastName().equals("") || client.getName().equals("") || client.getAddress().equals("")) {
            return null;
        }
        return dao.save(client);
    }

    public List<Client> findAll() {
        return (List<Client>) dao.findAll();
    }

    public Page<Client> findAllPageable(Pageable pageable) {
        return dao.findAll(pageable);
    }

    public Long deleteByIdClient(Long idClient) {
        return idClient != 0 ? dao.deleteByIdClient(idClient) : null;
    }
}
