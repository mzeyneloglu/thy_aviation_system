import api from './api';

export const routeService = {
    // Search Routes Between Origin and Destination
    searchRoutes: async (originCode, destinationCode, date = null) => {
        const requestBody = {
            originCode,
            destinationCode,
        };

        if (date) {
            requestBody.date = date;
        }

        const response = await api.post('/routes/search-routes', requestBody);
        return response.data;
    },
};
