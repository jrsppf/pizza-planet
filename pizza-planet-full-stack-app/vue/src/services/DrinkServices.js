import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
  });
  
  export default {
 
get(){
    return http.get('/menu/drinks');
},
post(drinkObject){
    return http.post('/menu/drinks', drinkObject);
},
put(drinkObject){
    return http.put(`/menu/drinks/${drinkObject.id}`, drinkObject);
},
delete(drinkObject){
    return http.delete(`/menu/drinks/${drinkObject.id}`);
}
  }