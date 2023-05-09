import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000"
});

export default {

  getOrderListByStatus(orderStatus) {
    return http.get(`/orders?status=${orderStatus}`);
  },

  getOrderList() {
    return http.get(`/orders`);
  },

  updateOrder(orderId, updatedOrder) {
    return http.put(`/orders/${orderId}`, updatedOrder);
  },
  updateOrderStatus(orderId, updatedOrder) {
    return http.put(`/orders/${orderId}/status`, updatedOrder);
  },

  postAnOrder(orderObject){
    return http.post('/orders', orderObject);
  }
}