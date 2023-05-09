import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
  });
  
  export default {
 
get(){
    return http.get('/menu/crusts');
},
post(crustObject){
    return http.post('/menu/crusts', crustObject);
},
put(crustObject){
    return http.put(`/menu/crusts/${crustObject.itemId}/status`, crustObject);
}
  }