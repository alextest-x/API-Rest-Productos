package com.mx.crud.app.services;

import com.mx.crud.app.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {


    List<Product> findAll();

    Optional<Product> findById(Long id); //buscar por el id usamos un optional

    Product save(Product product);

    //para actualizar le poenemos el producto y el id para no usar el save
    //Product update(Long id, Product product);
    //lo pasamos e un Optional, para consultar el id en la Bd
    Optional<Product> update(Long id, Product product);


    //se comenta porque cambiamos en el controller del obejeto producto por el id
    //Optional<Product> delete(Product product);
    //optimizando
    Optional<Product> delete(Long id);

    boolean  existsBySku(String sku);



}
