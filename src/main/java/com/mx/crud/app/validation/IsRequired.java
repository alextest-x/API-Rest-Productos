package com.mx.crud.app.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//se ejecuta en tiempo de ejecucion con Runtime
//target le ponemos un element porque lo ponemos en un arreglo
//con la anotacion field y method anotamos sobre un campo y un metodo
//@contraint
@Constraint(validatedBy = RequiredValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface IsRequired {


    //estos metodos se copiaron del clase NotBlank.class  en la clase entity de Producto
    //control + click y copiamos estos tres metodos
    String message() default "es requerido usando anotaciones";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
