import React, { useState, useEffect } from 'react';
import { locationService } from '../../services/locationService';
import Input from '../Common/Input';
import Select from '../Common/Select';
import Button from '../Common/Button';

const TRANSPORTATION_TYPES = [
    { value: 'FLIGHT', label: '✈️ Flight' },
    { value: 'BUS', label: '🚌 Bus' },
    { value: 'SUBWAY', label: '🚇 Subway' },
    { value: 'UBER', label: '🚗 Uber' },
];

export default function TransportationForm({ initialData, onSubmit, onCancel }) {
    const [formData, setFormData] = useState(
        initialData || {
            originLocationId: '',
            destinationLocationId: '',
            transportationType: '',
            operatingDays: '',
        }
    );

    const [locations, setLocations] = useState([]);
    const [errors, setErrors] = useState({});
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetchLocations();
    }, []);

    const fetchLocations = async () => {
        try {
            const data = await locationService.getAll();
            setLocations(data);
        } catch (err) {
            console.error('Failed to fetch locations:', err);
        } finally {
            setLoading(false);
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value,
        }));
        // Clear error when user starts typing
        if (errors[name]) {
            setErrors((prev) => ({
                ...prev,
                [name]: '',
            }));
        }
    };

    const validate = () => {
        const newErrors = {};

        if (!formData.originLocationId) {
            newErrors.originLocationId = 'Departure location is required';
        }

        if (!formData.destinationLocationId) {
            newErrors.destinationLocationId = 'Arrival location is required';
        }

        if (formData.originLocationId && formData.destinationLocationId && formData.originLocationId === formData.destinationLocationId) {
            newErrors.destinationLocationId = 'Arrival location must be different from departure';
        }

        if (!formData.transportationType) {
            newErrors.transportationType = 'Transportation type is required';
        }

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        if (validate()) {
            onSubmit(formData);
        }
    };

    const locationOptions = locations.map((loc) => ({
        value: loc.id,
        label: `${loc.locationCode} - ${loc.name} (${loc.city})`,
    }));

    if (loading) {
        return <div style={{ padding: 'var(--spacing-xl)', textAlign: 'center' }}>Loading locations...</div>;
    }

    if (locations.length === 0) {
        return (
            <div style={{ padding: 'var(--spacing-xl)', textAlign: 'center' }}>
                <p style={{ color: 'var(--neutral-600)', marginBottom: 'var(--spacing-md)' }}>
                    No locations available. Please add locations first.
                </p>
                <Button variant="secondary" onClick={onCancel}>
                    Close
                </Button>
            </div>
        );
    }

    return (
        <form onSubmit={handleSubmit}>
            <Select
                label="Departure Location"
                name="originLocationId"
                value={formData.originLocationId}
                onChange={handleChange}
                options={locationOptions}
                placeholder="Select departure location"
                required
                error={errors.originLocationId}
            />

            <Select
                label="Arrival Location"
                name="destinationLocationId"
                value={formData.destinationLocationId}
                onChange={handleChange}
                options={locationOptions}
                placeholder="Select arrival location"
                required
                error={errors.destinationLocationId}
            />

            <Select
                label="Transportation Type"
                name="transportationType"
                value={formData.transportationType}
                onChange={handleChange}
                options={TRANSPORTATION_TYPES}
                placeholder="Select transportation type"
                required
                error={errors.transportationType}
            />

            <Input
                label="Operating Days (Optional)"
                name="operatingDays"
                value={formData.operatingDays}
                onChange={handleChange}
                placeholder="e.g. 1,2,3 for Mon,Tue,Wed (1-7)"
                error={errors.operatingDays}
            />

            <div style={{
                display: 'flex',
                gap: 'var(--spacing-md)',
                justifyContent: 'flex-end',
                marginTop: 'var(--spacing-xl)'
            }}>
                <Button type="button" variant="secondary" onClick={onCancel}>
                    Cancel
                </Button>
                <Button type="submit" variant="primary">
                    {initialData ? 'Update Transportation' : 'Add Transportation'}
                </Button>
            </div>
        </form>
    );
}
