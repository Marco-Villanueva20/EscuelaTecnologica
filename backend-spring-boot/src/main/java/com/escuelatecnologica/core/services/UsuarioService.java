package com.escuelatecnologica.core.services;

import com.escuelatecnologica.core.entities.Usuario;
import com.escuelatecnologica.core.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService{
	
    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Optional<Usuario> user = usuarioRepository.findByUsername(username);
		    if (user.isPresent()) {
		        var userObj = user.get();
		        
		        return User.builder()
		            .username(userObj.getUsername())
		            .password(userObj.getPassword())
		            .roles(getRoles(userObj))
		            .build();
		    } else {
		        throw new UsernameNotFoundException(username);
		    }
	}

    
    
    private String[] getRoles(Usuario userObj) {
    	if (userObj.getRole() == null) {
            return new String[]{"USER"};
        }
        return userObj.getRole().split(",");
    }



	public Usuario saveUser(Usuario usuario) {
        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAllUsers() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

//    public Usuario authenticateUser(String login, String clave) {
//        if (login == null || clave == null) {
//            return null;
//        }
//        Usuario usuario = usuarioRepository.findByUsername(login);
//        if (usuario != null && bCryptPasswordEncoder.matches(clave, usuario.getClave())) {
//            return usuario;
//        }
//        return null;
//    }
}
