package org.doto.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.doto.entity.TaskStatus;

@Getter
@Setter
@NoArgsConstructor
public class StatusDto {
    private TaskStatus taskStatus;
}
