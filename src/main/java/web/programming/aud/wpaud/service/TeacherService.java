package web.programming.aud.wpaud.service;

import web.programming.aud.wpaud.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    public List<Teacher> findAll();

    public Optional<Teacher> findById(Long id);
}
