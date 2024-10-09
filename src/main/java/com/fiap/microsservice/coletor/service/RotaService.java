package com.fiap.microsservice.coletor.service;

import com.fiap.microsservice.coletor.entities.Rota;
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
public class RotaService {

    @Autowired
    private RotaRepository repository;


    public Rota insert(Rota rota) {
        return repository.save(rota);
    }

    public Rota getRota(Long id) {
        Optional<Rota> rota = repository.findById(id);
        return rota.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Rota> getAllRota() {
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

    public Rota update(Long id, Rota newRota) {
        try{
            Rota oldRota = repository.getReferenceById(id);
            updateData(oldRota, newRota);
            return repository.save(oldRota);
        }  catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Rota oldRota, Rota newRota) {
        oldRota.setDescricao(newRota.getDescricao());
        oldRota.setDistancia(newRota.getDistancia());
    }


}
