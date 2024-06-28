package com.makrosoft.pruebab.support.model;

import com.makrosoft.pruebab.worker.model.Worker;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    @Column(name = "weight")
    private Integer supportWeight;
    @Column(name = "priority")
    private Integer supportPriority;
    @Column(name = "assigantion_date")
    private LocalDate supportAssignatedDate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "worker", nullable = true)
    private Worker worker;
}
