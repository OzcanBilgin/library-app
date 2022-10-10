package com.library.app.libraryapp.controllers;

import com.library.app.libraryapp.dto.LibraryRequestDto;
import com.library.app.libraryapp.dto.RestResponse;
import com.library.app.libraryapp.enums.StatusEnum;
import com.library.app.libraryapp.services.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "api/v1/library")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(RestResponse.of(libraryService.list(),StatusEnum.SUCCESS.getValue(),StatusEnum.SUCCESS,null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody LibraryRequestDto libraryRequestDTO) {
        libraryService.create(libraryRequestDTO);
        return new ResponseEntity<>(RestResponse.created(), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody LibraryRequestDto libraryRequestDTO) {
        libraryService.update(id,libraryRequestDTO);
        return new ResponseEntity<>(RestResponse.empty(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        libraryService.delete(id);
        return new ResponseEntity<>(RestResponse.empty(), HttpStatus.OK);
    }


}
