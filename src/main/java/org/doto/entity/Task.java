package org.doto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "task")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String  description;
    @Column(name = "creationDate")
    private Timestamp creationDate;
    @Column(name = "deadline")
    private Timestamp deadline;
    @Column(name = "status")
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
