package vn.edu.iuh.controllers;

import vn.edu.iuh.dao.IProductDao_24133038;
import vn.edu.iuh.dao.ProductDaoImpl_24133038;
import vn.edu.iuh.models.Product_24133038;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/products"})
public class ProductController_24133038 extends HttpServlet {
    private IProductDao_24133038 productDao = new ProductDaoImpl_24133038();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product_24133038> products = productDao.findAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/views/web/products.jsp").forward(req, resp);
    }
}
