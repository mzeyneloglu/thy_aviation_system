import React from 'react';
import { Link } from 'react-router-dom';
import './HomePage.css';

export default function HomePage() {
    return (
        <div className="home-page">
            <div className="hero-section">
                <h1>Aviation Route Management System</h1>
                <p className="hero-description">
                    Manage locations, transportations, and discover optimal routes across the aviation network
                </p>
                <div className="hero-actions">
                    <Link to="/locations" className="btn btn-primary">
                        Get Started
                    </Link>
                </div>
            </div>

            <div className="features-grid">
                <div className="feature-card">
                    <h3>Locations</h3>
                    <p>Manage airports and transportation hubs with location codes</p>
                    <Link to="/locations" className="feature-link">Manage Locations →</Link>
                </div>

                <div className="feature-card">
                    <h3>Transportations</h3>
                    <p>Configure flights, buses, subways, and other connections</p>
                    <Link to="/transportations" className="feature-link">Manage Transportations →</Link>
                </div>

                <div className="feature-card">
                    <h3>Route Search</h3>
                    <p>Find all valid routes between any two locations</p>
                    <Link to="/routes" className="feature-link">Search Routes →</Link>
                </div>
            </div>
        </div>
    );
}
