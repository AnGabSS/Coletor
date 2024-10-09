package com.fiap.microsservice.coletor.service;

import com.fiap.microsservice.coletor.entities.Coleta;
import com.fiap.microsservice.coletor.entities.DTO.ColetaDTO;
import com.fiap.microsservice.coletor.repositories.CaminhaoRepository;
import com.fiap.microsservice.coletor.repositories.ColetaRepository;
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
public class ColetaService {

    @Autowired
    private ColetaRepository repository;

    @Autowired
    private CaminhaoRepository caminhaoRepository;

    @Autowired
    private RotaRepository rotaRepository;


    public Coleta insert(Coleta coleta) {
        return repository.save(coleta);
    }

    public Coleta getColeta(Long id) {
        Optional<Coleta> coleta = repository.findById(id);
        return coleta.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Coleta> getAllColeta() {
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

    public Coleta update(Long id, Coleta newCaminhao) {
        try{
            Coleta oldCaminhao = repository.getReferenceById(id);
            updateData(oldCaminhao, newCaminhao);
            return repository.save(oldCaminhao);
        }  catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Coleta oldColeta, Coleta newColeta) {
        oldColeta.setCaminhao(newColeta.getCaminhao());
        oldColeta.setHora(newColeta.getHora());
        oldColeta.setRota(newColeta.getRota());
    }

    public Coleta fromDTO(ColetaDTO coletaDTO){
        Coleta coleta = new Coleta(null, coletaDTO.getHora(), caminhaoRepository.findById(coletaDTO.getCaminhao_id()).get(), rotaRepository.findById(coletaDTO.getRota_id()).get());
        return coleta;
    }


}
