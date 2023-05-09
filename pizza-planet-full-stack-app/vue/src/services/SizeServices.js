import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
  });
  
  export default {
 
get(){
    return http.get('/menu/sizes');
},
post(sizeObject){
    return http.post('/menu/sizes', sizeObject);
},
put(sizeObject){
    return http.put(`/menu/sizes/${sizeObject.id}`, sizeObject);
},
delete(sizeObject){
    return http.delete(`/menu/sizes/${sizeObject.id}`);
}
  }