package com.fiap.microsservice.coletor.service;

import com.fiap.microsservice.coletor.entities.Morador;
import com.fiap.microsservice.coletor.repositories.MoradorRepository;
import com.fiap.microsservice.coletor.repositories.RotaRepository;
import com.fiap.microsservice.coletor.service.exceptions.DatabaseException;
import com.fiap.microsservice.coletor.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoradorService {

    @Autowired
    private MoradorRepository repository;


    public Morador insert(Morador morador) {
        return repository.save(morador);
    }

    public Morador getMorador(Long id) {
        Optional<Morador> morador = repository.findById(id);
        return morador.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Morador> getAllMorador() {
        return repository.findAll();
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch(DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Morador update(Long id, Morador newMorador) {
        try{
            Morador oldMorador = repository.getReferenceById(id);
            updateData(oldMorador, newMorador);
            return repository.save(oldMorador);
        }  catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Morador oldMorador, Morador newMorador) {
        oldMorador.setNome(newMorador.getNome());
        oldMorador.setEmail(newMorador.getEmail());
        oldMorador.setTelefone(newMorador.getTelefone());
        oldMorador.setEndereco(newMorador.getEndereco());

    }


}
