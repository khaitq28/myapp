package com.tqk.myapp.interfaces;

import com.tqk.myapp.application.PartnerService;
import com.tqk.myapp.domain.Partner;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Quang-Khai TRAN
 * @date 03/04/2025
 */

@RestController
@AllArgsConstructor
@RequestMapping("/api/partners")
public class PartnerResources {

     private final PartnerService partnerService;

     @GetMapping
     public Page<Partner> getPartners(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "alias") String sortBy,
                                      @RequestParam(defaultValue = "asc") String sortDir) {

         Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
         Pageable pageable = PageRequest.of(page, size, sort);
         return partnerService.getPartners(pageable);
     }

     @PostMapping
     public ResponseEntity<Void> savePartner(@RequestBody @Valid Partner partner) {
         partnerService.save(partner);
         return ResponseEntity.status(HttpStatus.CREATED).build();
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deletePartner(@PathVariable Long id) {
        partnerService.delete(id);
        return ResponseEntity.noContent().build();
     }

}
