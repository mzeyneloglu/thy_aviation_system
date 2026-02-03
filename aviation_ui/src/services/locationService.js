import api from './api';

export const locationService = {
    // Get All Locations
    getAll: async () => {
        const response = await api.get('/locations/get-all-locations');
        const data = response.data;

        if (Array.isArray(data)) {
            return data;
        }
        if (data && typeof data === 'object') {
            if (Array.isArray(data.data)) return data.data;
            if (Array.isArray(data.content)) return data.content;
            if (Array.isArray(data.locations)) return data.locations;
        }
        return data;
    },

    // Create New Location
    create: async (locationData) => {
        const response = await api.post('/locations/insert-location', locationData);
        return response.data;
    },

    // Update Location
    update: async (id, locationData) => {
        const updatePayload = {
            id: parseInt(id, 10), 
            ...locationData,
        };
        const response = await api.put(`/locations/update-location`, updatePayload);
        return response.data;
    },

    // Delete Location
    delete: async (id) => {
        const response = await api.delete(`/locations/delete-location?id=${id}`);
        return response.data;
    },
};
