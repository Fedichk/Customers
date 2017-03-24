package com.fedich.repository;

        import com.fedich.model.Product;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.Collection;

public interface ProductJPARepository extends JpaRepository<Product, Long> {

    Collection<Product> findByName(String name);

    Collection<Product> findByPrice(Double price);
}
