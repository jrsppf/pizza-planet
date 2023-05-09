import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
  });
  
  export default {
 
get(){
    return http.get('/menu/salads');
},
post(saladObject){
    return http.post('/menu/salads', saladObject);
},
put(saladObject){
    return http.put(`/menu/salads/${saladObject.id}`, saladObject);
},
delete(saladObject){
    return http.delete(`/menu/salads/${saladObject.id}`);
}
  }