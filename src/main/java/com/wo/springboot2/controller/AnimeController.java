package com.wo.springboot2.controller;

import com.wo.springboot2.domain.Anime;
import com.wo.springboot2.service.AnimeService;
import com.wo.springboot2.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("animes")
@RequiredArgsConstructor
public class AnimeController {
    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<List<Anime>> list() {
        log.info("[start] AnimeController - List");
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        log.info("[finished] AnimeController - List");
        return ResponseEntity.ok(animeService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id) {
        log.info("[start] AnimeController - findById");
        log.info("[finished] AnimeController - findById");
        return ResponseEntity.ok(animeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody Anime anime) {
        log.info("[start] AnimeController - save");
        log.info("[finished] AnimeController - save");
        return new ResponseEntity<>(animeService.save(anime), HttpStatus.CREATED);
    }
}
