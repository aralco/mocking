package bo.edu.umss.programming.mocking.mock;

import bo.edu.umss.programming.mocking.domain.Personnel;
import bo.edu.umss.programming.mocking.repository.PersonnelRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class MockPersonnelRepository {
//public class MockPersonnelRepository<T extends Personnel> extends PersonnelRepository<Personnel, String> {
    /*@Override
    public Personnel findPersonnelByFullName(String fullName) {
        return null;
    }

    @Override
    public Personnel findPersonnelByNationalID(String nationalID) {
        return null;
    }

    @Override
    public List<Personnel> findAll() {
        return null;
    }

    @Override
    public List<Personnel> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<Personnel> findAllById(Iterable<String> iterable) {
        return null;
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends T> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Personnel> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Personnel getOne(String s) {
        return null;
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public Page<Personnel> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends T> S save(S entity) {
        return null;
    }

    @Override
    public Optional<Personnel> findById(String s) {
        return null;
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Personnel entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Personnel> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return null;
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return false;
    }*/
}
