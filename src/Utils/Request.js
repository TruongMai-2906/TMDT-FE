import axios from 'axios';

const tokenType = localStorage.getItem('tokenType');
const accessToken = localStorage.getItem('accessToken');
// spring.datasource.url=jdbc:mysql://localhost:3307/webcuoiky1
// spring.datasource.username=root
// spring.datasource.password=

export const request = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'Content-Type': 'application/json',
        Authorization: `${tokenType} ${accessToken}`,
    },
});

export const methodGet = (url, options) => {
    return request.get(url, options);
    // return kq.data;
};
export const methodPost = (url, data) => {
    return request.post(url, data);
};
