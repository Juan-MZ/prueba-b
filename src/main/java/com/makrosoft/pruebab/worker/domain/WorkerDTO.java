package com.makrosoft.pruebab.worker.domain;

import com.makrosoft.pruebab.support.domain.SupportDTO;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkerDTO {
    private Integer workerSec;
    private String identification;
    private String name;
}
