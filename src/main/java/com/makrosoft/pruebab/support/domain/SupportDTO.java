package com.makrosoft.pruebab.support.domain;

import com.makrosoft.pruebab.worker.domain.WorkerDTO;
import com.makrosoft.pruebab.worker.model.Worker;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupportDTO {
    private Integer supportSec;
    private String supportDescription;
    private Integer supportComplexity;
    private WorkerDTO worker;
}
