import React from 'react';
import { Outlet } from 'react-router-dom';
import Header from './Header';
import './Layout.css';

export default function Layout() {
    return (
        <div className="layout">
            <Header />
            <main className="main-content">
                <div className="container">
                    <Outlet />
                </div>
            </main>
            <footer className="footer">
                <div className="container">
                    <p>© 2026 Turkish Airlines Technology - Aviation Route Management System</p>
                </div>
            </footer>
        </div>
    );
}
