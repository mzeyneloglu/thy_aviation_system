import React, { useState } from 'react';
import RouteSummaryCard from './RouteSummaryCard';
import RouteDetailPanel from './RouteDetailPanel';
import './RouteResults.css';

export default function RouteResults({ routes: response, origin, destination }) {
    const routes = response?.routes || [];
    const [selectedRoute, setSelectedRoute] = useState(null);

    const handleRouteClick = (route) => {
        setSelectedRoute(route);
    };

    const handleClosePanel = () => {
        setSelectedRoute(null);
    };

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
        <div className="route-results-container">
            <div className="results-list-section">
                <div className="results-header">
                    <h2>
                        Found {routes.length} {routes.length === 1 ? 'Route' : 'Routes'}
                    </h2>
                    <p className="results-subtitle">
                        From <span className="highlight">{response.origin || origin}</span> to <span className="highlight">{response.destination || destination}</span>
                    </p>
                </div>

                <div className="routes-list">
                    {routes.map((route, index) => (
                        <RouteSummaryCard
                            key={index}
                            route={route}
                            index={index}
                            onClick={() => handleRouteClick(route)}
                            isSelected={selectedRoute === route}
                        />
                    ))}
                </div>
            </div>

            {selectedRoute && (
                <>
                    <div className="panel-overlay" onClick={handleClosePanel}></div>
                    <RouteDetailPanel
                        route={selectedRoute}
                        onClose={handleClosePanel}
                    />
                </>
            )}
        </div>
    );
}
