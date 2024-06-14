package com.blaaxo.RecursosHumanos.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmpleadoDTO {

    @NonNull
    private String nombre;

    @NonNull
    private String departamento;

    @NonNull
    private Float sueldo;
}
