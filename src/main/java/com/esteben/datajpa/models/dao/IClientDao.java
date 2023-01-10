package com.esteben.datajpa.models.dao;

import com.esteben.datajpa.models.entity.Client;
import org.springframework.data.repository.CrudRepository;


public interface IClientDao extends CrudRepository<Client, Long> {
}
