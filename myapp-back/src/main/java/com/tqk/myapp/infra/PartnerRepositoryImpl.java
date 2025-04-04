package com.tqk.myapp.infra;

import com.tqk.myapp.domain.Partner;
import com.tqk.myapp.domain.repository.PartnerRepository;
import com.tqk.myapp.infra.persistance.PartnerEntity;
import com.tqk.myapp.infra.persistance.PartnerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PartnerRepositoryImpl implements PartnerRepository {

    private final PartnerJpaRepository partnerJpaRepository;

    @Override
    public void save(Partner partner) {
        partnerJpaRepository.save(toEntity(partner));
    }

    @Override
    public void saveAll(List<Partner> messages) {
        partnerJpaRepository.saveAll(messages.stream().map(this::toEntity).collect(Collectors.toList()));
    }

    private PartnerEntity toEntity(Partner partner) {
        return
                PartnerEntity.builder()
                .alias(partner.getAlias())
                .processedFlowType(partner.getProcessedFlowType())
                .description(partner.getDescription())
                .type(partner.getType())
                .application(partner.getApplication())
                .direction(partner.getDirection())
                .build();
    }

    @Override
    public Page<Partner> findAll(Pageable pageable) {
        return partnerJpaRepository.findAll(pageable)
                .map(m ->
                        Partner.builder()
                                .id(m.getId())
                                .alias(m.getAlias())
                                .processedFlowType(m.getProcessedFlowType())
                                .description(m.getDescription())
                                .type(m.getType())
                                .application(m.getApplication())
                                .direction(m.getDirection())
                                .build());
    }

    @Override
    public boolean existsById(Long id) {
        return partnerJpaRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        partnerJpaRepository.deleteById(id);
    }

}
