import axiosStatic from "axios";

export default axiosStatic.create({
    baseURL: "http://localhost/",
    withCredentials: true,
    headers: {Accept: 'application/json'}
})