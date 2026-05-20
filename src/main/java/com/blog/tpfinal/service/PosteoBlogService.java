package com.blog.tpfinal.service;

import com.blog.tpfinal.model.PosteoBlog;
import com.blog.tpfinal.repository.PosteoBlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PosteoBlogService {

    private final PosteoBlogRepository posteoBlogRepository;

    public List<PosteoBlog> findAll() {
        return posteoBlogRepository.findAll();
    }

    public Optional<PosteoBlog> findById(Long id) {
        return posteoBlogRepository.findById(id);
    }

    public PosteoBlog save(PosteoBlog posteoBlog) {
        return posteoBlogRepository.save(posteoBlog);
    }

    public void deleteById(Long id) {
        posteoBlogRepository.deleteById(id);
    }
}
