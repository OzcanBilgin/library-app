package com.library.app.libraryapp;

import com.library.app.libraryapp.dao.LibraryDao;
import com.library.app.libraryapp.dto.LibraryRequestDto;
import com.library.app.libraryapp.dto.LibraryResponseDto;
import com.library.app.libraryapp.dto.convert.LibraryMapper;
import com.library.app.libraryapp.entities.Library;
import com.library.app.libraryapp.services.LibraryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceTest {
    @Mock
    private LibraryDao libraryDao;

    @InjectMocks
    private LibraryServiceImpl libraryService;

    @Mock
    private LibraryMapper libraryMapper;

    @Before
    public void setup() {
        System.out.println("************** START ************");
    }


    @Test
    public void whenCreateCalledWithValidRequest_itShouldReturnValid() {
        LibraryRequestDto libraryRequestDto = new LibraryRequestDto(null, "TEST", "TEST");
        Library returnEntityLibrary = new Library(null, "TEST", "TEST");

        when(libraryMapper.libraryRequestDTOToLibrary(libraryRequestDto)).thenReturn(returnEntityLibrary);

        libraryService.create(libraryRequestDto);
        verify(libraryDao, times(1)).create(returnEntityLibrary);
    }

    @Test
    public void whenUpdateCalledWithLibraryId_itShouldReturnValid() {
        final String id = "6342f04a31359159b2419a42";

        LibraryRequestDto libraryRequestDto = new LibraryRequestDto(id, "TEST1", "TEST1");
        Library returnEntityLibrary = new Library(id, "TEST", "TEST");
        Library convertEntity = new Library(id, "TEST1", "TEST1");

        when(libraryDao.findLibraryById(id)).thenReturn(Optional.of(returnEntityLibrary));
        when(libraryMapper.updateLibraryFromDTO(libraryRequestDto,returnEntityLibrary)).thenReturn(convertEntity);

        libraryService.update(id, libraryRequestDto);

        verify(libraryDao, times(1)).update(convertEntity);
    }

    @Test
    public void whenDeleteCalledWithLibraryId_itShouldReturnValid() {

        final String id = "6342f04a31359159b2419a42";

        Library findOne = new Library(id, "TEST", "TEST");

        when(libraryDao.findLibraryById(id)).thenReturn(Optional.of(findOne));
        libraryService.delete(id);

        verify(libraryDao, times(1)).delete(findOne);
    }

    @Test
    public void whenListCalled_itShouldReturnValid() {

        List<Library> libraryList = new ArrayList<Library>();
        Library libOne = new Library("6342f04a31359159b2419a42", "TEST", "TEST");
        libraryList.add(libOne);

        List<LibraryResponseDto> libraryResponseDtoList = new ArrayList<>();
        LibraryResponseDto libraryResponseDto = new LibraryResponseDto("6342f04a31359159b2419a42", "TEST", "TEST");
        libraryResponseDtoList.add(libraryResponseDto);

        when(libraryDao.list()).thenReturn(libraryList);

        when(libraryMapper.libraryListToLibraryResponseDTO(libraryList)).thenReturn(libraryResponseDtoList);

        libraryService.list();

        verify(libraryDao, times(1)).list();

    }
}
