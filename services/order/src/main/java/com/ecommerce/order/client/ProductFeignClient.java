package com.ecommerce.order.client;

import com.ecommerce.order.dto.PurchaseResponse;
import com.ecommerce.order.request.ProductPurchaseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "products",url = "http://localhost:8090/products")
public interface ProductFeignClient {
    @PostMapping("/purchase-products")
    List<PurchaseResponse> purchaseProducts(@RequestBody List<ProductPurchaseRequest> productRequests);
}
