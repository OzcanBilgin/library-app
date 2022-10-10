package com.library.app.libraryapp.dao;

import com.library.app.libraryapp.entities.Library;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("LibraryDao")
public class LibraryDaoImp implements LibraryDao {

    private static final String COLLECTION_NAME = "library";

    private final MongoTemplate mongoTemplate;

    public LibraryDaoImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void create(Library library) {
        if (!mongoTemplate.collectionExists(Library.class)) {
            mongoTemplate.createCollection(Library.class);
        }
        mongoTemplate.insert(library, COLLECTION_NAME);
    }

    @Override
    public void update(Library library) {
        mongoTemplate.save(library, COLLECTION_NAME);
    }

    @Override
    public void delete(Library library) {
        mongoTemplate.remove(library, COLLECTION_NAME);
    }

    @Override
    public List<Library> list() {
        return mongoTemplate.findAll(Library.class, COLLECTION_NAME);
    }

    @Override
    public Optional<Library> findLibraryById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, Library.class));
    }
}
