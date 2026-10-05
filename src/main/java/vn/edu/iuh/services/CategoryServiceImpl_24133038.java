package vn.edu.iuh.services;

import vn.edu.iuh.dao.CategoryDaoImpl_24133038;
import vn.edu.iuh.dao.ICategoryDao_24133038;
import vn.edu.iuh.models.Category_24133038;
import java.util.List;

public class CategoryServiceImpl_24133038 implements ICategoryService_24133038 {
    private ICategoryDao_24133038 categoryDao = new CategoryDaoImpl_24133038();

    @Override
    public List<Category_24133038> findAll() { return categoryDao.findAll(); }
    @Override
    public Category_24133038 findById(int categoryId) { return categoryDao.findById(categoryId); }
}
