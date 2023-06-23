package com.vti.specification;

import com.vti.entity.Department;
import com.vti.filter.DepartmentForm.DepartmentFilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class DepartmentSpecification {
//    private static final String searchUserName = "searchUserName";
//    public static Specification<Department> buildWhere(DepartmentFilterForm form) {
//        if (form == null) {
//            return null;
//        }
//        CustomSpecification whereUserName = new CustomSpecification(searchUserName, form.getSearchUserName());
//
//        return Specification.where(whereUserName);
//    }
//
//    @AllArgsConstructor
//    static class CustomSpecification implements Specification<Department> {
//
//        private String key;
//        private Object value;
//
//        @Override
//        public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//            if (value == null) {
//                return null;
//            }
//            // Where UserName LiKE '%search%'
//            Join join = root.join("accounts", JoinType.LEFT);
//            return criteriaBuilder.like(join.get("username"), value.toString().trim() + "%");
//        }
//    }
    //searchEmail
    private static final String searchEmail = "searchEmail";
    public static Specification<Department> buildWhere(DepartmentFilterForm form) {
        if (form == null) {
            return null;
        }
        CustomSpecification whereEmail = new CustomSpecification(searchEmail, form.getSearchEmail());

        return Specification.where(whereEmail);
    }

    @AllArgsConstructor
    static class CustomSpecification implements Specification<Department> {

        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null) {
                return null;
            }
            // Where UserName LiKE '%search%'
            Join join = root.join("accounts", JoinType.LEFT);
            return criteriaBuilder.like(join.get("email"), value.toString().trim() + "%");
        }
    }

}
