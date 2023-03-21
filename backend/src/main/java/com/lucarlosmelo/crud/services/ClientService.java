package com.lucarlosmelo.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucarlosmelo.crud.entities.Client;
import com.lucarlosmelo.crud.entities.dto.ClientDTO;
import com.lucarlosmelo.crud.repositories.ClientRepository;

@Service
public class ClientService {
	 
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(Pageable pageRequest){
		Page<Client> list = repository.findAll(pageRequest);
		return list.map(x -> new ClientDTO(x));
	}
	
	 
	
	
}
