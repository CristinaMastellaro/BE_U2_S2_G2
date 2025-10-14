package cristinamastellaro.BE_U2_S2_G2.services;

import cristinamastellaro.BE_U2_S2_G2.entities.Blog;
import cristinamastellaro.BE_U2_S2_G2.exceptions.IdNotFoundException;
import cristinamastellaro.BE_U2_S2_G2.payloads.BlogPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BlogService {
    private List<Blog> blogs = new ArrayList<>();

    public Blog saveBlog(BlogPayload addBlog) {
        Blog newBlog = new Blog(addBlog.getCategoria(), addBlog.getTitolo(), addBlog.getContenuto(), addBlog.getTempoDiLettura(), addBlog.getCover());
        blogs.add(newBlog);
        log.info("Il blog dal titolo " + newBlog.getTitolo() + " è stato salvato correttamente!");
        return newBlog;
    }

    public List<Blog> findAll() {
        return blogs;
    }

    public Blog findBlogById(long id) {
        Blog found = blogs.stream().filter(b -> b.getId() == id).toList().getFirst();
        if (found == null) throw new IdNotFoundException(id);
        return found;
    }

    public Blog findAndUpdateBlog(long id, BlogPayload newInfo) {
        Blog found = null;
        for (Blog blog : blogs) {
            if (blog.getId() == id) {
                found = blog;
                found.setCategoria(newInfo.getCategoria());
                found.setContenuto(newInfo.getContenuto());
                found.setTitolo(newInfo.getTitolo());
                found.setTempoDiLettura(newInfo.getTempoDiLettura());
            }
        }
        if (found == null) throw new IdNotFoundException(id);
        return found;
    }

    public void deleteBlog(long id) {
        Blog found = null;
        for (Blog blog : blogs) {
            if (blog.getId() == id) {
                found = blog;
                blogs.remove(blog);
            }
        }
        if (found == null) throw new IdNotFoundException(id);
        else log.info("Il blog con id " + id + " è stato eliminato!");
    }
}
