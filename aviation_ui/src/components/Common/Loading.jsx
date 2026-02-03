import React from 'react';

export default function Loading({ message = 'Loading...' }) {
    return (
        <div style={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            justifyContent: 'center',
            padding: 'var(--spacing-3xl)',
            gap: 'var(--spacing-md)'
        }}>
            <div className="spinner"></div>
            <p style={{ color: 'var(--neutral-600)', fontSize: '0.875rem' }}>
                {message}
            </p>
        </div>
    );
}
