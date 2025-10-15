package cristinamastellaro.BE_U2_S2_G2.controllers;

import cristinamastellaro.BE_U2_S2_G2.entities.Autore;
import cristinamastellaro.BE_U2_S2_G2.payloads.AutorePayload;
import cristinamastellaro.BE_U2_S2_G2.services.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AutoreController {
    @Autowired
    private AutoreService autoreService;

    @GetMapping
    public Page<Autore> findAllAuthors(
            @RequestParam(defaultValue = "0") int pages,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "nome") String sortBy
    ) {
        return autoreService.findAll(pages, size, sortBy);
    }

    @GetMapping("/{authorId}")
    public Autore findAuthor(@PathVariable UUID authorId) {
        return autoreService.findById(authorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Autore createAuthor(@RequestBody AutorePayload newAuthor) {
        return autoreService.saveAuthor(newAuthor);
    }

    @PutMapping("/{authorId}")
    public Autore updateAuthor(@PathVariable UUID authorId, @RequestBody AutorePayload newInfo) {
        return autoreService.findByIdandUpdateAuthor(authorId, newInfo);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable UUID authorId) {
        autoreService.deleteByid(authorId);
    }
}
