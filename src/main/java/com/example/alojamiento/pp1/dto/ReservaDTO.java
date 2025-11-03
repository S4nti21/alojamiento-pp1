package com.example.alojamiento.pp1.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ReservaDTO {
    private Long usuarioId;
    private Long alojamientoId;
    private LocalDateTime fecha_check_in;
    private LocalDateTime fecha_check_out;
}
