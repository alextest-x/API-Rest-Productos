package com.mx.crud.app.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;


// implementamos de ConstraintValidator
public class RequiredValidation implements ConstraintValidator<IsRequired, String> {

  /*
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value != null && !value.isEmpty() && !value.isBlank()){
            return true;
        }
        return false;
    }
   */


    //en una sola linea ponemos la validacion en el return
    //regresa un boolean
    //(es distinto de null regresa un null y el value no sea vacio y el value no sea blanco)
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //return (value != null && !value.isEmpty() && !value.isBlank()); //true os si falla es false

        //aqui validamos con el StringUtils si tiene texto es true sino es false
        //donde hasText valida que sea distinto de nulo y que no tenga espacios en blanco
        return StringUtils.hasText(value);


    }

}
