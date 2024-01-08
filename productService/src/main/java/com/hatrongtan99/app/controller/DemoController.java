package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.entity.test.GroupEntity;
import com.hatrongtan99.app.entity.test.StudentEntity;
import com.hatrongtan99.app.repository.test.GroupRepo;
import com.hatrongtan99.app.repository.test.StudentRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
@AllArgsConstructor
public class DemoController {
    private StudentRepo studentRepo;
    private GroupRepo groupRepo;

    @PostMapping("/create-student")
    public String createStudent(
            @RequestBody StudentEntity student
    ) {
        this.studentRepo.save(student);
        return "success";
    }

    @PostMapping("/create-group")
    public String createGroup(
            @RequestBody GroupEntity group
    ) {
        this.groupRepo.save(group);
        return "success";
    }

    @Getter
    @Setter
    private static class Join {
        private String studentId;
        private String groupId;
    }

    @PostMapping("join")
    @Transactional
    public String join(
            @RequestBody DemoController.Join join
    ) {
        StudentEntity student = this.studentRepo.findById(join.studentId).get();
        GroupEntity group = this.groupRepo.findById(join.getGroupId()).get();
        student.getGroups().add(group);
        this.studentRepo.save(student);
        return "success";
    }

    @DeleteMapping("/{idGroup}")
    @Transactional
    public String delete(
            @PathVariable("idGroup") String id
    ) {
//        List<StudentEntity> list = this.studentRepo.findAll();
//        list.forEach(student -> student.getGroups().remove(this.groupRepo.findById(id).get()));
//        List<StudentEntity> list = this.studentRepo.findAll();
//        list.forEach(gr -> gr.getGroups().remove(this.groupRepo.findById(id).get()));
        this.studentRepo.delete(this.studentRepo.findById(id).get());
        return "success";
    }

}
