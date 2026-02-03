import api from './api';

export const transportationService = {
    // Get All Transportations
    getAll: async () => {
        const response = await api.get('/transportations/get-all-transportations');
        const data = response.data;

        if (Array.isArray(data)) {
            return data;
        }
        if (data && typeof data === 'object') {
            if (Array.isArray(data.data)) return data.data;
            if (Array.isArray(data.content)) return data.content;
            if (Array.isArray(data.transportations)) return data.transportations;
        }
        return data;
    },

    // Create New Transportation
    create: async (transportationData) => {
        const response = await api.post('/transportations/insert-transportation', transportationData);
        return response.data;
    },

    // Update Transportation
    update: async (transportationData) => {
        const response = await api.put(`/transportations/update-transportation`, transportationData);
        return response.data;
    },

    // Delete Transportation
    delete: async (id) => {
        const response = await api.delete(`/transportations/delete-transportation?id=${id}`);
        return response.data;
    },
};
