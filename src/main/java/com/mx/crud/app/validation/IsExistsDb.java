package com.mx.crud.app.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//se pone @interface porque es una anotacion
@Constraint(validatedBy = IsExistsDbValidation.class)  //viene de la calse IsExistsDbValidation
@Target(ElementType.FIELD)  //valida el atributo
@Retention(RetentionPolicy.RUNTIME)
public @interface IsExistsDb {


    //copiamos IsExistsDb y la pegamos en la clase Producto en el atributo del campo a validar




    //copiamos de la clase IsRequired y pegamos aqui
    //estos metodos se copiaron del clase NotBlank.class  en la clase entity de Producto
    //control + click y copiamos estos tres metodos para validar el campo en la clase entity

    //hay que hacer una clase que de soporte a esta clase de validacion

    String message() default "Ya existe en la base de datos!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
