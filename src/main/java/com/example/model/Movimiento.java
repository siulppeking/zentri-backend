package com.example.model;

import lombok.Getter;
import lombok.Setter;
import com.example.iam.model.Usuario;

import java.math.BigDecimal;

@Getter
@Setter
public class Movimiento {

    private Long idMovimiento;
    private Usuario usuario;
    private Categoria categoria;
    private String descripcion;
    private BigDecimal monto;
    private TipoTransaccion tipo;
    private String fecMovimiento;
    private String fecCre;
    private String fecMod;

}
