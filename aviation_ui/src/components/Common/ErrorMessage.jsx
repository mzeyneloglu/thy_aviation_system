import React from 'react';
import Button from './Button';
import './ErrorMessage.css';

export default function ErrorMessage({ message, onRetry }) {
    return (
        <div className="error-container">
            <div className="error-icon-wrapper">
                <span className="error-icon">⚠️</span>
            </div>

            <h3 className="error-title">Network Error</h3>

            <div className="error-actions">
                {onRetry && (
                    <Button onClick={onRetry} variant="primary">
                        Try Again
                    </Button>
                )}
            </div>
        </div>
    );
}
