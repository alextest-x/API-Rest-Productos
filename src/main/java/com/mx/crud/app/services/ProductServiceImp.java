package com.mx.crud.app.services;

import com.mx.crud.app.entities.Product;
import com.mx.crud.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

 /*
     @Service es un componente de spring
     Logica de negocio que viene de la Bd, repositorio un dao
     calculos de una tabla interactuar con repositories
     destron de una misma transaction

 */
@Service
public class ProductServiceImp implements IProductService{


    @Autowired
    private ProductRepository repository;


    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {

        return (List<Product>) repository.findAll();
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {

        return repository.findById(id);
    }



    @Transactional
    @Override
    public Product save(Product product) {

        return repository.save(product);
    }


     @Transactional
     @Override
     public Optional<Product> update(Long id, Product product) {
         //para validar ponemos un optional para buscar en la Bd y lo obtenemos con el optional

         Optional<Product> productOptional = repository.findById(id);

         /*** se comenta porque el Optional es un void no regresa nada  hay que poner un if
         productoOptional.ifPresent(productDb -> {

          //para validar ponemos un optional para buscar en la Bd
          //lo guardamos en productoOptional y se lo pasamos con la expresion lambda productDb
             //para actualizar los nombres de los campos
             // ponemos el nombre del request que viene desde el json
             productDb.setName(product.getName());
             productDb.setDescription(product.getDescription());
             productDb.setPrice(productDb.getPrice());

             // error hay que cambiar el return por un optional
             // return repository.save(productDb);  //el productDb tiene el id porque busca en la BD
             return Optional.of(repository.save(productDb));
         });
         ***/

         if(productOptional.isPresent()){

             //obtenemos el producto que viene de la BD
             Product productDb = productOptional.orElseThrow();

             //para actualizar los nombres de los campos
             //ponemos el nombre del request que viene desde el json
             productDb.setSku(product.getSku());  //se actualiza este campo nuevo
             productDb.setName(product.getName());
             productDb.setPrice(product.getPrice());
             productDb.setDescription(product.getDescription());


             // error hay que cambiar el return por un optional
             // return repository.save(productDb);  //el productDb tiene el id porque busca en la BD
             //se actualiza productDb con los datos cambiados y se lo ponemos en un optional
             //para poder validarlo y regresa en un json o en un notfound
             return Optional.of(repository.save(productDb));
         }

            //sino esta presente regresa un optional
         return productOptional;
     }


     @Transactional
    @Override
    //public void delete(Product product) { //lo pasamos a un optional
    //optimizando public Optional<Product> delete(Product product) {
        public Optional<Product> delete(Long id) {

        // es  un optional hacer el cast
        // <Product> productoDb = repository.findById(product.getId());
        //vamos a buscar el id al BD

       //optimizando Optional<Product> productoOptional = repository.findById(product.getId());
        Optional<Product> productoOptional = repository.findById(id);

        //si esta presenta lo elimina el productDb que viene de la BD
        productoOptional.ifPresent(productDb -> {
            repository.delete(productDb);
        });

        return productoOptional;

    }

    //valida si existe el campo en la  base de datos
     @Override
     @Transactional(readOnly = true)
     public boolean existsBySku(String sku) {

         if(repository.existsBySku(sku) == true){
             System.out.println("sku If() " + sku);
             System.out.println("sku => " + repository.existsBySku(sku));
             return repository.existsBySku(sku);
         }


         System.out.println("sku else() " + sku);
         System.out.println("sku => " +  repository.existsBySku(sku));
        return repository.existsBySku(sku);

     }


 }
