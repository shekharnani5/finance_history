import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

const getAuthToken = () => localStorage.getItem('token');

const axiosInstance = axios.create({
    baseURL: API_URL,
});

axiosInstance.interceptors.request.use(config => {
    const token = getAuthToken();
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

export const register = (data) => axiosInstance.post('/auth/register', data);
export const login = (data) => axiosInstance.post('/auth/login', data);
export const addTransaction = (data) => axiosInstance.post('/transactions', data);
export const getTransactions = () => axiosInstance.get('/transactions');
export const getAnalytics = () => axiosInstance.get('/transactions/analytics');
