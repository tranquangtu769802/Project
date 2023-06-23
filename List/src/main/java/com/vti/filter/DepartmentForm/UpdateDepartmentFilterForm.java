package com.vti.filter.DepartmentForm;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.entity.Type;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@NoArgsConstructor
public class UpdateDepartmentFilterForm {
    private int id;

    @NotBlank(message = "Name khong duoc de trong")
    @Length(max = 50, message = "Name toi da la 50 ki tu")
    private String name;

    @Positive
    private int totalMember;

    @Pattern(regexp = "Dev || Test || ScrumMaster || PM")
    private String type;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createDate;


}
