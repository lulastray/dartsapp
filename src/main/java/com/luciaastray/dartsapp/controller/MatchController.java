package com.luciaastray.dartsapp.controller;

import com.luciaastray.dartsapp.model.match.Match;
import com.luciaastray.dartsapp.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/all")
    public List<Match> getAll() { return matchService.findAll();}

    @GetMapping("/{id}")
    public Match getOne(@PathVariable String id) { return matchService.findOne(id);}

    @PostMapping("/new")
    public void create(@RequestBody Match match) { matchService.create(match);}
}
