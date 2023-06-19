package com.duan.mapper;

import com.duan.dto.ProductModel;
import com.duan.entities.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class productmapper {
    @Autowired
    private ModelMapper mapper;

    public Product convertToEntity(ProductModel productModel) {
        Product entity = new Product();

        mapper.map(productModel, entity);

        return entity;
    }

    public ProductModel convertToDTO(Product entity) {
        ProductModel productModel = new ProductModel();
        mapper.map(entity, productModel);
        return productModel;
    }
}
