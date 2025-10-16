package cristinamastellaro.BE_U2_S2_G2.controllers;

import cristinamastellaro.BE_U2_S2_G2.entities.Blog;
import cristinamastellaro.BE_U2_S2_G2.exceptions.ValidationBodyException;
import cristinamastellaro.BE_U2_S2_G2.payloads.BlogPayload;
import cristinamastellaro.BE_U2_S2_G2.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public Page<Blog> findAllBlogs(@RequestParam(defaultValue = "0") int numPages,
                                   @RequestParam(defaultValue = "5") int elPerPages,
                                   @RequestParam(defaultValue = "titolo") String sortBy) {
        return blogService.findAll(numPages, elPerPages, sortBy);
    }

    @GetMapping("/{blogId}")
    public Blog findBlog(@PathVariable UUID blogId) {
        return blogService.findBlogById(blogId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Blog createBlog(@RequestBody @Validated BlogPayload newBlog, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new ValidationBodyException(validation.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList());
        }
        return blogService.saveBlog(newBlog);
    }

    @PutMapping("/{blogId}")
    public Blog updateBlog(@PathVariable UUID blogId, @RequestBody @Validated BlogPayload newInfo, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new ValidationBodyException(validation.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList());
        }
        return blogService.findAndUpdateBlog(blogId, newInfo);
    }

    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable UUID blogId) {
        blogService.deleteBlog(blogId);
    }

    @PatchMapping("/{blogId}/cover")
    public Blog uploadCoverBlog(@PathVariable UUID blogId, @RequestParam("cover") MultipartFile file) {
        return blogService.updateCoverBlog(blogId, file);
    }
}
