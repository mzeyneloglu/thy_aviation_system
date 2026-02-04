import React from 'react';
import RouteCard from './RouteCard';
import Button from '../Common/Button';
import './RouteDetailPanel.css';

export default function RouteDetailPanel({ route, onClose }) {
    if (!route) return null;

    return (
        <div className="route-detail-panel slide-in-right">
            <div className="panel-header">
                <h3>Route Details</h3>
                <Button variant="text" onClick={onClose} className="close-btn">
                    ✕
                </Button>
            </div>

            <div className="panel-content">
                <RouteCard route={route} index={0} />
            </div>
        </div>
    );
}
