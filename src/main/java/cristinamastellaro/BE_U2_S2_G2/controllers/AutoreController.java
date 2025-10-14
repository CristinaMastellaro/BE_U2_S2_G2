package cristinamastellaro.BE_U2_S2_G2.controllers;

import cristinamastellaro.BE_U2_S2_G2.entities.Autore;
import cristinamastellaro.BE_U2_S2_G2.payloads.AutorePayload;
import cristinamastellaro.BE_U2_S2_G2.services.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AutoreController {
    @Autowired
    private AutoreService autoreService;

    @GetMapping
    public List<Autore> findAllAuthors() {
        return autoreService.findAll();
    }

    @GetMapping("/{authorId}")
    public Autore findAuthor(@PathVariable long authorId) {
        return autoreService.findById(authorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autore createAuthor(@RequestBody AutorePayload newAuthor) {
        return autoreService.saveAuthor(newAuthor);
    }

    @PutMapping("/{authorId}")
    public Autore updateAuthor(@PathVariable long authorId, @RequestBody AutorePayload newInfo) {
        return autoreService.findByIdandUpdateAuthor(authorId, newInfo);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable long authorId) {
        autoreService.deleteByid(authorId);
    }
}
