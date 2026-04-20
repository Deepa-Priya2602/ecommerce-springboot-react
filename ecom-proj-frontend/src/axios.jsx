import axios from "axios";

const API = axios.create({
  baseURL: "https://ecommerce-springboot-react-5.onrender.com/api",
});
delete API.defaults.headers.common["Authorization"];
export default API;
