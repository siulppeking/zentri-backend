package com.example.model;

import lombok.Getter;
import lombok.Setter;
import com.example.iam.model.Usuario;

@Getter
@Setter
public class Categoria {

    private Long idCategoria;
    private Usuario usuario;
    private String nombre;
    private String fecCre;
    private String fecMod;

}
