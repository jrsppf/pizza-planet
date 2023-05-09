import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:9000"
  });
  
  export default {
 
get(){
    return http.get('/menu/sides');
},
post(sideObject){
    return http.post('/menu/sides', sideObject);
},
put(sideObject){
    return http.put(`/menu/sides/${sideObject.id}`, sideObject);
},
delete(sideObject){
    return http.delete(`/menu/sides/${sideObject.id}`);
}
  }