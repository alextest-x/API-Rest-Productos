package com.mx.crud.app.validation;


import com.mx.crud.app.services.ProductServiceImp;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class IsExistsDbValidation implements ConstraintValidator<IsExistsDb, String> {


    //inyectando el service
    @Autowired
    private ProductServiceImp serviceImp;


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //return value != null && !serviceImp.existsBySku(value);
        //value es distinto de null and sino existe en la base de datos es true

        System.out.println("value = " + value);
        return !serviceImp.existsBySku(value); //solo valida si existe en la base de datos

        //hay que enlazar en el isExistsDb.java
        //@Constraint IsExistsDbValidation

    }

}
