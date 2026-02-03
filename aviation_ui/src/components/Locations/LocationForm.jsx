import React, { useState } from 'react';
import Input from '../Common/Input';
import Button from '../Common/Button';

export default function LocationForm({ initialData, onSubmit, onCancel }) {
    const [formData, setFormData] = useState(
        initialData || {
            name: '',
            country: '',
            city: '',
            locationCode: '',
        }
    );

    const [errors, setErrors] = useState({});

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

        if (!formData.name.trim()) {
            newErrors.name = 'Name is required';
        }

        if (!formData.country.trim()) {
            newErrors.country = 'Country is required';
        }

        if (!formData.city.trim()) {
            newErrors.city = 'City is required';
        }

        if (!formData.locationCode.trim()) {
            newErrors.locationCode = 'Location code is required';
        } 
        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        if (validate()) {
            onSubmit({
                ...formData,
                locationCode: formData.locationCode.toUpperCase(),
            });
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <Input
                label="Location Name"
                name="name"
                value={formData.name}
                onChange={handleChange}
                placeholder="e.g., Istanbul Airport"
                required
                error={errors.name}
            />

            <Input
                label="Country"
                name="country"
                value={formData.country}
                onChange={handleChange}
                placeholder="e.g., Turkey"
                required
                error={errors.country}
            />

            <Input
                label="City"
                name="city"
                value={formData.city}
                onChange={handleChange}
                placeholder="e.g., Istanbul"
                required
                error={errors.city}
            />

            <Input
                label="Location Code"
                name="locationCode"
                value={formData.locationCode}
                onChange={handleChange}
                placeholder="e.g., IST"
                required
                error={errors.locationCode}
                maxLength={3}
                style={{ textTransform: 'uppercase' }}
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
                    {initialData ? 'Update Location' : 'Add Location'}
                </Button>
            </div>
        </form>
    );
}
