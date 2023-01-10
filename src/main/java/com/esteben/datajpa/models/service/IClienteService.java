package com.esteben.datajpa.models.service;

import com.esteben.datajpa.models.entity.*;

import java.util.*;

public interface IClienteService {

    public List<Client> findAll();

    public void save(Client c);

    public Client findById(Long cid);

    public void delete(Long cid);
}
