package com.pimpos.patientmanagement.repository;

import com.pimpos.patientmanagement.model.ResponsiblePatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResponsiblePatientRepository extends JpaRepository<ResponsiblePatientModel, UUID> {
    boolean existsByCpf(String cpf);
}
