import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        'Content-Type': 'application/json',
    },
});

// Request interceptor for error handling
api.interceptors.response.use(
    (response) => response,
    (error) => {
        console.error('API Error:', error);

        let errorMessage = 'An unexpected error occurred';
        let errorCode = null;

        if (error.response && error.response.data) {
            errorMessage = error.response.data.message || error.response.data.error || errorMessage;
            errorCode = error.response.data.status || error.response.status;

            if (typeof error.response.data === 'string') {
                errorMessage = error.response.data;
            }
        } else if (error.message) {
            errorMessage = error.message;
        }

        error.formattedMessage = errorCode ? `Error ${errorCode}: ${errorMessage}` : errorMessage;

        return Promise.reject(error);
    }
);

export default api;
