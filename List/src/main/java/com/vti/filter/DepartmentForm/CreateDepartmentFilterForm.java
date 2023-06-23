package com.vti.filter.DepartmentForm;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.Validate.EmailNotExists;
import com.vti.Validate.IdNotExists;
import com.vti.Validate.UserNameNotExists;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.PrePersist;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class CreateDepartmentFilterForm {
    @NotBlank(message = "Name khong duoc de trong")
    @Length(max = 50, message = "Name toi da la 50 ki tu")
    private String name;

    @Positive
    private int totalMember;

    @Pattern(regexp = "Dev|Test|ScrumMaster|PM")
    private String type;

    private List<AccountForm> accounts;

    @Data
    @NoArgsConstructor
    public static class AccountForm {

        private Integer id; // tao moi thang account

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

        @DateTimeFormat(pattern = "dd-MM-yyyy")
        @PastOrPresent(message = "thời gian phải tồn tại từ trước")
        private Date createDate;

        @NotBlank(message = "Vui lòng không để trống tên")
        @Length(min = 1, max = 7, message = "độ dài tối đa là 7 ký tự")
        private String firstName;

        @NotBlank(message = "Vui lòng không để trống tên")
        private String lastName;

        @Pattern(regexp = "ADMIN|EMPLOYEE|MANAGER")
        private String role;

        private String password;
    }

//
//    public static void main(String[] args) {
//        List<Integer> item = new ArrayList<>();
//        item.add(1);
//        item.add(2);
//        item.add(3);
//
//        // foreach
//        for (Integer items: item) {
//            System.out.println(items);
//        }
//        System.out.println("----------------");
//        // for co lamda
//        item .forEach(items -> {
//            System.out.println(items);
//        });
//
//
//    }
}
