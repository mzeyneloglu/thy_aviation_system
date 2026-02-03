import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import logo from '../../assets/logo.png';
import './Header.css';

export default function Header() {
    const location = useLocation();

    const isActive = (path) => location.pathname === path;

    return (
        <header className="header">
            <div className="container">
                <div className="header-content">
                    <Link to="/" className="logo">
                        <img src={logo} alt="Aviation System Logo" className="logo-img" />
                        <span className="logo-text">Aviation System</span>
                    </Link>

                    <nav className="nav">
                        <Link
                            to="/locations"
                            className={`nav-link ${isActive('/locations') ? 'active' : ''}`}
                        >
                            Locations
                        </Link>
                        <Link
                            to="/transportations"
                            className={`nav-link ${isActive('/transportations') ? 'active' : ''}`}
                        >
                            Transportations
                        </Link>
                        <Link
                            to="/routes"
                            className={`nav-link ${isActive('/routes') ? 'active' : ''}`}
                        >
                            Route Search
                        </Link>
                    </nav>
                </div>
            </div>
        </header>
    );
}
