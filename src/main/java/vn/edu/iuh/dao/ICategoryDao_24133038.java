package vn.edu.iuh.dao;

import vn.edu.iuh.models.Category_24133038;
import java.util.List;

public interface ICategoryDao_24133038 {
    List<Category_24133038> findAll();
    Category_24133038 findById(int categoryId);
}
