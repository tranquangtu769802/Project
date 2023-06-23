package com.vti.filter.AccountForm;



import com.vti.Validate.EmailNotExists;
import com.vti.Validate.UserNameNotExists;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.PrePersist;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
public class CreateAccountFilterForm {

    @NotBlank(message = "Vui lòng không để trống")
    @Length(min = 6, max = 50, message = "Vui lòng nhập email trong độ dài từ 6 đến 50 ký tự")
    @Email(message = "Vui lòng nhập đúng cú pháp")
    @EmailNotExists
    private String email;

    @NotBlank(message = "Vui lòng không để trống")
    @Length(max = 20, message = "Vui lòng nhập username trong độ dài tối đa 20 k tự")
    @UserNameNotExists
    private String userName;

    @NonNull
    @Positive
    private int departmentId;


    @NotBlank(message = "Vui lòng không để trống tên")
    @Length(min = 1, max = 7, message = "độ dài tối đa là 7 ký tự")
    private String firstName;

    @NotBlank(message = "Vui lòng không để trống tên")
    private String lastName;

    @Pattern(regexp = "ADMIN|EMPLOYEE|MANAGER")
    private String role;
    private String password;

}
