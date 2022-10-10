package com.library.app.libraryapp.services;

import com.library.app.libraryapp.dto.LibraryRequestDto;
import com.library.app.libraryapp.dto.LibraryResponseDto;
import com.library.app.libraryapp.dto.convert.LibraryMapper;
import com.library.app.libraryapp.entities.Library;
import com.library.app.libraryapp.dao.LibraryDao;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService{

    private final LibraryDao libraryDao;

    private final LibraryMapper libraryMapper;

    public LibraryServiceImpl(LibraryDao libraryDao, LibraryMapper libraryMapper) {
        this.libraryDao = libraryDao;
        this.libraryMapper = libraryMapper;
    }

    @Override
    public void create(LibraryRequestDto libraryRequestDto) {
        libraryDao.create(libraryMapper.libraryRequestDTOToLibrary(libraryRequestDto));
    }

    @Override
    public void update(String id,LibraryRequestDto libraryRequestDTO) {
        Library library = libraryDao.findLibraryById(id)
               .orElseThrow(()->new NullPointerException());
        libraryDao.update(libraryMapper.updateLibraryFromDTO(libraryRequestDTO,library));
    }

    @Override
    public void delete(String id) {
        Library library = libraryDao.findLibraryById(id)
                .orElseThrow(()->new NullPointerException());
        libraryDao.delete(library);
    }

    @Override
    public List<LibraryResponseDto> list() {
        List<Library> libraries = libraryDao.list();
        return libraryMapper.libraryListToLibraryResponseDTO(libraries);
    }
}
