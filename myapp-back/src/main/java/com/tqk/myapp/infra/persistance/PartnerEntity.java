package com.tqk.myapp.infra.persistance;

import com.tqk.myapp.domain.Direction;
import com.tqk.myapp.domain.ProcessedFlowType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "partners")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String alias;

    @Column(nullable = false)
    private String type;

    @Enumerated(EnumType.STRING)
    @Column
    private Direction direction;

    @Column
    private String application;

    @Enumerated(EnumType.STRING)
    @Column(name = "processed_flow_type")
    private ProcessedFlowType processedFlowType;

    @Column(nullable = false)
    private String description;

}
