package com.hatrongtan99.app.entity.test;

import com.hatrongtan99.app.entity.test.StudentEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "group_table")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    @ManyToMany(mappedBy = "groups")
    private List<StudentEntity> students = new ArrayList<>();
}
