package com.library.app.libraryapp.dao;

import com.library.app.libraryapp.entities.Library;
import java.util.List;
import java.util.Optional;

public interface LibraryDao {

     void create(Library library);

     void update(Library library);

     void delete(Library library);

     List<Library> list();

     Optional<Library> findLibraryById(String id);
}
