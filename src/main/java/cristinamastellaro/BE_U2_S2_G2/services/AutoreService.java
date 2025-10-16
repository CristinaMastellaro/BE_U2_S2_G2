package cristinamastellaro.BE_U2_S2_G2.services;

import cristinamastellaro.BE_U2_S2_G2.entities.Autore;
import cristinamastellaro.BE_U2_S2_G2.exceptions.EmailAlreadyUsedException;
import cristinamastellaro.BE_U2_S2_G2.exceptions.IdNotFoundException;
import cristinamastellaro.BE_U2_S2_G2.payloads.AutorePayload;
import cristinamastellaro.BE_U2_S2_G2.repositories.AutoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class AutoreService {

    @Autowired
    private AutoreRepository aRepo;

    public Page<Autore> findAll(int numPages, int elPerPage, String sortBy) {
        Pageable pageable = PageRequest.of(numPages, elPerPage, Sort.by(sortBy));
        return aRepo.findAll(pageable);
    }

    public Autore findById(UUID id) {
        Autore found = aRepo.findById(id).orElseThrow(() -> new IdNotFoundException(id));
        return found;
    }

    public Autore saveAuthor(AutorePayload infoAuthor) {
        if (aRepo.existsByEmail(infoAuthor.email())) throw new EmailAlreadyUsedException(infoAuthor.email());

        Autore newAutore = new Autore(infoAuthor.nome(), infoAuthor.cognome(), infoAuthor.email(), infoAuthor.dataDiNascita());
        aRepo.save(newAutore);
        log.info("Autore salvato!");
        return newAutore;
    }

    public Autore findByIdandUpdateAuthor(UUID id, AutorePayload newInfo) {
        Autore autore = findById(id);
        autore.setNome(newInfo.nome());
        autore.setCognome(newInfo.cognome());
        autore.setEmail(newInfo.email());
        autore.setDataDiNascita(newInfo.dataDiNascita());
        aRepo.save(autore);
        return autore;
    }

    public void deleteByid(UUID id) {
        Autore found = findById(id);
        aRepo.delete(found);
        System.out.println("Dati dell'autore cancellato!");
    }
}
