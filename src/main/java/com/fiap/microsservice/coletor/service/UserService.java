package com.fiap.microsservice.coletor.service;

//import com.fiap.microsservice.coletor.entities.User;
//import com.fiap.microsservice.coletor.repositories.CaminhaoRepository;
//import com.fiap.microsservice.coletor.repositories.UserRepository;
//import com.fiap.microsservice.coletor.service.exceptions.DatabaseException;
//import com.fiap.microsservice.coletor.service.exceptions.ResourceNotFoundException;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository repository;
//
//
//    public User insert(User user) {
//        return repository.save(user);
//    }
//
//    public User getUser(Long id) {
//        Optional<User> user = repository.findById(id);
//        return user.orElseThrow(() -> new ResourceNotFoundException(id));
//    }
//
//    public List<User> getAllUser() {
//        return repository.findAll();
//    }
//
//    public void delete(Long id) {
//        try {
//            repository.deleteById(id);
//        } catch (EmptyResultDataAccessException e) {
//            throw new ResourceNotFoundException(id);
//        } catch(DataIntegrityViolationException e) {
//            throw new DatabaseException(e.getMessage());
//        }
//    }
//
//    public User update(Long id, User newUser) {
//        try{
//            User oldUser = repository.getReferenceById(id);
//            updateData(oldUser, newUser);
//            return repository.save(oldUser);
//        }  catch(EntityNotFoundException e) {
//            throw new ResourceNotFoundException(id);
//        }
//    }
//
//    private void updateData(User oldUser, User newUser) {
//        oldUser.setNome(newUser.getNome());
//        oldUser.setEmail(newUser.getEmail());
//        oldUser.setSenha(newUser.getSenha());
//    }
//
//
//}





import com.fiap.microsservice.coletor.entities.DTO.UsuarioCadastroDTO;
import com.fiap.microsservice.coletor.entities.DTO.UsuarioExibicaoDTO;
import com.fiap.microsservice.coletor.entities.User;
import com.fiap.microsservice.coletor.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository usuarioRepository;

    public UsuarioExibicaoDTO salvarUsuario(UsuarioCadastroDTO usuarioDTO){

        String senhaCriptografada = new
                BCryptPasswordEncoder().encode(usuarioDTO.senha());

        User usuario = new User();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setSenha(senhaCriptografada);

        User usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioExibicaoDTO(usuarioSalvo);

    }

    public UsuarioExibicaoDTO listarPorId(Long id){
        Optional<User> usuarioOptional =
                usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()){
            return new UsuarioExibicaoDTO(usuarioOptional.get());
        } else {
            throw new RuntimeException("Usuário não existe!");
        }
    }

    public List<UsuarioExibicaoDTO> listarTodos(){
        return usuarioRepository
                .findAll()
                .stream()
                .map(UsuarioExibicaoDTO::new)
                .toList();
    }

}
