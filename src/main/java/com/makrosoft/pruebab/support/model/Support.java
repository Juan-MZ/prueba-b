package com.makrosoft.pruebab.support.model;

import com.makrosoft.pruebab.support.model.enums.statusEnum;
import com.makrosoft.pruebab.worker.model.Worker;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="support")
public class Support {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "support_sec_seq")
    @SequenceGenerator(name = "support_sec_seq", sequenceName = "support_sec_seq", allocationSize = 1)
    @Column(name = "support_sec")
    private Integer supportSec;
    @Column(name = "description")
    private String supportDescription;
    @Column(name = "complexity")
    private Integer supportComplexity;
    @Enumerated(EnumType.STRING) // Mapea el enum como una cadena en la base de datos
    @Column(name = "status", length = 20)
    private statusEnum supportStatus;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "worker", nullable = true)
    private Worker worker;
}
