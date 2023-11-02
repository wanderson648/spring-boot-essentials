package com.wo.springboot2.service;

import com.wo.springboot2.domain.Anime;
import com.wo.springboot2.exception.BadRequestException;
import com.wo.springboot2.mapper.AnimeMapper;
import com.wo.springboot2.repository.AnimeRepository;
import com.wo.springboot2.requests.AnimePostRequestBody;
import com.wo.springboot2.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;

    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findByIdOrThrowBadRequestException(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
//        Anime anime = AnimeMapper.INSTANCE.toAnime(animePostRequestBody);
        Anime anime = Anime.builder().name(animePostRequestBody.getName()).build();
        return animeRepository.save(anime);
    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
//        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        Anime anime = Anime.builder()
                .id(animePutRequestBody.getId())
                .name(animePutRequestBody.getName())
                .build();

        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }
}
