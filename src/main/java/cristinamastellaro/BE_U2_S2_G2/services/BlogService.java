package cristinamastellaro.BE_U2_S2_G2.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import cristinamastellaro.BE_U2_S2_G2.entities.Autore;
import cristinamastellaro.BE_U2_S2_G2.entities.Blog;
import cristinamastellaro.BE_U2_S2_G2.exceptions.AuthorAlreadyWrittenBlogException;
import cristinamastellaro.BE_U2_S2_G2.exceptions.IdNotFoundException;
import cristinamastellaro.BE_U2_S2_G2.exceptions.NoFileException;
import cristinamastellaro.BE_U2_S2_G2.payloads.BlogPayload;
import cristinamastellaro.BE_U2_S2_G2.repositories.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class BlogService {
    @Autowired
    private BlogRepository bRepo;
    @Autowired
    private AutoreService aService;
    @Autowired
    private Cloudinary imageUploader;

    public Blog saveBlog(BlogPayload addBlog) {
        // Intanto verifichiamo che l'autore non abbia già scritto altri blog
        Autore autore = aService.findById(addBlog.autoreId());
        boolean isAuthorAlreadyPresent = bRepo.existsByAutore(autore);
        if (isAuthorAlreadyPresent) throw new AuthorAlreadyWrittenBlogException(addBlog.autoreId());

        Blog newBlog = new Blog(addBlog.categoria(), addBlog.titolo(), addBlog.contenuto(), addBlog.tempoDiLettura(), autore);
        bRepo.save(newBlog);
        log.info("Il blog dal titolo " + newBlog.getTitolo() + " è stato salvato correttamente!");
        return newBlog;
    }

    public Page<Blog> findAll(int numPages, int elPerPage, String sortBy) {
        Pageable pageable = PageRequest.of(numPages, elPerPage, Sort.by(sortBy));
        return bRepo.findAll(pageable);
    }

    public Blog findBlogById(UUID id) {
        return bRepo.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }

    public Blog findAndUpdateBlog(UUID id, BlogPayload newInfo) {
        Blog found = findBlogById(id);
        System.out.println("Id autore blog: " + newInfo.autoreId());
        Autore autore = aService.findById(newInfo.autoreId());
        found.setCategoria(newInfo.categoria());
        found.setTitolo(newInfo.titolo());
        found.setContenuto(newInfo.contenuto());
        found.setTempoDiLettura(newInfo.tempoDiLettura());
        found.setAutore(autore);
        bRepo.save(found);
        return found;
    }

    public void deleteBlog(UUID id) {
        Blog found = findBlogById(id);
        bRepo.delete(found);
        System.out.println("Blog cancellato!");
    }

    public Blog updateCoverBlog(UUID id, MultipartFile file) {
        Blog blog = findBlogById(id);
        if (file.isEmpty()) throw new NoFileException();

        try {
            Map result = imageUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("url");
            blog.setCover(url);
            bRepo.save(blog);
            return blog;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
