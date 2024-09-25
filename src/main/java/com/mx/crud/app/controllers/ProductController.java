package com.mx.crud.app.controllers;


//import com.mx.crud.app.ProductValidator;
import com.mx.crud.app.entities.Product;
import com.mx.crud.app.services.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RequestMapping("/api/products")
@RestController
public class ProductController {


    //inyectamos el service de la interface
    @Autowired
    private IProductService service;

    //@Autowired
    //private ProductValidator validator;


    @GetMapping
    public List<Product> list(){
        return service.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id){
        Optional<Product> productOptional =  service.findById(id);
        //retornamos con un if

       if(productOptional.isPresent()){
           return ResponseEntity.ok(productOptional.orElseThrow());  //regresa un json
       }
       //sino esta presente regresa un notfound
       return ResponseEntity.notFound().build();
    }



    @PostMapping
      public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result){
        //validamos con la clase validator
        //validator.validate(product, result);
        if(result.hasErrors()){
            return validation(result);
        }

        //en una sola linea
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }


    //pasamos el id con @pathvariable y
    //pasamos el objeto product con la anotacion @RequestBody
     @PutMapping("/{id}")
      public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Long id){

        //validamos con la clase validator
        //validator.validate(product, result);
         if(result.hasErrors()){
             return validation(result);

         }
        
        
         //para una actualizacion podemos usar el CREATE O OK
         //usamos el objeto completo product
         // hacemos una consulta en la Bd
         // y solo actualizamos los campos que queremos actualizar en el json
         product.setId(id);
         //como es un opcional le poenemos el get para obtener el objeto producto y se lo pasamos al body
         //return ResponseEntity.status(HttpStatus.CREATED).body(service.update(id, product).get());

         //para validar el producto le ponemos un optional y usamos el .isPresent para validar
         //si esta presente lo pasamos al json con el productOptional.orElseThrow()
         Optional<Product> productOptional = service.update(id, product); //aqui hace el update
         if(productOptional.isPresent()){
             return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
         }

         return ResponseEntity.notFound().build();
     }


    @DeleteMapping("/{id}")
     public ResponseEntity<?> delete(@PathVariable Long id){

         //pasamos el objeto id y el objeto producto completo
         //Optional<Product> productOptional = service.delete(id);
         //quitamos el id y le pasamos el objeto product
         //creamos una instancia y le pasamos el objeto product

         //optimizando
         //Product product = new Product();
         //product.setId(id); //le pasamos el id

         Optional<Product> productOptional = service.delete(id);
         //cambiamos el product por id y modificamos el service con Long id

          if(productOptional.isPresent()){
              return ResponseEntity.ok(productOptional.orElseThrow());
          }

          return ResponseEntity.notFound().build();
     }


       //valida los errores
       //para que no salga error en el return le ponemos un generico porque el ResponseEntity es de tipo Producto
       //pero es de tipo Map<> entonces le ponemos un generico
       //private ResponseEntity<Product> validation(BindingResult result) {
        private ResponseEntity<?> validation(BindingResult result) {

        //por cada mensaje de error de cada campo sale el mensaje de error genera un json
        Map<String, String> errors =  new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        //pasando el json en el cuerpo de la respuesta y le ponemos el ResponseEntity

        //return ResponseEntity.badRequest().body(errors);
            //de otra forma es el mismo resultado
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


}
