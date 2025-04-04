package com.tqk.myapp.domain.repository;

import com.tqk.myapp.domain.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PartnerRepository {

    void save(Partner partner);
    void saveAll(List<Partner> partners);
    Page<Partner> findAll(Pageable pageable);
    boolean existsById(Long id);
    void deleteById(Long id);
}
