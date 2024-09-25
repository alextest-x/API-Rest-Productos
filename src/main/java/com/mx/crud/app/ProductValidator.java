package com.mx.crud.app;


import com.mx.crud.app.entities.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


//anotamos con component para despues inyectarlo
//en el controller ProductController
@Component
public class ProductValidator implements Validator {

    //da soporte a la clase que se va a validar
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }


    //este metodo validate lo usamos en el controlador
    //el target es el objeto product que pasa por referencia
    //el errors es el BindingResult
    @Override
    public void validate(Object target, Errors errors) {
        //hacemos el cast econ Product
         Product product = (Product) target;
         ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name",null, "es requerido!");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors,"description","NotBlank.product.description");


        //tambien se puede hacer de esta forma validando la description
        if (product.getDescription() == null || product.getDescription().isBlank()){
            errors.rejectValue("description", null, "Es requerido please!");
        }


        //validando con if el price
        if(product.getPrice() == null){
            errors.rejectValue("price", null, "No puede ser nulo, ok");

        }else if(product.getPrice() < 500 ){
            errors.rejectValue("price", null, "Debe ser un valor numerico mayor o igual a 500!");
        }

    }


   /*
    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }

   */
}
