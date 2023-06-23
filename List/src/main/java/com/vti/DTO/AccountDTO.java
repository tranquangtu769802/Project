package com.vti.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Email;
import java.util.Date;

@Data
@NoArgsConstructor
public class AccountDTO extends RepresentationModel<AccountDTO> {

    private int id;
    @NonNull
    private String email;
    @NonNull
    private String userName;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String role;
    @NonNull
    private String departmentName;
}


// array = [0,1,2,3]
// array .steam().map() // java thuan
//array.map() // java scripte
// JAVSRIPTE
// typescript
//id: 1
// name: "abc"
// backend ngon ngu java
// + java thuan // di sau // backend
// + java script/ typescripte --> nodejs: viet dc ca backend va frondend + css + html // co ban