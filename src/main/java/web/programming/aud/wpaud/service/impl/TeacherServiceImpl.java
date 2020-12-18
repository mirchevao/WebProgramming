package web.programming.aud.wpaud.service.impl;

import org.springframework.stereotype.Service;
import web.programming.aud.wpaud.model.Teacher;
import web.programming.aud.wpaud.repository.impl.IMTeacherRepository;
import web.programming.aud.wpaud.repository.jpa.TeacherRepository;
import web.programming.aud.wpaud.service.TeacherService;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return this.teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> findById(Long id) {

        return teacherRepository.findById(id);
    }

}
