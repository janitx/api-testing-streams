package training;

import clients.ProductClient;
import models.Product;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamsTest {

    // Check does products in category "Active wear - women" contains product with a name "Stretchy Dance Pants"
    @Test
    public void tc_1() {
        List<Product> products = ProductClient.getProducts();
        List<Product> specifiedProduct = products.stream()
                .filter(x -> x.getCategoryName().equals("Active Wear - Women"))
                .filter(x -> x.getName().equals("Stretchy Dance Pants"))
                .toList();


        assertThat(specifiedProduct.get(0).getName()).as("Wrong product")
                .isEqualTo("Stretchy Dance Pants");

    }

    // Check does product with id = 3 has price = 68.0
    @Test
    public void tc_2() {
        List<Product> products = ProductClient.getProducts();

        var product = products.stream().filter(x -> x.getId() == 3)
                .filter(x -> x.getPrice() == 68)
                .toList();

        assertThat(product.get(0).getPrice()).as("Wrong price").isEqualTo(68);
    }

    // Check does product with id = 1 is has price grater than 90
    @Test
    public void tc_3() {
        List<Product> products = ProductClient.getProducts();

        var product = products.stream().filter(x -> x.getId() == 1)
                .filter(x -> x.getPrice() > 90)
                .toList();

        assertThat(product.get(0).getPrice()).as("Product with id 1 hasn't price grater than 90").isEqualTo(99);


    }

    // Calculate average price for products with help of stream.
    @Test
    public void tc_4() {
        List<Product> products = ProductClient.getProducts();

        double product = products.stream().collect(Collectors.averagingDouble(Product::getPrice));

        assertThat(new DecimalFormat("#.##").format(product)).as("Wrong average price").isEqualTo("15.91");

    }


    // Create a map with ids as key and name as value
    @Test
    public void tc_5() {
        List<Product> products = ProductClient.getProducts();

        Map<Integer, String> productsMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Product::getName));

        productsMap.forEach((k, v) -> System.out.println("Key: " + k + " Value: " + v));

    }

}
