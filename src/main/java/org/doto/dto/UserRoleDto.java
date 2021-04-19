package org.doto.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.doto.entity.Role;

@Getter
@Setter
@NoArgsConstructor
public class UserRoleDto {
    private Role role;
}
