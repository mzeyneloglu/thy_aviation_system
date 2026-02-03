import React from 'react';
import RouteCard from './RouteCard';
import './RouteResults.css';

export default function RouteResults({ routes: response, origin, destination }) {
    const routes = response?.routes || [];

    if (!routes || routes.length === 0) {
        return (
            <div className="no-routes">
                <span className="no-routes-icon">🚫</span>
                <h3>No routes found</h3>
                <p>
                    No valid routes available from <strong>{origin}</strong> to <strong>{destination}</strong>
                </p>
                <p style={{ fontSize: '0.875rem', color: 'var(--gray-500)' }}>
                    Try adding more transportation connections or selecting different locations.
                </p>
            </div>
        );
    }

    return (
        <div className="route-results">
            <div className="results-header">
                <h2>
                    Found {routes.length} {routes.length === 1 ? 'Route' : 'Routes'}
                </h2>
                <p className="results-subtitle">
                    From <span className="highlight">{response.origin || origin}</span> to <span className="highlight">{response.destination || destination}</span>
                </p>
            </div>

            <div className="routes-grid">
                {routes.map((route, index) => (
                    <RouteCard key={index} route={route} index={index} />
                ))}
            </div>
        </div>
    );
}
