package com.library.app.libraryapp.dto.convert;

import com.library.app.libraryapp.dto.LibraryRequestDto;
import com.library.app.libraryapp.dto.LibraryResponseDto;
import com.library.app.libraryapp.entities.Library;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibraryMapper {
    public LibraryResponseDto libraryToLibraryResponseDTO(Library library){
        return new LibraryResponseDto(
                library.getId(),
                library.getBookName(),
                library.getAuthor()
        );
    }

    public Library libraryRequestDTOToLibrary(LibraryRequestDto libraryRequestDTO){
        return new Library(
                libraryRequestDTO.getId(),
                libraryRequestDTO.getBookName(),
                libraryRequestDTO.getAuthor()
        );
    }

    public List<LibraryResponseDto> libraryListToLibraryResponseDTO(List<Library> libraries) {
        return libraries.stream()
                .map(item -> libraryToLibraryResponseDTO(item))
                .collect(Collectors.toList());
    }

    public Library updateLibraryFromDTO(LibraryRequestDto libraryRequestDTO, Library library){
        library.setAuthor(libraryRequestDTO.getAuthor());
        library.setBookName(libraryRequestDTO.getBookName());
        return library;
    }


}
