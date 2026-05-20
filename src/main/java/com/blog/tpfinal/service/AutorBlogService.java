package com.blog.tpfinal.service;

import com.blog.tpfinal.model.AutorBlog;
import com.blog.tpfinal.repository.AutorBlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutorBlogService {

    private final AutorBlogRepository autorBlogRepository;

    public List<AutorBlog> findAll() {
        return autorBlogRepository.findAll();
    }

    public Optional<AutorBlog> findById(Long id) {
        return autorBlogRepository.findById(id);
    }

    public AutorBlog save(AutorBlog autorBlog) {
        return autorBlogRepository.save(autorBlog);
    }

    public void deleteById(Long id) {
        autorBlogRepository.deleteById(id);
    }
}
