package com.fiap.microsservice.coletor.service;

import com.fiap.microsservice.coletor.entities.Caminhao;
import com.fiap.microsservice.coletor.repositories.CaminhaoRepository;
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
public class CaminhaoService {

    @Autowired
    private CaminhaoRepository repository;


    public Caminhao insert(Caminhao caminhao) {
        return repository.save(caminhao);
    }

    public Caminhao getCaminhao(Long id) {
        Optional<Caminhao> caminhao = repository.findById(id);
        return caminhao.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Caminhao> getAllCaminhao() {
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

    public Caminhao update(Long id, Caminhao newCaminhao) {
        try{
            Caminhao oldCaminhao = repository.getReferenceById(id);
            updateData(oldCaminhao, newCaminhao);
            return repository.save(oldCaminhao);
        }  catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Caminhao oldCaminhao, Caminhao newCaminhao) {
        oldCaminhao.setCapacidade(newCaminhao.getCapacidade());
        oldCaminhao.setMotorista(newCaminhao.getMotorista());
        oldCaminhao.setPlaca(newCaminhao.getPlaca());
    }


}
