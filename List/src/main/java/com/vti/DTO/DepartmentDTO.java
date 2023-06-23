package com.vti.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@NoArgsConstructor
public class DepartmentDTO extends RepresentationModel<DepartmentDTO> {

    private int id;

    private String name;

    private int totalMember;

    private List<AccountDTO> accounts;

    @Data
    @NoArgsConstructor
    public static class AccountDTO extends RepresentationModel<DepartmentDTO>{

        private int id;
        private String email;
        private String userName;
        private String firstName;
        private String lastName;
        private int departmentId;



    }

}
