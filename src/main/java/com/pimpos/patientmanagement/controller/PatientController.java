package com.pimpos.patientmanagement.controller;

import com.pimpos.patientmanagement.dto.PatientDto;
import com.pimpos.patientmanagement.model.PatientModel;
import com.pimpos.patientmanagement.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/patients")
public class PatientController {
    final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid PatientDto patientDto) {
        if(patientService.existsByCpf(patientDto.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: cpf is already in use");
        }
        PatientModel patientModel = new PatientModel();
        BeanUtils.copyProperties(patientDto, patientModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.save(patientModel));
    }

    @GetMapping
    public ResponseEntity<Page<PatientModel>> list(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> listOne(@PathVariable(value = "id") UUID id) {
        Optional<PatientModel> patientOptional = patientService.findById(id);
        if (!patientOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("patient not found");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(patientOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
        Optional<PatientModel> patientModelOptional = patientService.findById(id);
        if (!patientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        }
        patientService.delete(patientModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Patient deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody @Valid PatientDto patientDto) {
        Optional<PatientModel> patientModelOptional = patientService.findById(id);
        if (!patientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        }
        var patientModel = new PatientModel();
        BeanUtils.copyProperties(patientDto, patientModel);
        patientModel.setId(patientModelOptional.get().getId());
        patientModel.setCpf(patientModelOptional.get().getCpf());
        return ResponseEntity.status(HttpStatus.OK).body(patientService.save(patientModel));
    }


}
