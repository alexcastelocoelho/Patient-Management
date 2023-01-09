package com.pimpos.patientmanagement.service;

import com.pimpos.patientmanagement.model.DoctorModel;
import com.pimpos.patientmanagement.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorService {
    final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public DoctorModel save(DoctorModel doctorModel) {
        return doctorRepository.save(doctorModel);
    }

    public List<DoctorModel> findAll() {
        return doctorRepository.findAll();
    }

    public Optional<DoctorModel> findById(UUID id) {
        return doctorRepository.findById(id);
    }

    public void delete(DoctorModel doctorModel) {
        doctorRepository.delete(doctorModel);
    }
}
