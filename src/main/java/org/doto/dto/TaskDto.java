package org.doto.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.doto.entity.TaskStatus;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class TaskDto {
    private String title;
    private String description;
    private Timestamp creationDate;
    private Timestamp deadline;
    private TaskStatus status;
    private Integer userId;
}
