import React, { useState, useEffect } from 'react';
import { locationService } from '../../services/locationService';
import Select from '../Common/Select';
import Button from '../Common/Button';

export default function RouteSearchForm({ onSearch, loading }) {
    const [formData, setFormData] = useState({
        origin: '',
        destination: '',
        date: '',
    });

    const [locations, setLocations] = useState([]);
    const [loadingLocations, setLoadingLocations] = useState(true);

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
            setLoadingLocations(false);
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        if (formData.origin && formData.destination) {
            if (formData.origin === formData.destination) {
                alert('Origin and destination must be different');
                return;
            }
            onSearch(formData.origin, formData.destination, formData.date);
        }
    };

    const locationOptions = locations.map((loc) => ({
        value: loc.locationCode,
        label: `${loc.locationCode} - ${loc.name} (${loc.city})`,
    }));

    if (loadingLocations) {
        return (
            <div className="card" style={{ textAlign: 'center', padding: 'var(--space-6)' }}>
                Loading locations...
            </div>
        );
    }

    if (locations.length === 0) {
        return (
            <div className="card" style={{ textAlign: 'center', padding: 'var(--space-6)' }}>
                <p style={{ color: 'var(--gray-600)' }}>
                    No locations available. Please add locations first.
                </p>
            </div>
        );
    }

    return (
        <div className="card">
            <h3 style={{ marginBottom: 'var(--space-6)' }}>Search Routes</h3>
            <form onSubmit={handleSubmit}>
                <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: 'var(--space-4)' }}>
                    <Select
                        label="Origin"
                        name="origin"
                        value={formData.origin}
                        onChange={handleChange}
                        options={locationOptions}
                        placeholder="Select origin"
                        required
                    />

                    <Select
                        label="Destination"
                        name="destination"
                        value={formData.destination}
                        onChange={handleChange}
                        options={locationOptions}
                        placeholder="Select destination"
                        required
                    />
                </div>

                <div style={{ marginTop: 'var(--space-4)' }}>
                    <label className="label">Date (Optional)</label>
                    <input
                        type="date"
                        name="date"
                        value={formData.date}
                        onChange={handleChange}
                        className="input"
                        style={{ width: '100%' }}
                    />
                    <p style={{ fontSize: '0.75rem', color: 'var(--gray-500)', marginTop: 'var(--space-1)' }}>
                        Format: YYYY-MM-DD
                    </p>
                </div>

                <div style={{ marginTop: 'var(--space-4)' }}>
                    <Button
                        type="submit"
                        variant="primary"
                        disabled={loading}
                        style={{ width: '100%' }}
                    >
                        {loading ? 'Searching...' : 'Search Routes'}
                    </Button>
                </div>
            </form>
        </div>
    );
}
