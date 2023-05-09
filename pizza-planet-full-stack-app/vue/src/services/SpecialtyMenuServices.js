import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
  });
  
  export default {
 //get specialty menu
get(){
    return axios.get('/menu/specialties');
},
post(specialtyPizzaObject){
    return http.post('/menu/specialties', specialtyPizzaObject);
},
put(specialtyPizzaObject){
    return http.put(`/menu/specialties/${specialtyPizzaObject}`, specialtyPizzaObject);
},
delete(specialtyPizzaObject){
    return http.delete(`/menu/specialties/${specialtyPizzaObject.itemId}`);
},

updateStatus(specialtyPizzaObject){
    return http.put(`/menu/specialties/${specialtyPizzaObject.itemId}/status`, specialtyPizzaObject);
},

  }