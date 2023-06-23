package com.vti.specification;

import com.vti.entity.Account;
import com.vti.filter.AccountForm.AccountFilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AccountSpecification {

    //Datetime
//    private static final String searchMinCreateDate = "searchMinCreateDate";
//    private static final String searchMaxCreateDate = "searchMaxCreateDate";
//
//    // hung ket qua specification
//    public static Specification<Account> buildWhere(AccountFilterForm form) {
//        if (form == null) {
//            return null;
//
//        }
//
//        CustomSpecification WhereByMinCreateDate = new CustomSpecification(searchMinCreateDate, form.getSearchMinCreateDate());
//        CustomSpecification WhereByMaxCreateDate = new CustomSpecification(searchMaxCreateDate, form.getSearchMaxCreateDate());
//
//        return Specification.where(WhereByMinCreateDate.and(WhereByMaxCreateDate));
//    }
//
//
//    @AllArgsConstructor
//    static class CustomSpecification implements Specification<Account> {
//
//        private String key;
//        private Object value;
//
//        @Override
//        public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//            if (value == null){
//                return null;
//            }
//            switch (key){
//                case searchMinCreateDate:
//                    return criteriaBuilder.greaterThanOrEqualTo(root.get("createDate").as(java.sql.Date.class), (Date) value);
//
//                case searchMaxCreateDate:
//                    return criteriaBuilder.lessThanOrEqualTo(root.get("createDate").as(java.sql.Date.class), (Date)value);
//
//                default:
//                    return null;
//            }
//
//        }
//    }


    //Id
//    private static final String Max_Id = "Max_Id";
//    private static final String Min_Id = "Min_Id";
//
//    // hung ket qua specification
//    public static Specification<Account> buildWhere(AccountFilterForm form) {
//        if (form == null) {
//            return null;
//
//        }
//
//        CustomSpecification WhereByMinId = new CustomSpecification(Min_Id, form.getMin_Id());
//        CustomSpecification WhereByMaxId = new CustomSpecification(Max_Id, form.getMax_Id());
//
//        return Specification.where(WhereByMinId.and(WhereByMaxId));
//    }
//
//
//    @AllArgsConstructor
//    static class CustomSpecification implements Specification<Account> {
//
//        private String key;
//        private Object value;
//
//        @Override
//        public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//            if (value == null){
//                return null;
//            }
//            switch (key){
//                case Min_Id:
//                    return criteriaBuilder.greaterThanOrEqualTo(root.get("id"), value.toString());
//
//                case Max_Id:
//                    return criteriaBuilder.lessThanOrEqualTo(root.get("id"),value.toString());
//
//                default:
//                    return null;
//            }
//
//        }
//    }

    //YEAR
//    private static final String MIN_Year = "minYear";
//    private static final String MAX_Year = "maxYear";
//
//    public static Specification<Account> buildWhere(AccountFilterForm form) {
//        if (form == null) {
//            return null;
//        }
//        CustomSpecification whereMinYear = new CustomSpecification(MIN_Year, form.getMinYear());
//        CustomSpecification whereMaxYear = new CustomSpecification(MAX_Year, form.getMaxYear());
//
//        return Specification.where(whereMinYear.and(whereMaxYear));
//    }
//
//    @AllArgsConstructor
//    static class CustomSpecification implements Specification<Account> {
//
//        private String key;
//        private Object value;
//
//        @Override
//        public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//            if (value == null) {
//                return null;
//            }
//
//
//            switch (key) {
//                case MIN_Year:
//                    return criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.function("YEAR", Integer.class, root.get("createDate")), (Integer)value);
//
//                case MAX_Year:
//                    return criteriaBuilder.lessThanOrEqualTo(criteriaBuilder.function("YEAR", Integer.class, root.get("createDate")), (Integer)value);
//
//                default:
//                    return null;
//            }
//        }
//    }
//


    //DAY
//    private static final String MIN_Day = "minDay";
//    private static final String MAX_Day = "maxDay";
//
//    public static Specification<Account> buildWhere(AccountFilterForm form) {
//        if (form == null) {
//            return null;
//        }
//        CustomSpecification whereMinDay = new CustomSpecification(MIN_Day, form.getMinDay());
//        CustomSpecification whereMaxDay = new CustomSpecification(MAX_Day, form.getMaxDay());
//
//        return Specification.where(whereMinDay.and(whereMaxDay));
//    }
//
//    @AllArgsConstructor
//    static class CustomSpecification implements Specification<Account> {
//
//        private String key;
//        private Object value;
//
//        @Override
//        public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//            if (value == null) {
//                return null;
//            }
//
//
//            switch (key) {
//                case MIN_Day:
//                    return criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.function("DAY", Integer.class, root.get("createDate")), (Integer)value);
//
//                case MAX_Day:
//                    return criteriaBuilder.lessThanOrEqualTo(criteriaBuilder.function("DAY", Integer.class, root.get("createDate")), (Integer)value);
//
//                default:
//                    return null;
//            }
//        }
//    }


    //MONTH
//    private static final String MIN_Month = "minMonth";
//    private static final String MAX_Month = "maxMonth";
//
//    public static Specification<Account> buildWhere(AccountFilterForm form) {
//        if (form == null) {
//            return null;
//        }
//        CustomSpecification whereMinDay = new CustomSpecification(MIN_Month, form.getMinMonth());
//        CustomSpecification whereMaxDay = new CustomSpecification(MAX_Month, form.getMaxMonth());
//
//        return Specification.where(whereMinDay.and(whereMaxDay));
//    }
//
//    @AllArgsConstructor
//    static class CustomSpecification implements Specification<Account> {
//
//        private String key;
//        private Object value;
//
//        @Override
//        public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//            if (value == null) {
//                return null;
//            }
//
//
//            switch (key) {
//                case MIN_Month:
//                    return criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.function("MONTH", Integer.class, root.get("createDate")), (Integer)value);
//
//                case MAX_Month:
//                    return criteriaBuilder.lessThanOrEqualTo(criteriaBuilder.function("MONTH", Integer.class, root.get("createDate")), (Integer)value);
//
//                default:
//                    return null;
//            }
//        }
//    }



//    searchUserName : search theo từ ký tự đầu tiên
    private static final String searchUserName = "searchUserName";


    // hung ket qua specification
    public static Specification<Account> buildWhere(AccountFilterForm form) {
        if (form == null) {
            return null;

        }

        CustomSpecification WhereByUserName = new CustomSpecification(searchUserName, form.getSearch());

        return Specification.where(WhereByUserName);
    }

// username: t + departname: o

    // xu ly dk, dung key va value
    @AllArgsConstructor
    static class CustomSpecification implements Specification<Account> {

        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null) {
                return null;
            }
            switch (key) {
                case searchUserName:
                    // Where userName LiKE '%search%'
                    return criteriaBuilder.like(root.get("userName"), value.toString().trim() + "%");

                default:
                    return null;
            }

        }
    }

    //searchEmail
//    private static final String searchEmail = "searchEmail";
//
//
//    // hung ket qua specification
//    public static Specification<Account> buildWhere(AccountFilterForm form) {
//        if (form == null) {
//            return null;
//
//        }
//
//        CustomSpecification WhereByEmail = new CustomSpecification(searchEmail, form.getSearchEmail());
//
//        return Specification.where(WhereByEmail);
//    }

// username: t + departname: o

    // xu ly dk, dung key va value
//    @AllArgsConstructor
//    static class CustomSpecification implements Specification<Account> {
//
//        private String key;
//        private Object value;
//
//        @Override
//        public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//            if (value == null) {
//                return null;
//            }
//            switch (key) {
//                case searchEmail:
//                    // Where userName LiKE '%search%'
//                    return criteriaBuilder.like(root.get("email"), value.toString().trim() + "%");
//
//                default:
//                    return null;
//            }
//
//        }
//    }
}
