package com.esteben.datajpa.models.service.impl;

import com.esteben.datajpa.models.dao.*;
import com.esteben.datajpa.models.entity.*;
import com.esteben.datajpa.models.service.IClienteService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Repository
public class ClientServiceImpl implements IClienteService {
    @Autowired
    private IClientDao clientDao;

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return (List<Client>) clientDao.findAll();
    }

    @Override
    @Transactional
    public void save(Client c) {
        clientDao.save(c);
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(Long cid) {
        return clientDao.findById(cid).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long cid) {
        clientDao.delete(findById(cid));
    }
}
