package cristinamastellaro.BE_U2_S2_G2.controllers;

import cristinamastellaro.BE_U2_S2_G2.entities.Autore;
import cristinamastellaro.BE_U2_S2_G2.exceptions.ValidationBodyException;
import cristinamastellaro.BE_U2_S2_G2.payloads.AutorePayload;
import cristinamastellaro.BE_U2_S2_G2.services.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
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
    public Autore createAuthor(@RequestBody @Validated AutorePayload newAuthor, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new ValidationBodyException(validation.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList());
        }
        return autoreService.saveAuthor(newAuthor);
    }

    @PutMapping("/{authorId}")
    public Autore updateAuthor(@PathVariable UUID authorId, @RequestBody @Validated AutorePayload newInfo, BindingResult validation) {
        if (validation.hasErrors()) {
            List<String> errors = new ArrayList<>();
            validation.getFieldErrors().forEach(e -> errors.add(e.getDefaultMessage()));
            throw new ValidationBodyException(errors);
        }
        return autoreService.findByIdandUpdateAuthor(authorId, newInfo);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable UUID authorId) {
        autoreService.deleteByid(authorId);
    }

    @PatchMapping("/{authorId}/avatar")
    public Autore updateAvatarAuthor(@PathVariable UUID authorId, @RequestParam("avatar") MultipartFile file) {
        return autoreService.uploadAvatarAtuhor(authorId, file);
    }
}
