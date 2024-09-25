package com.mx.crud.app.entities;

import com.mx.crud.app.validation.IsExistsDb;
import com.mx.crud.app.validation.IsRequired;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name= "products")
public class Product {


     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    /**
    @NotNull -> es para objetos una date o un time o integer
    @NotBlank -> es para validar que no tenga espacios en blanco y no tenga una cadena vacia
    @NotEmpty -> es para un string, no valida espacios vacios
     **/

    /**Las validaciones sirven para enviar al front los mensajes de error
      cuando van valores nulos al enviar con postman

     con @valid en el controlador en los metodos crete y update

     para validar campos tenemos que poner la anotacion  @Valid  antes de la anotacion @RequestBody Product product


     los mensajes de error los trae lo que configamos en la clase entity con las anotaciones @NotNull

     para recibir los mensajes de error se ponenen el controller
     la anotacion en el controltolador en los metodos crete y update
     la anotacion @ BindingResult result va a lado del objeto que queremos validar Product product
     BindingResult tiene todas las validaciones del mensaje de error que trae el request cuando se envia el json

     update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Long id){

     **/


     //el mensaje de validacion se pone en el properties
     @IsRequired(message = "{IsRequired.product.name}")
     //@NotEmpty(message = "{NotEmpty.product.name}")
     @Size(min=3, max = 20)
     private String name;

     @Min(value=500, message = "{Min.product.price}")
     @NotNull(message = "{NotNull.product.price}")
     private Integer price;

     //@NotEmpty
     //@NotBlank(message = "{NotBlank.product.description}")
     //validando con un mensaje personalizado (Isrequired.java  RequieredValidation.java)
      @IsRequired
     private String description;

     //no ponemos el tostring porque es un apirest
     //tampoco el constructor
     //solo los getters y setters

    @IsRequired //es requerido
    @IsExistsDb //existe enla base de datos
    private String sku;



    public Product() {
      }

    public Product(Long id, String name, Integer price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}
