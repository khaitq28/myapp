package com.tqk.myapp.application;

import com.tqk.myapp.domain.Partner;
import com.tqk.myapp.domain.repository.PartnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class PartnerService {

    private final PartnerRepository partnerRepository;

    public void saveAll(List<Partner> partners) {
        partnerRepository.saveAll(partners);
    }

    public void save(Partner partner) {
        partnerRepository.save(partner);
    }

    public Page<Partner> getPartners(Pageable pageable) {
        return partnerRepository.findAll(pageable);
    }

    public void delete(Long id) {
        if (!partnerRepository.existsById(id)) {
            throw new IllegalArgumentException("Partner not found");
        }
        partnerRepository.deleteById(id);
    }
}
