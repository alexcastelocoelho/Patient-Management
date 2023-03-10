package com.pimpos.patientmanagement.service;

import com.pimpos.patientmanagement.model.MedicalAppointmentModel;
import com.pimpos.patientmanagement.repository.MedicalAppointmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MedicalAppointmentService {
    final MedicalAppointmentRepository medicalAppointmentRepository;

    public MedicalAppointmentService(MedicalAppointmentRepository medicalAppointmentRepository) {
        this.medicalAppointmentRepository = medicalAppointmentRepository;
    }

    public MedicalAppointmentModel save(MedicalAppointmentModel medicalAppointmentModel) {
        return medicalAppointmentRepository.save(medicalAppointmentModel);
    }

    public Page<MedicalAppointmentModel> findAll(Pageable pageable) {
        return medicalAppointmentRepository.findAll(pageable);
    }

    public Optional<MedicalAppointmentModel> findById(UUID id) {
        return medicalAppointmentRepository.findById(id);
    }

    public void delete(MedicalAppointmentModel medicalAppointmentModel ) {
        medicalAppointmentRepository.delete(medicalAppointmentModel);
    }
}
