package com.dbc.pokesuits.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.dbc.pokesuits.exceptions.RegraDeNegocioException;
import com.dbc.pokesuits.model.entity.User;

@Repository
public class UserRepository {
	
	private ArrayList<User> listUsers = new ArrayList<>();
	private AtomicInteger COUNTER = new AtomicInteger();
	
	public UserRepository() {
		listUsers.add(new User(COUNTER.incrementAndGet(),"boabalhao","joaovjcode@gmail.com",124578,"joaovjcode", new ArrayList<>()));
		listUsers.add(new User(COUNTER.incrementAndGet(),"boabalhao","joaovjcode@gmail.com",124578,"joaovjcode", new ArrayList<>()));
	}
	
	public Optional<User> getByid(Integer id) {
    	return 	 listUsers.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
    public User create(User user){
    	user.setId(COUNTER.incrementAndGet());
    	listUsers.add(user);
    	return user;
    }

    public List<User> list() {
        return listUsers;
    }

    public User update(Integer id, User user) throws RegraDeNegocioException{
    	
    	Optional<User> userById = this.getByid(id);
    	if(userById.isEmpty())throw new RegraDeNegocioException("Não Existe esse usuario");
    	User userRecuperado = userById.get();
    	
    	listUsers.remove(userRecuperado);
    	
    	userRecuperado.setEmail(user.getEmail());
    	userRecuperado.setNome(user.getNome());
    	userRecuperado.setUsername(user.getUsername());
        
    	listUsers.add(userRecuperado);
        
        return userRecuperado;
    }

    public User delete(Integer id) throws RegraDeNegocioException{
    	
    	Optional<User> userById = this.getByid(id);
    	if(userById.isEmpty())throw new RegraDeNegocioException("Não Existe esse usuario");
    	User userRecuperado = userById.get();
    	
        listUsers.remove(userRecuperado);
		return userRecuperado;
    }

    public List<User> listByName(String nome) {
        return listUsers.stream()
                .filter(user -> user.getNome().toUpperCase().contains(nome.toUpperCase()))
                .collect(Collectors.toList());
    }
	
}
