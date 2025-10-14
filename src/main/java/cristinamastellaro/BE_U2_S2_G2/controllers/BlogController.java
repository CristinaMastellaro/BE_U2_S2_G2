package cristinamastellaro.BE_U2_S2_G2.controllers;

import cristinamastellaro.BE_U2_S2_G2.entities.Blog;
import cristinamastellaro.BE_U2_S2_G2.payloads.BlogPayload;
import cristinamastellaro.BE_U2_S2_G2.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<Blog> findAllBlogs() {
        return blogService.findAll();
    }

    @GetMapping("/{blogId}")
    public Blog findBlog(@PathVariable long blogId) {
        return blogService.findBlogById(blogId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Blog createBlog(@RequestBody BlogPayload newBlog) {
        return blogService.saveBlog(newBlog);
    }

    @PutMapping("/{blogId}")
    public Blog updateBlog(@PathVariable long blogId, @RequestBody BlogPayload newInfo) {
        return blogService.findAndUpdateBlog(blogId, newInfo);
    }

    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable long blogId) {
        blogService.deleteBlog(blogId);
    }
}
