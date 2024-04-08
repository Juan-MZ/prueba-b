package com.makrosoft.pruebab.worker.model;

import com.makrosoft.pruebab.support.model.Support;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "worker_sec_seq")
    @SequenceGenerator(name = "worker_sec_seq", sequenceName = "worker_sec_seq", allocationSize = 1)
    @Column(name = "worker_sec")
    private Integer workerSec;
    @Column(name = "identification")
    private String identification;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "worker")
    private List<Support> supports;

    public Worker(){
        this.supports = new ArrayList<>();
    }
}
