import React from 'react';
import Button from '../Common/Button';
import './TransportationCard.css';

const getTypeBadgeClass = (type) => {
    const typeMap = {
        FLIGHT: 'badge-flight',
        BUS: 'badge-bus',
        SUBWAY: 'badge-subway',
        UBER: 'badge-uber',
    };
    return typeMap[type] || 'badge-uber';
};



export default function TransportationCard({ transportation, onEdit, onDelete }) {
    const { originLocation, destinationLocation, transportationType } = transportation;

    return (
        <div className="transportation-card fade-in">
            <div className="transportation-header">
                <span className={`badge ${getTypeBadgeClass(transportationType)}`}>
                    {transportationType}
                </span>
                <div className="transportation-actions">
                    <Button
                        variant="text"
                        onClick={() => onEdit(transportation)}
                        className="btn-sm"
                    >
                        Edit
                    </Button>
                    <Button
                        variant="text-danger"
                        onClick={() => onDelete(transportation.id)}
                        className="btn-sm"
                    >
                        Delete
                    </Button>
                </div>
            </div>

            <div className="transportation-body">
                <div className="route-visual">
                    <div className="route-point">
                        <div className="point-code">{originLocation?.locationCode || 'N/A'}</div>
                        <div className="point-name">{originLocation?.name || 'Unknown'}</div>
                        <div className="point-location">
                            {originLocation?.city}, {originLocation?.country}
                        </div>
                    </div>

                    <div className="route-arrow">
                        <div className="arrow-line"></div>
                        <div className="arrow-head">→</div>
                    </div>

                    <div className="route-point">
                        <div className="point-code">{destinationLocation?.locationCode || 'N/A'}</div>
                        <div className="point-name">{destinationLocation?.name || 'Unknown'}</div>
                        <div className="point-location">
                            {destinationLocation?.city}, {destinationLocation?.country}
                        </div>
                    </div>
                </div>

                {transportation.operatingDays && (
                    <div className="operating-days-info">
                        <span className="info-label">Operating Days:</span>
                        <div className="days-list">
                            {transportation.operatingDays.split(',').map(day => {
                                const dayMap = {
                                    '1': 'Mon', '2': 'Tue', '3': 'Wed', '4': 'Thu',
                                    '5': 'Fri', '6': 'Sat', '7': 'Sun'
                                };
                                return (
                                    <span key={day} className="day-badge" title={dayMap[day] || day}>
                                        {dayMap[day] || day}
                                    </span>
                                );
                            })}
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}
