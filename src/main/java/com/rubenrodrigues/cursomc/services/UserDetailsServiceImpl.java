package com.rubenrodrigues.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rubenrodrigues.cursomc.domain.Cliente;
import com.rubenrodrigues.cursomc.repositories.ClienteRepository;
import com.rubenrodrigues.cursomc.security.UserSecuriry;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cli = clienteRepository.findByEmail(email);
		if (cli == null)
			throw new UsernameNotFoundException(email);
		return new UserSecuriry(cli.getId(), cli.getEmail(), cli.getSenha(),cli.getPerfis());
	}

}
