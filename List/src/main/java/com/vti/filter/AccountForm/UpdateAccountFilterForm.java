package com.vti.filter.AccountForm;

import com.vti.Validate.EmailNotExists;
import com.vti.Validate.UserNameNotExists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
public class UpdateAccountFilterForm {

    private int id;
    @NotBlank(message = "UserName khong duoc de trong")
    @Length(max = 50, message = "UserName toi da la 50 ki tu")
    @UserNameNotExists
    private String userName;

    @NotBlank(message = "FirstName khong duoc de trong")
    @Length(max = 50, message = "FirstName toi da la 50 ki tu")
    private String firstName;

    @NotBlank(message = "LastName khong duoc de trong")
    @Length(max = 50, message = "LastName toi da la 50 ki tu")
    private String lastName;

    @NotBlank(message = "Email khong duoc de trong")
    @Length(max = 50, message = "Email toi da la 50 ki tu")
    @Email(message = "Email không đúng định dạng. VD: abc@gmail.com")
    @EmailNotExists
    private String email;

    @Positive
    private int departmentId;

    @NotBlank(message = "vui lòng khong để trống password")
    private String password;

    @Pattern(regexp = "ADMIN|EMPLOYEE|MANAGER")
    private String role;
}
