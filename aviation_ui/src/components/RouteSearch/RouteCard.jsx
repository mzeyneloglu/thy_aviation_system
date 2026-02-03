import React from 'react';
import './RouteCard.css';

const getTypeBadgeClass = (type) => {
    const typeMap = {
        FLIGHT: 'badge-flight',
        BUS: 'badge-bus',
        SUBWAY: 'badge-subway',
        UBER: 'badge-uber',
    };
    return typeMap[type] || 'badge-uber';
};



export default function RouteCard({ route, index }) {
    const segments = route.segments || [];

    return (
        <div className="route-card fade-in">
            <div className="route-card-header">
                <h4>Route Option {index + 1}</h4>
                <span className="route-steps-badge">
                    {segments.length} {segments.length === 1 ? 'Step' : 'Steps'}
                </span>
            </div>

            <div className="route-steps">
                {segments.map((segment, idx) => (
                    <div key={idx} className="route-step">
                        <div className="step-number">{idx + 1}</div>

                        <div className="step-content">
                            <div className="step-header">
                                <span className={`badge ${getTypeBadgeClass(segment.transportationType)}`}>
                                    {segment.transportationType}
                                </span>
                            </div>

                            <div className="step-route">
                                <div className="step-location">
                                    <div className="location-code">
                                        {segment.originCode}
                                    </div>
                                    <div className="location-name">
                                        {segment.originName}
                                    </div>
                                </div>

                                <div className="step-arrow">→</div>

                                <div className="step-location">
                                    <div className="location-code">
                                        {segment.destinationCode}
                                    </div>
                                    <div className="location-name">
                                        {segment.destinationName}
                                    </div>
                                </div>
                            </div>
                            {segment.description && (
                                <div style={{ marginTop: 'var(--space-2)', fontSize: '0.75rem', color: 'var(--gray-500)', fontStyle: 'italic' }}>
                                    {segment.description}
                                </div>
                            )}
                        </div>
                    </div>
                ))}
            </div>
            {route.description && (
                <div style={{ padding: 'var(--space-3)', background: 'var(--gray-50)', borderTop: '1px solid var(--gray-100)', fontSize: '0.875rem', color: 'var(--gray-600)' }}>
                    <strong>Route Summary:</strong> {route.description}
                </div>
            )}
        </div>
    );
}
