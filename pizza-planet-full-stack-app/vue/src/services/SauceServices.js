import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
  });
  
  export default {
 
get(){
    return http.get('/menu/sauces');
},
post(sauceObject){
    return http.post('/menu/sauces', sauceObject);
},
put(sauceObject){
    return http.put(`/menu/sauces/${sauceObject.itemId}/status`, sauceObject);
},
delete(sauceObject){
    return http.delete(`/menu/sauces/${sauceObject.itemId}`);
}
  }