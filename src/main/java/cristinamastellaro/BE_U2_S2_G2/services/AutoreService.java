package cristinamastellaro.BE_U2_S2_G2.services;

import cristinamastellaro.BE_U2_S2_G2.entities.Autore;
import cristinamastellaro.BE_U2_S2_G2.exceptions.IdNotFoundException;
import cristinamastellaro.BE_U2_S2_G2.payloads.AutorePayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AutoreService {

    private List<Autore> autori = new ArrayList<>();

    public List<Autore> findAll() {
        return autori;
    }

    public Autore findById(long id) {
        Autore found = autori.stream().filter(a -> a.getId() == id).toList().getFirst();
        if (found == null) throw new IdNotFoundException(id);
        return found;
    }

    public Autore saveAuthor(AutorePayload infoAuthor) {
        Autore newAutore = new Autore(infoAuthor.getNome(), infoAuthor.getCognome(), infoAuthor.getEmail(), infoAuthor.getDataDiNascita());
        autori.add(newAutore);
        return newAutore;
    }

    public Autore findByIdandUpdateAuthor(long id, AutorePayload newInfo) {
        Autore author = null;
        for (Autore autore : autori) {
            if (autore.getId() == id) {
                author = autore;
                author.setNome(newInfo.getNome());
                author.setCognome((newInfo.getCognome()));
                author.setEmail(newInfo.getEmail());
                author.setDataDiNascita(newInfo.getDataDiNascita());
            }
        }
        if (author == null) throw new IdNotFoundException(id);
        return author;
    }

    public void deleteByid(long id) {
        int startingLength = autori.size();
        for (Autore autore : autori) {
            if (autore.getId() == id) autori.remove(autore);
        }
        if (autori.size() == startingLength) throw new IdNotFoundException(id);
        else log.info("Autore con id " + id + " è stato cancellato correttamente!");
    }
}
