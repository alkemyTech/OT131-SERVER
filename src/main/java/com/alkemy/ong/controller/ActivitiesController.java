package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivitiesDTO;
import com.alkemy.ong.model.Activities;
import com.alkemy.ong.repository.ActivitiesRepository;
import com.alkemy.ong.service.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.alkemy.ong.util.Constants.*;

@RestController
@RequestMapping(REQ_MAPP_ACTIVITIES)
public class ActivitiesController {

    @Autowired
    private ActivitiesService activitiesService;

    @Autowired
    private ActivitiesRepository activitiesRepository;


    @GetMapping
    public ResponseEntity<List<ActivitiesDTO>> getAllActives() {
        return ResponseEntity.ok().body(activitiesService.getAllActives());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivitiesDTO> update(@PathVariable Long id, @Valid @RequestBody ActivitiesDTO dto) {
        ActivitiesDTO result = activitiesService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activitiesService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<?> createActivity(@Valid @RequestBody ActivitiesDTO activityDTO){

        return new ResponseEntity<>(activitiesService.save(activityDTO), HttpStatus.CREATED);

    }
}