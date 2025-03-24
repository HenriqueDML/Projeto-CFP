package org.acme.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.UriBuilderException;
import org.acme.entity.PalestraEntity;
import org.acme.exception.PalestraNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@ApplicationScoped

public class PalestraService {

    @Transactional
    public PalestraEntity createPalestra(PalestraEntity palestraEntity){
        PalestraEntity.persist(palestraEntity);
        return palestraEntity;
    }

    public List<PalestraEntity> findPalestra(Integer page, Integer pageSize){
        return PalestraEntity.findAll()
                .page(page, pageSize)
                .list();
    }

    public PalestraEntity findById(UUID palestraId){
        return (PalestraEntity) PalestraEntity.findByIdOptional(palestraId)
                .orElseThrow(PalestraNotFoundException::new);
    }

    public PalestraEntity updatePalestra(UUID palestraId, PalestraEntity palestraEntity){
        var palestra = findById(palestraId);
        palestra.setTitulo(palestraEntity.getTitulo());
        palestra.setResumo(palestraEntity.getResumo());
        palestra.setNomeAutor(palestraEntity.getNomeAutor());
        palestra.setEmail(palestraEntity.getEmail());
        PalestraEntity.persist(palestra);
        return palestra;
    }

    public void deleteById(UUID palestraId){
        var palestra = findById(palestraId);
        PalestraEntity.deleteById(palestra.getPalestraId());
    }
}
