package vn.edu.iuh.dao;

import vn.edu.iuh.models.Product_24133038;
import java.util.List;

public interface IProductDao_24133038 {
    List<Product_24133038> findAll();
    Product_24133038 findById(int id);
}
