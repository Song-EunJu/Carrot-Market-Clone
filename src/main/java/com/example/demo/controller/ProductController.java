package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductImage;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.SessionUser;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Controller
//@RequestMapping(value="/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService ProductService) {
        this.productService = ProductService;
    }

    @GetMapping("/products") // localhost:8080/boards 뒤에 / 안쓰려면 빈 문자열 넣어주기
    public String products(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        System.out.println("sessionUser.getId() = " + sessionUser.getId());
        model.addAttribute("uid", sessionUser.getId());
        return "products";
    }

    @GetMapping("/product")
    public String writing(){
        return "writing"; // 글 작성페이지로
    }

    @PostMapping("/product")
    public String product(@RequestParam("files") MultipartFile[] file, HttpServletRequest request, Model model, ProductDto productDto){
        String rootPath = System.getProperty("user.dir");;
        String imgUploadPath = rootPath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "uploadImages";
        System.out.println("imgUploadPath = " + imgUploadPath);

        File folder = new File(imgUploadPath);

        if(!folder.exists()){ // 폴더가 없는 경우 디렉토리 생성
            try{
                folder.mkdir(); //폴더 생성
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }
        String originalName="";
        for(MultipartFile f : file) {
            originalName = f.getOriginalFilename();
            try {
                f.transferTo(new File(imgUploadPath + File.separator + originalName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");

        // Product 에 저장되는 요소
        Long user_id = sessionUser.getId();
        String title = productDto.getTitle();
        String category = productDto.getCategory();
        String content = productDto.getContent();
        int price = productDto.getPrice();
        LocalDateTime posted = LocalDateTime.now();
        int product_status = 1; // 판매중
        int post_status = 1; // 게시글 삭제여부

        // ProductImage 에 저장되는 요소
        String savedPath = imgUploadPath + File.separator + originalName;

        Product product = new Product(user_id, title, category, content, price, posted, product_status, post_status);
        productService.save(product);
        Long productId = productService.findById(product.getId()).getId();
        ProductImage productImage = new ProductImage(productId, originalName, savedPath);
        productService.saveImage(productImage);
        productService.findAll();
//        model.addAttribute("products",product);
        return "products";
    }
}
