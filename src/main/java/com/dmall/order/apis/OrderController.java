package com.dmall.order.apis;

import com.dmall.order.model.Order;
import com.dmall.order.model.Product;
import com.dmall.order.model.Shipping;
import com.dmall.order.service.ProductService;
import com.dmall.order.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class OrderController {

  @Autowired
  private ProductService productService;


  @Autowired
  private ShippingService shippingService;

  private List<Order> orders = Arrays.asList(
      new Order("o001", "p001"),
      new Order("o002", "p002"));

  public OrderController() throws ParseException {

  }

  @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
  public List<Order> getOrders() {
    return orders;
  }

  @RequestMapping(value = "{orderId}", method = RequestMethod.GET, headers = "Accept=application/json")
  public Order getTaskByTaskId(@PathVariable("orderId") String orderId) {
    Order orderToReturn = null;
    for (Order order : orders) {
      if (order.getOrderId().equalsIgnoreCase(orderId)) {
        orderToReturn = order;
        break;
      }
    }

    if (orderToReturn != null) {
      Product product = this.productService.getProductDetial(orderToReturn.getProductId());
      orderToReturn.setProduct(product);
      Shipping shipping = shippingService.getShippingDetail(orderToReturn.getProductId());
      orderToReturn.setShipping(shipping);
    }

    return orderToReturn;
  }


  @RequestMapping(value = "shippings/{goodsId}", method = RequestMethod.GET, headers = "Accept=application/json")
  public Shipping getPatientById(@PathVariable("goodsId") String goodsId) {

    return shippingService.getShippingDetail(goodsId);
  }
}
