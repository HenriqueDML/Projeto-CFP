package org.acme.service;


import io.quarkus.hibernate.orm.panache.Panache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.UriBuilderException;
import org.acme.entity.PalestraEntity;
import org.acme.exception.PalestraNotFoundException;
import io.quarkus.hibernate.orm.panache.Panache;

import java.util.HashMap;
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


    public List<PalestraEntity> buscarComFiltros(String titulo, String nomeAutor, String email) {
        var query = new StringBuilder("FROM PalestraEntity WHERE 1=1");
        var params = new HashMap<String, Object>();

        if (titulo != null && !titulo.isBlank()) {
            query.append(" AND LOWER(titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))");
            params.put("titulo", titulo);
        }
        if (nomeAutor != null && !nomeAutor.isBlank()) {
            query.append(" AND LOWER(nomeAutor) LIKE LOWER(CONCAT('%', :nomeAutor, '%'))");
            params.put("nomeAutor", nomeAutor);
        }
        if (email != null && !email.isBlank()) {
            query.append(" AND LOWER(email) LIKE LOWER(CONCAT('%', :email, '%'))");
            params.put("email", email);
        }

        var em = Panache.getEntityManager();
        var typedQuery = em.createQuery(query.toString(), PalestraEntity.class);
        params.forEach(typedQuery::setParameter);

        return typedQuery.getResultList();
    }

}
