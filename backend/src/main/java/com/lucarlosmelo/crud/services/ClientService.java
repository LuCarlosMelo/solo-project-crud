package com.lucarlosmelo.crud.services;

import java.util.Optional;

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
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> entity = repository.findById(id);
		return new ClientDTO(entity.get());
	}
	
	@Transactional
	public ClientDTO insert(ClientDTO dto ) {
		Client entity = new Client();
		dtoToEntity(dto, entity);
		entity = repository.save(entity);
	    return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		Client entity = repository.getOne(id);
		dtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}
	
	public void delete(Long id){
		repository.deleteById(id);
	}
	
	private void dtoToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}
	
}
