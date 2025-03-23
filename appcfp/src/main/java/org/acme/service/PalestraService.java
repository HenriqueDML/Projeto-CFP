package org.acme.service;


import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.PalestraEntity;

import java.util.List;

@ApplicationScoped

public class PalestraService {

    public PalestraEntity createPalestra(PalestraEntity palestraEntity){
        PalestraEntity.persist(palestraEntity);
        return palestraEntity;
    }

    public List<PalestraEntity> findPalestra(Integer page, Integer pageSize){
        return PalestraEntity.findAll()
                .page(page, pageSize)
                .list();
    }
}
