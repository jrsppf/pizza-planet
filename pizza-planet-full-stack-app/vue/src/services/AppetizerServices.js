import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
  });
  
  export default {
 
get(){
    return http.get('/menu/appetizer');
},
post(appetizerObject){
    return http.post('/menu/appetizers', appetizerObject);
},
put(appetizerObject){
    return http.put(`/menu/appetizers/${appetizerObject.id}`, appetizerObject);
},
delete(appetizerObject){
    return http.delete(`/menu/appetizers/${appetizerObject.id}`);
}
  }