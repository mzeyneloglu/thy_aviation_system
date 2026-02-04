import React, { useState, useEffect } from 'react';
import { locationService } from '../../services/locationService';
import LocationCard from './LocationCard';
import LocationForm from './LocationForm';
import Modal from '../Common/Modal';
import Button from '../Common/Button';
import Loading from '../Common/Loading';
import ErrorMessage from '../Common/ErrorMessage';
import './LocationList.css';

export default function LocationList() {
    const [locations, setLocations] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [editingLocation, setEditingLocation] = useState(null);

    useEffect(() => {
        fetchLocations();
    }, []);

    const fetchLocations = async () => {
        try {
            setLoading(true);
            setError(null);
            const data = await locationService.getAll();
            setLocations(data || []);
        } catch (err) {
            setError(err.formattedMessage || err.message || 'Failed to fetch locations');
        } finally {
            setLoading(false);
        }
    };

    const handleAdd = () => {
        setEditingLocation(null);
        setIsModalOpen(true);
    };

    const handleEdit = (location) => {
        setEditingLocation(location);
        setIsModalOpen(true);
    };

    const handleDelete = async (id) => {
        if (!window.confirm('Are you sure you want to delete this location?')) {
            return;
        }

        try {
            await locationService.delete(id);
            setLocations(locations.filter((loc) => loc.id !== id));
        } catch (err) {
            alert('Failed to delete location: ' + (err.formattedMessage || err.message || 'Unknown error'));
        }
    };

    const handleSubmit = async (formData) => {
        try {
            if (editingLocation) {
                // Merge form data with existing data to fill in any missing fields
                const updatedData = {
                    ...editingLocation,
                    ...formData,
                    id: parseInt(editingLocation.id, 10), // Ensure ID is an integer
                };
                const updated = await locationService.update(editingLocation.id, updatedData);
                setLocations(locations.map((loc) =>
                    loc.id === editingLocation.id ? updated : loc
                ));
            } else {
                const created = await locationService.create(formData);
                console.log(created);
                console.log([...locations, created]);
                setLocations([...locations, created]);
            }
            setIsModalOpen(false);
            setEditingLocation(null);
        } catch (err) {
            alert('Failed to save location: ' + (err.formattedMessage || err.message || 'Unknown error'));
        }
    };

    const handleCancel = () => {
        setIsModalOpen(false);
        setEditingLocation(null);
    };

    if (loading) {
        return <Loading message="Loading locations..." />;
    }

    return (
        <div className="location-list-container">
            <div className="page-header">
                <div>
                    <h1>Locations</h1>
                    <p className="page-description">
                        Manage all locations in the aviation system
                    </p>
                </div>
                <Button variant="primary" onClick={handleAdd}>
                    Add Location
                </Button>
            </div>

            {error && <ErrorMessage message={error} onRetry={fetchLocations} />}

            {!error && locations.length === 0 && (
                <div className="empty-state">
                    <span className="empty-icon">○</span>
                    <h3>No locations yet</h3>
                    <p>Get started by adding your first location</p>
                    <Button variant="primary" onClick={handleAdd}>
                        Add First Location
                    </Button>
                </div>
            )}

            {!error && locations.length > 0 && (
                <div className="location-grid">
                    {locations.map((location) => (
                        <LocationCard
                            key={location.id}
                            location={location}
                            onEdit={handleEdit}
                            onDelete={handleDelete}
                        />
                    ))}
                </div>
            )}

            <Modal
                isOpen={isModalOpen}
                onClose={handleCancel}
                title={editingLocation ? 'Edit Location' : 'Add New Location'}
            >
                <LocationForm
                    initialData={editingLocation}
                    onSubmit={handleSubmit}
                    onCancel={handleCancel}
                />
            </Modal>
        </div>
    );
}
