package com.example.emtlab.service.domain.impl;

import com.example.emtlab.model.domain.Guest;
import com.example.emtlab.model.domain.Host;
import com.example.emtlab.repository.GuestRepository;
import com.example.emtlab.repository.HostRepository;
import com.example.emtlab.service.domain.CountryService;
import com.example.emtlab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final GuestRepository guestRepository;
    private final CountryService countryService;

    public HostServiceImpl(HostRepository hostRepository, GuestRepository guestRepository, CountryService countryService) {
        this.hostRepository = hostRepository;
        this.guestRepository = guestRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }

    @Override
    public Optional<Host> save(Host host) {
        if (host.getCountry() != null &&
                countryService.findById(host.getCountry().getId()).isPresent()) {
            return Optional.of(
                    hostRepository.save(new Host(
                            host.getName(), host.getSurname(),
                            countryService.findById(host.getCountry().getId()).get()
                    )));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return hostRepository.findById(id).map(existingHost -> {
            if (host.getName() != null) {
                existingHost.setName(host.getName());
            }
            if (host.getSurname() != null) {
                existingHost.setSurname(host.getSurname());
            }
            if (host.getCountry() != null && countryService.findById(host.getCountry().getId()).isPresent()) {
                existingHost.setCountry(countryService.findById(host.getCountry().getId()).get());
            }
            return hostRepository.save(existingHost);
        });
    }

    @Override
    public Optional<Host> addGuest(Long id, Guest guest) {
        return hostRepository.findById(id).map(existingHost -> {
            if (guest != null &&
                    guestRepository.findById(guest.getId()).isPresent()) {
                        existingHost.getHistoryOfGuests().add(guest);
            }
            return hostRepository.save(existingHost);
        });
    }

    @Override
    public List<Guest> findAllGuests(Long id) {
        return hostRepository.findById(id).get()
                .getHistoryOfGuests().stream().toList();
    }

    @Override
    public void deleteById(Long id) {
        hostRepository.deleteById(id);
    }
}
