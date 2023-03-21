package com.lucarlosmelo.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucarlosmelo.crud.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
