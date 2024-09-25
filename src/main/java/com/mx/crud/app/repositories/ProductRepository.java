package com.mx.crud.app.repositories;

import com.mx.crud.app.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {


    /**  para validar un campo en la base datos
     * se puede hacer de una forma
     *
     * 1. se agrega un metodo para validar si existe el producto sku
     *    metodo basado en el nombre existsBy + campo atributo
     *    si lo encuentra regresa un true lo valida al vuelo
     *
     *     lo pasamo a la interface service e implemetamos el metodo en la clase service
     *     boolean  existsBySku(String sku);
     *
     *   2. haciendo una consulta en la base de datos con el where con la anotacion @query
     *      regresa un optional de producto en uno solo
     *
     */

    boolean  existsBySku(String sku);

    //lo pasamo a la interface service
    // boolean  existsBySku(String sku);




}
