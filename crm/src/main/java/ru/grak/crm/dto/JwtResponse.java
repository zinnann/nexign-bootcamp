package ru.grak.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.grak.crm.entity.Role;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class JwtResponse {

    private Long id;
    private String token;
    private String username;
    private List<Role> roles;
}
