import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
  });
  
  export default {
 
get(){
    return http.get('/menu/toppings');
},
getToppingByType(type){
    return http.get(`/menu/toppings?type=${type}`);
},
post(toppingObject){
    return http.post('/menu/toppings', toppingObject);
},
// put(toppingObject){
//     return http.put(`/menu/toppings/${toppingObject.itemId}/status`, toppingObject);
// },
put(id,toppingObject){
    return http.put(`/menu/toppings/${toppingObject.itemId}/status`, id, toppingObject);
},
delete(toppingObject){
    return http.delete(`/menu/toppings/${toppingObject.itemId}`);
}
  }