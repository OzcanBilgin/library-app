package com.library.app.libraryapp.services;

import com.library.app.libraryapp.dto.LibraryRequestDto;
import com.library.app.libraryapp.dto.LibraryResponseDto;
import java.util.List;

public interface LibraryService {

     void create(LibraryRequestDto person);

     void update(String id, LibraryRequestDto person);

     void delete(String id);

     List<LibraryResponseDto> list();

}
