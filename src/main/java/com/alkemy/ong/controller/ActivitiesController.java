package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivitiesDTO;
import com.alkemy.ong.services.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("activities")
public class ActivitiesController {

    @Autowired
    private ActivitiesService activitiesService;

    @GetMapping
    public ResponseEntity<List<ActivitiesDTO>> getAll() {

        List<ActivitiesDTO> activities = activitiesService.getAll();
        return ResponseEntity.ok().body(activities);

    }

    @PostMapping
    public ResponseEntity<ActivitiesDTO> save(@RequestBody ActivitiesDTO dto) {

        ActivitiesDTO updated = activitiesService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updated);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivitiesDTO> update(@PathVariable Long id, @RequestBody ActivitiesDTO dto) {

        ActivitiesDTO result = activitiesService.update(id, dto);
        return ResponseEntity.ok().body(result);

    }
}
