package com.bondevalue.marketplaceservice.resource;

import com.bondevalue.marketplaceservice.common.ApiResponse;
import com.bondevalue.marketplaceservice.domain.Order;
import com.bondevalue.marketplaceservice.dto.OrderDto;
import com.bondevalue.marketplaceservice.exeptions.OrderNotFoundException;
import com.bondevalue.marketplaceservice.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import static com.bondevalue.marketplaceservice.common.Constants.SUCCESS;
import static com.bondevalue.marketplaceservice.common.Constants.FAILURE;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

  private static final Logger LOGGER = Logger.getLogger(OrderResource.class.getName());

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private OrderService orderService;

  @GetMapping()
  public ApiResponse orders() {
    LOGGER.info("REST: Request to GET all orders");
    ApiResponse apiResponse;
    try {
      List<Order> orderList = orderService.findAll(Sort.by(new Sort.Order(Sort.Direction.DESC, "price")));
      List<OrderDto> orderDtos = orderList.stream()
                                          .map(this::convertToDto)
                                          .collect(Collectors.toList());
      apiResponse = new ApiResponse(orderDtos, null, SUCCESS, 200, null );
    } catch (Exception ex) {
      apiResponse = new ApiResponse(null, null, SUCCESS, 500, ex.getMessage());
    }
    return apiResponse;
  }

  @GetMapping(value = "/{orderId}")
  public ApiResponse getOrderById(@PathVariable String orderId) {
    LOGGER.info("REST: Request to GET Order By Id: " + orderId);
    ApiResponse apiResponse;
    try {
      Order order = orderService.findById(orderId);
      apiResponse = new ApiResponse(convertToDto(order), null, SUCCESS, 200, null);
    } catch (OrderNotFoundException ex) {
      LOGGER.info("No Order Found with OrderId: "+orderId);
      apiResponse = new ApiResponse(null, null, FAILURE, 500, ex.getMessage());
    } catch (Exception ex) {
      ex.printStackTrace();
      apiResponse = new ApiResponse(null, null, FAILURE, 500, ex.getMessage());
    }
    return apiResponse;
  }

  @PostMapping
  public ApiResponse saveOrder(@RequestBody OrderDto orderDto) {
    LOGGER.info("REST: Request to SAVE all orders");
    ApiResponse apiResponse;
    try {
      Order order = orderService.save(convertToEntity(orderDto));
      apiResponse = new ApiResponse(convertToDto(order), null, SUCCESS, 200, null);

    } catch (Exception ex) {
      ex.printStackTrace();
      apiResponse = new ApiResponse(null, ex.getMessage(), FAILURE, 500, null);
    }
    return apiResponse;
  }

  @PutMapping(value = "/{orderId}")
  public ApiResponse updateOrder(@RequestBody OrderDto orderDto, @PathVariable String orderId) {
    LOGGER.info("REST: Request to load all orders");

    return new ApiResponse();
  }

  @DeleteMapping("/{orderId}")
  public ApiResponse deleteById(@PathVariable String orderId) {
    LOGGER.info("REST: request to delete Order by Id:" + orderId);
    ApiResponse apiResponse;
    try {
      orderService.deleteById(orderId);
      apiResponse = new ApiResponse("successfully deleted Order with id:" + orderId, null, SUCCESS, 200, null);
    }
    catch (Exception ex) {
      apiResponse = new ApiResponse(null, ex.getMessage(), FAILURE, 500, ex.getMessage());
    }
    return apiResponse;
  }

  private OrderDto convertToDto(Order post) {
    return modelMapper.map(post, OrderDto.class);
  }

  private Order convertToEntity(OrderDto postDto) {
    return modelMapper.map(postDto, Order.class);
  }
}
