package com.example.Practice24.services;

import com.example.Practice24.models.Departure;
import com.example.Practice24.models.PostOffice;
import com.example.Practice24.repositories.DepartureRepository;
import com.example.Practice24.repositories.PostOfficeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class DepartureService {
    private DepartureRepository departureRepository;
    private PostOfficeRepository postOfficeRepository;
    private EmailService emailService;

    public void save(Departure departure, int postOfficeId) {
        log.info("Saving Departure");
        Optional<PostOffice> postOffice = postOfficeRepository.findById(postOfficeId);
        if(!postOffice.isPresent())
            return;
        emailService.sendSimpleMessage("Saved new departure", departure.getId().toString());
        departure.setPostOffice(postOffice.get());
        departureRepository.save(departure);

    }

    public void delete(int id){
        log.info("deleting departure {}", id);
        departureRepository.deleteById(id);
    }

    public List<Departure> getSortedDeparturesByField(String fieldName) {
        log.info("returning departures sorted by {}", fieldName);
        return departureRepository.findAll(Sort.by(fieldName));
    }

    public List<Departure> getAll() {
        log.info("returning all departures");
        return departureRepository.findAll();
    }

    public List<Departure> getAllDeparturesByPostOfficeId(int postOfficeId){
        log.info("returning all departures of PO: {}", postOfficeId);
        return departureRepository.findAllByPostOfficeId(postOfficeId);
    }

    @Autowired
    public void setDepartureRepository(DepartureRepository departureRepository) {
        log.info("Setting departure repository");
        this.departureRepository = departureRepository;
    }
    @Autowired
    public void setPostOfficeRepository(PostOfficeRepository postOfficeRepository) {
        log.info("setting PO repository");
        this.postOfficeRepository = postOfficeRepository;
    }
    @Autowired
    public void setEmailService(EmailService emailService) {
        log.info("setting email service for Departure service");
        this.emailService = emailService;
    }
}
