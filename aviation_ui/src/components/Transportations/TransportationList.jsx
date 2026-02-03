import React, { useState, useEffect } from 'react';
import { transportationService } from '../../services/transportationService';
import TransportationCard from './TransportationCard';
import TransportationForm from './TransportationForm';
import Modal from '../Common/Modal';
import Button from '../Common/Button';
import Loading from '../Common/Loading';
import ErrorMessage from '../Common/ErrorMessage';
import './TransportationList.css';

export default function TransportationList() {
    const [transportations, setTransportations] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [editingTransportation, setEditingTransportation] = useState(null);

    useEffect(() => {
        fetchTransportations();
    }, []);

    const fetchTransportations = async () => {
        try {
            setLoading(true);
            setError(null);
            const data = await transportationService.getAll();
            setTransportations(data || []);
        } catch (err) {
            setError(err.message || 'Failed to fetch transportations');
        } finally {
            setLoading(false);
        }
    };

    const handleAdd = () => {
        setEditingTransportation(null);
        setIsModalOpen(true);
    };

    const handleEdit = (transportation) => {
        const formData = {
            ...transportation,
            originLocationId: transportation.originLocation?.id || '',
            destinationLocationId: transportation.destinationLocation?.id || '',
        };
        setEditingTransportation(formData);
        setIsModalOpen(true);
    };

    const handleDelete = async (id) => {
        if (!window.confirm('Are you sure you want to delete this transportation?')) {
            return;
        }

        try {
            await transportationService.delete(id);
            setTransportations(transportations.filter((t) => t.id !== id));
        } catch (err) {
            alert('Failed to delete transportation: ' + (err.message || 'Unknown error'));
        }
    };

    const handleSubmit = async (formData) => {
        try {
            if (editingTransportation) {
                // Merge form data with existing data to fill in any missing fields
                const updatedData = {
                    ...editingTransportation,
                    ...formData,
                    id: editingTransportation.id, // Ensure ID is preserved
                };
                const updated = await transportationService.update(updatedData);
                setTransportations(transportations.map((t) =>
                    t.id === editingTransportation.id ? updated : t
                ));
            } else {
                const created = await transportationService.create(formData);
                setTransportations([...transportations, created]);
            }
            setIsModalOpen(false);
            setEditingTransportation(null);
        } catch (err) {
            alert('Failed to save transportation: ' + (err.message || 'Unknown error'));
        }
    };

    const handleCancel = () => {
        setIsModalOpen(false);
        setEditingTransportation(null);
    };

    if (loading) {
        return <Loading message="Loading transportations..." />;
    }

    return (
        <div className="transportation-list-container">
            <div className="page-header">
                <div>
                    <h1>Transportations</h1>
                    <p className="page-description">
                        Manage all transportation connections between locations
                    </p>
                </div>
                <Button variant="primary" onClick={handleAdd}>
                    Add Transportation
                </Button>
            </div>

            {error && <ErrorMessage message={error} onRetry={fetchTransportations} />}

            {!error && transportations.length === 0 && (
                <div className="empty-state">
                    <span className="empty-icon">○</span>
                    <h3>No transportations yet</h3>
                    <p>Get started by adding your first transportation connection</p>
                    <Button variant="primary" onClick={handleAdd}>
                        Add First Transportation
                    </Button>
                </div>
            )}

            {!error && transportations.length > 0 && (
                <div className="transportation-grid">
                    {transportations.map((transportation) => (
                        <TransportationCard
                            key={transportation.id}
                            transportation={transportation}
                            onEdit={handleEdit}
                            onDelete={handleDelete}
                        />
                    ))}
                </div>
            )}

            <Modal
                isOpen={isModalOpen}
                onClose={handleCancel}
                title={editingTransportation ? 'Edit Transportation' : 'Add New Transportation'}
            >
                <TransportationForm
                    initialData={editingTransportation}
                    onSubmit={handleSubmit}
                    onCancel={handleCancel}
                />
            </Modal>
        </div>
    );
}
