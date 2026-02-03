import React from 'react';
import Button from '../Common/Button';
import './LocationCard.css';

export default function LocationCard({ location, onEdit, onDelete }) {
    return (
        <div className="location-card fade-in">
            <div className="location-header">
                <div className="location-code">{location.locationCode}</div>
                <div className="location-actions">
                    <Button
                        variant="text"
                        onClick={() => onEdit(location)}
                        className="btn-sm"
                    >
                        Edit
                    </Button>
                    <Button
                        variant="text-danger"
                        onClick={() => onDelete(location.id)}
                        className="btn-sm"
                    >
                        Delete
                    </Button>
                </div>
            </div>

            <div className="location-body">
                <h4 className="location-name">{location.name}</h4>
                <div className="location-meta">
                    {location.city}, {location.country}
                </div>
            </div>
        </div>
    );
}
