package com.makrosoft.pruebab.support.domain;

import com.makrosoft.pruebab.worker.domain.WorkerDTO;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupportDTO {
    private Integer supportSec;
    private String supportDescription;
    private Integer supportWeight;
    private Integer supportPriority;
    private LocalDate supportAssignatedDate;
    private WorkerDTO worker;
}
