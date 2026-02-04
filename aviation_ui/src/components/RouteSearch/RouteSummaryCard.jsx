import React from 'react';
import './RouteSummaryCard.css';

export default function RouteSummaryCard({ route, index, onClick, isSelected }) {
    const segments = route.segments || [];

    const flightSegment = segments.find(s => s.transportationType === 'FLIGHT');

    const viaLocation = flightSegment ? flightSegment.originName : segments[0]?.originName;

    return (
        <div
            className={`route-summary-card fade-in ${isSelected ? 'selected' : ''}`}
            onClick={onClick}
        >
            <div className="summary-header">
                <span className="via-text">Via</span>
                <span className="airport-name-large">{viaLocation}</span>
            </div>

            <div className="summary-footer">
                <span className="steps-badge">
                    {segments.length} {segments.length === 1 ? 'Step' : 'Steps'}
                </span>
                <span className="view-details">
                    View Details →
                </span>
            </div>
        </div>
    );
}
