package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_palestra")
public class PalestraEntity extends PanacheEntityBase {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID palestraId;

    public String titulo;
    public String resumo;
    public String nomeAutor;
    public String email;
}
