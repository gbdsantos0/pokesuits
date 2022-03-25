package com.dbc.pokesuits.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.dbc.pokesuits.entity.UserEntity;
import com.dbc.pokesuits.exceptions.RegraDeNegocioException;

@Repository
public class UserRepository {
	
	private ArrayList<UserEntity> listUsers = new ArrayList<>();
	private AtomicInteger COUNTER = new AtomicInteger();
	
	public UserRepository() {
		listUsers.add(new UserEntity(COUNTER.incrementAndGet(),"boabalhao","joaovjcode@gmail.com",124578,"joaovjcode", new ArrayList<>()));
		listUsers.add(new UserEntity(COUNTER.incrementAndGet(),"boabalhao","joaovjcode@gmail.com",124578,"joaovjcode", new ArrayList<>()));
	}
	
	public Optional<UserEntity> getByid(Integer id) {
    	return 	 listUsers.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
    public UserEntity saveAndFlush(UserEntity user){
    	user.setId(COUNTER.incrementAndGet());
    	listUsers.add(user);
    	return user;
    }

    public List<UserEntity> findAll() {
        return listUsers;
    }

    public UserEntity update(Integer id, UserEntity user) throws RegraDeNegocioException{
    	
    	Optional<UserEntity> userById = this.getByid(id);
    	if(userById.isEmpty())throw new RegraDeNegocioException("Não Existe esse usuario");
    	UserEntity userRecuperado = userById.get();

    	userRecuperado.setEmail(user.getEmail());
    	userRecuperado.setNome(user.getNome());
    	userRecuperado.setUsername(user.getUsername());

        return userRecuperado;
    }

    public UserEntity deleteById(Integer id) throws RegraDeNegocioException{
    	
    	Optional<UserEntity> userById = this.getByid(id);
    	if(userById.isEmpty())throw new RegraDeNegocioException("Não Existe esse usuario");
    	UserEntity userRecuperado = userById.get();
    	
        listUsers.remove(userRecuperado);
		return userRecuperado;
    }

    public List<UserEntity> findByName(String nome) {
        return listUsers.stream()
                .filter(user -> user.getNome().toUpperCase().contains(nome.toUpperCase()))
                .collect(Collectors.toList());
    }
	
}
